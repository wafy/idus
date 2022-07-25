package com.homework.idus.core.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSearcher {

    private final UserSearcherRepository searcherRepository;

    public Optional<User> findOne(String name, String password) {
      return searcherRepository.findByNameAndPassword(name, password);
    }

}
