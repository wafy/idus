package com.homework.idus.core.user.query;

import com.homework.idus.core.user.command.User;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {


    public static Specification<User> likeName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), name + "%");
    }

    public static Specification<User> likeEmail(String email) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("email"), email + "%");
    }
}
