package com.homework.idus.core.user.command;

import com.homework.idus.core.exception.UserExistException;
import com.homework.idus.core.user.query.UserSearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    private final UserSearcherRepository userSearcherRepository;

    public User create(User user) {
          if (userSearcherRepository.countByName(user.getName()) > 0) {
            throw new UserExistException("이미 가입된 사용자입니다.");
        }
        return userRepository.save(user);
    }

}
