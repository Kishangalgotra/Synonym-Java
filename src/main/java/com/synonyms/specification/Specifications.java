package com.synonyms.specification;

import com.synonyms.util.CommonUtils;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;

public class Specifications {
    public static <T> Specification<T> combineWithAnd(final List<org.springframework.data.jpa.domain.Specification<T>> specifications) {
        if (CommonUtils.isEmpty(specifications))
            return org.springframework.data.jpa.domain.Specification.where(null);

        org.springframework.data.jpa.domain.Specification<T> specs = null;
        for (final org.springframework.data.jpa.domain.Specification<T> specification : specifications) {
            if (specs != null) {
                specs = specs.and(specification);
            } else {
                specs = org.springframework.data.jpa.domain.Specification.where(specification);
            }
        }
        return specs;
    }

    public static <T> org.springframework.data.jpa.domain.Specification<T> isEqual(String fieldKey, String fieldValue) {
        return (root, cq, cb) -> cb.equal(root.<String>get(fieldKey), fieldValue);
    }

    public static <T, R> org.springframework.data.jpa.domain.Specification<T> isFieldNotIn(String field, List<R> values) {
        return (root, cq, cb) -> root.get(field).in(values).not();
    }

}
