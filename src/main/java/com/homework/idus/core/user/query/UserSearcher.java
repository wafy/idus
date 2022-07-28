package com.homework.idus.core.user.query;

import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.core.user.command.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserSearcher {

    private final UserSearcherRepository searcherRepository;

    /**
     * 사용자 이름으로 조회해 리턴합니다.
     * @param user 조회요청
     * @return 조회된 결과
     */
    @Transactional(readOnly = true)
    public Optional<User> findByName(UserLoginDescription user) {
      User savedUser =searcherRepository.findByName(user.getName())
              .orElseThrow(() -> new UserNotFountException("사용자 정보를 찾을 수 없습니다."));

      if (!savedUser.isMatchesPassword(user.getPassword())) throw new UserNotFountException("사용자 정보를 찾을 수 없습니다.");
      return Optional.of(savedUser);
    }

    /**
     * 사용자고유번호로 조회해 리턴합니다.
     * @param userNo 사용자고유번호
     * @return 조회된 결과
     */
    @Transactional(readOnly = true)
    public User findByUserNo(Long userNo) {
        return searcherRepository.findByUserNo(userNo)
                .orElseThrow(() -> new UserNotFountException(String.format("사용자 정보를 찾을 수 없습니다." +
                        "요청하신 사용자번호 [%d]", userNo)));
    }
}
