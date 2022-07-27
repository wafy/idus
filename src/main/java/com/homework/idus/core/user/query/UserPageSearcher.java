package com.homework.idus.core.user.query;

import com.homework.idus.core.user.command.User;
import com.homework.idus.web.v1.admin.search.query.SearchKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPageSearcher {

    private final UserPageSearchRepository userPageSearchRepository;

    public Page<User> findAll(UserSearchDescription command, Pageable pageable) {
        Specification<User> spec = (root, query, criteriaBuilder) -> null;

        if (command.getSearchKey() != null && command.getSearchKey() == SearchKey.NAME) {
            spec = spec.and(UserSpecification.likeName(command.getSearchValue()));
        }

        if (command.getSearchKey() != null && command.getSearchKey() == SearchKey.EMAIL) {
            spec = spec.and(UserSpecification.likeEmail(command.getSearchValue()));
        }

        return userPageSearchRepository.findAll(spec, pageable);
    }
}
