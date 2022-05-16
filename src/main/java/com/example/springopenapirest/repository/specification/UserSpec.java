package com.example.springopenapirest.repository.specification;

import com.example.springopenapirest.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;
import java.util.stream.Stream;

@Setter
@Getter
public class UserSpec implements Specification<User> {
    private String name;
    private String phone;

    @Override
    public Predicate toPredicate(@NotNull Root<User> root, @NotNull CriteriaQuery<?> query, @NotNull CriteriaBuilder cb) {
        return getPredicates(root, query, cb)
                .filter(Objects::nonNull)
                .reduce(cb::and)
                .orElseGet(cb::conjunction);
    }

    protected @NotNull Stream<Predicate> getPredicates(@NotNull Root<User> root,
                                                       CriteriaQuery<?> criteriaQuery,
                                                       @NotNull CriteriaBuilder cb) {
        return Stream.of(
                createLike(root, cb, "name", name),
                createEq(root, cb, "phone", phone));
    }

    protected Predicate createEq(Root<?> root, CriteriaBuilder cb, String fieldName, Object value) {
        return value != null ? cb.equal(root.get(fieldName), value) : null;
    }


    protected Predicate createLike(Root<?> root, CriteriaBuilder cb, String fieldName, String value) {
        return value != null ? cb.like(cb.upper(root.get(fieldName)), "%" + StringUtils.upperCase(value) + "%") : null;
    }
}
