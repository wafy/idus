package com.homework.idus.core.user.query;

import com.homework.idus.core.user.command.User;
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
        Specification<User> spec = Specification.where(UserSpecification.likeName(command.getName()));


        if (command.getEmail() != null) {
            spec = spec.and(UserSpecification.likeEmail(command.getEmail()));
        }

        return userPageSearchRepository.findAll(spec, pageable);
    }


}
