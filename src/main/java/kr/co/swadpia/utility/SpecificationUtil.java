package kr.co.swadpia.utility;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.Arrays;
import java.util.List;

public class SpecificationUtil {

    private static final String[] ignoreFieldArgs = {"page", "size", "sort", "sortBy", "pageable"};

    public static void addDefaultPredicate(List<Predicate> predicates, CriteriaBuilder cb, Root<?> root, String key, Object value) {
        if (!shouldIgnoreField(key)) {
            predicates.add(cb.equal(root.get(key).as(String.class), value));
        }
    }

    private static boolean shouldIgnoreField(String fieldName) {
        return Arrays.asList(ignoreFieldArgs).contains(fieldName);
    }
}
