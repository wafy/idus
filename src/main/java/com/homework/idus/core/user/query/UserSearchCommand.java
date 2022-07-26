package com.homework.idus.core.user.query;

import com.homework.idus.core.user.command.User;
import lombok.Getter;

/**
 * 사용자 조회 검색 요청을 담당합니다.
 */
@Getter
public class UserSearchCommand {
    private String name;
    private String email;

    public UserSearchCommand(UserSearchDescription description) {

    }
}
