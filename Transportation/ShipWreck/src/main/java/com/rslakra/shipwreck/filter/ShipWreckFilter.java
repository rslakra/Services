package com.rslakra.shipwreck.filter;

import com.devamatre.framework.spring.filter.AbstractFilterImpl;
import com.rslakra.shipwreck.model.ShipWreck;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 10:05 PM
 */
@Getter
@Setter
public final class ShipWreckFilter extends AbstractFilterImpl implements Specification<ShipWreck> {

    private FilterCriteria criteria;

    /**
     * @param allParams
     * @param criteria
     */
    public ShipWreckFilter(Map<String, Object> allParams, FilterCriteria criteria) {
        super(allParams);
        this.criteria = criteria;
    }

    /**
     * @param allParams
     */
    public ShipWreckFilter(Map<String, Object> allParams) {
        this(allParams, null);
    }

    /**
     * @param root
     * @param query
     * @param builder
     * @return
     */
    @Override
    public Predicate toPredicate(Root<ShipWreck> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.equals(FilterOperation.GT)) {
            return builder.greaterThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.equals(FilterOperation.LT)) {
            return builder.lessThanOrEqualTo(root.<String>get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.equals(FilterOperation.LIKE)) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }

        return null;
    }

}
