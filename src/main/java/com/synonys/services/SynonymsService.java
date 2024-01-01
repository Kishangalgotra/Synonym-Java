package com.synonys.services;

import com.synonys.controller.SynonymsController;
import com.synonys.entity.Synonyms;
import com.synonys.repository.SynonymsRepo;
import com.synonys.request.ChargeDataSearchRequest;
import com.synonys.request.SynonymsCreateRequest;
import com.synonys.response.SynonymsCreationResponse;
import com.synonys.response.GenericPaginationResponse;
import com.synonys.specification.ChargeDataSpecification;
import com.synonys.util.CommonUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class SynonymsService {

    @Autowired
    @PersistenceContext()
    private EntityManager entityManagerRead;

    @Autowired
    private final SynonymsRepo synonymsRepo;
    private static final Logger log = LoggerFactory.getLogger(SynonymsController.class);

    @Autowired
    private ModelMapper modelMapper;

    public SynonymsService(EntityManager entityManagerRead, SynonymsRepo synonymsRepo) {
        this.entityManagerRead = entityManagerRead;
        this.synonymsRepo = synonymsRepo;
    }

    public SynonymsCreationResponse createOrUpdateSynonym(SynonymsCreateRequest request) {
        Synonyms synonyms = synonymsRepo.findBySynonymsContaining(request.getSynonymKeyword());
        if (CommonUtils.isNotNull(synonyms)) {
            SynonymsCreationResponse synonymsCreationResponse = new SynonymsCreationResponse();
            synonymsCreationResponse.setSynonyms(Arrays.asList(synonyms.getSynonyms().split(",")));
            return synonymsCreationResponse;
        }
        synonyms = new Synonyms();
        synonyms.setSynonyms(Arrays.asList(request.getSynonymKeyword(), request.getActualKeyword()).toString());
        synonymsRepo.save(synonyms);
        SynonymsCreationResponse response = modelMapper.map(synonyms, SynonymsCreationResponse.class);
        response.setActualKeyword(request.getActualKeyword());
        return response;
    }
}
