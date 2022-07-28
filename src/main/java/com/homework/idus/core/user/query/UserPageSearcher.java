package com.homework.idus.core.user.query;

import com.homework.idus.core.user.command.User;
import com.homework.idus.web.v1.admin.search.query.SearchKey;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserPageSearcher {

    private final UserPageSearchRepository userPageSearchRepository;

    /**
     * 사용자 목록을 조회합니다.
     * @param command 조회 요청
     * @return 조회된 결과
     */
    @Transactional(readOnly = true)
    public Page<User> findAll(UserSearchDescription command) {
        Specification<User> spec = (root, query, criteriaBuilder) -> null;

        if (command.getSearchKey() != null && command.getSearchKey() == SearchKey.NAME) {
            spec = spec.and(UserSpecification.likeName(command.getSearchValue()));
        }

        if (command.getSearchKey() != null && command.getSearchKey() == SearchKey.EMAIL) {
            spec = spec.and(UserSpecification.likeEmail(command.getSearchValue()));
        }

        Pageable pageable = PageRequest.of(command.getPage(), command.getSize());

        return userPageSearchRepository.findAll(spec, pageable);
    }
}
