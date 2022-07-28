package com.homework.idus.core.user.command;

import com.homework.idus.core.exception.ExistException;
import com.homework.idus.core.exception.UserExistException;
import com.homework.idus.core.user.query.UserSearcherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserCreator {
    private final UserRepository userRepository;
    private final UserSearcherRepository userSearcherRepository;

    /**
     * 사용자 등록을 담당합니다.
     * @param user 사용자정보
     * @return 등록된 결과
     */
    public User create(User user) {
        if (userSearcherRepository.countByName(user.getName()) > 0) {
            throw new UserExistException("이미 가입된 사용자입니다.");
        }

        if (userSearcherRepository.countByNickname(user.getNickname()) > 0) {
            throw new ExistException("이미 등록된 닉네입니다.");
        }

        if (userSearcherRepository.countByMobilePhoneNo(user.getMobilePhoneNo()) > 0) {
            throw new ExistException("이미 등록된 전화번호입니다.");
        }

        if (userSearcherRepository.countByEmail(user.getEmail()) > 0) {
            throw new ExistException("이미 등록된 이메일입니다.");
        }

        return userRepository.save(user);
    }

}
