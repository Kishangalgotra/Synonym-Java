package com.synonys.specification;

import com.synonys.entity.Synonyms;
import com.synonys.request.ChargeDataSearchRequest;
import com.synonys.util.CommonUtils;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ChargeDataSpecification {
    public static Specification<Synonyms> specificationBuilder(ChargeDataSearchRequest request,
                                                               Join<Object, Object> userJoin) {

        List<Specification<Synonyms>> specifications = new ArrayList<>();

        if (CommonUtils.isNotEmpty(request.getUniqueId()))
            specifications.add((root, cq, cb) -> cb.equal(userJoin.get("uniqueId"), request.getUniqueId()));

        return Specifications.combineWithAnd(specifications);
    }
}
