package com.homework.idus.core.user.query;

import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.core.user.command.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSearcher {

    private final UserSearcherRepository searcherRepository;

    public Optional<User> findByName(UserLoginDescription user) {
      User savedUser =searcherRepository.findByName(user.getName())
              .orElseThrow(() -> new UserNotFountException("사용자 정보를 찾을 수 없습니다."));

      if (!savedUser.isMatchesPassword(user.getPassword())) throw new UserNotFountException("사용자 정보를 찾을 수 없습니다.");
      return Optional.of(savedUser);
    }

}
