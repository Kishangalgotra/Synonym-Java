package com.synonyms.services;

import com.synonyms.controller.SynonymsController;
import com.synonyms.entity.Synonyms;
import com.synonyms.exception.CommonException;
import com.synonyms.model.SearchSynonymDto;
import com.synonyms.repository.SynonymsRepo;
import com.synonyms.request.SynonymsCreateRequest;
import com.synonyms.response.SynonymsCreationResponse;
import com.synonyms.util.CommonUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SynonymsService {

    @Autowired
    @PersistenceContext()
    private EntityManager entityManagerRead;
    @Autowired
    private final SynonymsRepo synonymsRepo;
    @Autowired
    private ModelMapper modelMapper;
    private static final Logger log = LoggerFactory.getLogger(SynonymsController.class);

    public SynonymsService(EntityManager entityManagerRead, SynonymsRepo synonymsRepo) {
        this.entityManagerRead = entityManagerRead;
        this.synonymsRepo = synonymsRepo;
    }

    public SynonymsCreationResponse createOrUpdateSynonym(SynonymsCreateRequest request) {
        SynonymsCreationResponse existingSynonymsData = addIfSynonymAlreadyExist(request);
        if (CommonUtils.isNotNull(existingSynonymsData))
            return existingSynonymsData;

        String newSynonymId = CommonUtils.randomAlphaNumericNo(7);
        List<Synonyms> synonymsList = new ArrayList<>();
        for (String synonym : request.getSynonyms()) {
            Synonyms synonyms = new Synonyms();
            synonyms.setSynonyms(synonym);
            synonyms.setSynonymId(newSynonymId);
            synonymsList.add(synonyms);
        }

        HashSet<String> newList = new HashSet<>(request.getSynonyms());
        newList.remove(request.getActualKeyword());
        SynonymsCreationResponse response = new SynonymsCreationResponse();
        response.setActualKeyword(request.getActualKeyword());
        response.setSynonyms(newList);

        synonymsRepo.saveAll(synonymsList);
        return response;
    }

    private SynonymsCreationResponse addIfSynonymAlreadyExist(SynonymsCreateRequest request) {
        HashSet<String> searchList = new HashSet<>(request.getSynonyms());
        searchList.add(request.getActualKeyword());
        Pageable topTwenty = PageRequest.of(0, 1);
        List<SearchSynonymDto> fetchedSynonymDtoList
                = synonymsRepo.findSynonymIdBySynonymsContaining(new ArrayList<>(searchList));

        if (CommonUtils.isNotEmpty(fetchedSynonymDtoList)) {
            Set<String> existingSynonyms
                    = fetchedSynonymDtoList.stream().map(SearchSynonymDto::getSynonym).collect(Collectors.toSet());
            List<String> nonExistingElements = nonExistingElements(searchList, existingSynonyms);

            String synonymId = fetchedSynonymDtoList.get(0).getSynonymId();
            List<Synonyms> synonymsList = new ArrayList<>();
            for (String synonym : nonExistingElements) {
                Synonyms synonyms = new Synonyms();
                synonyms.setSynonyms(synonym);
                synonyms.setSynonymId(synonymId);
                synonymsList.add(synonyms);
            }

            HashSet<String> newList = new HashSet<>(request.getSynonyms());
            newList.addAll(existingSynonyms);
            newList.remove(request.getActualKeyword());
            SynonymsCreationResponse response = new SynonymsCreationResponse();
            response.setActualKeyword(request.getActualKeyword());
            response.setSynonyms(newList);
            synonymsRepo.saveAll(synonymsList);
            return response;
        }
        return null;
    }

    private List<String> nonExistingElements(Set<String> hashSet1, Set<String> hashSet2) {
        HashSet<String> hashSet3 = new HashSet<>();
        for (String element : hashSet1) {
            if (!hashSet2.contains(element)) {
                hashSet3.add(element);
            }
        }
        return new ArrayList<>(hashSet3);
    }

    public List<String> fetchSynonyms(String keyword) {
        return synonymsRepo.findByWord(keyword);
    }

    @Transactional
    public List<String> deleteSynonym(String keyword) {
        String uniqueId;
        Synonyms synonyms = synonymsRepo.findBySynonyms(keyword);
        if (CommonUtils.isNotNull(synonyms)) {
            uniqueId = synonyms.getSynonymId();
            synonymsRepo.deleteBySynonyms(keyword);
            return synonymsRepo.findBySynonymId(uniqueId);
        } else {
            throw new CommonException(HttpStatus.NOT_FOUND, "synonym not found");
        }
    }
}
