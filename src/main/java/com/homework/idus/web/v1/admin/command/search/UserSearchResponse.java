package com.homework.idus.web.v1.admin.command.search;

import com.homework.idus.core.user.Gender;
import com.homework.idus.core.user.command.User;
import lombok.Getter;

/**
 * 사용자 정보 응답을 담당합니다.
 */
@Getter
public class UserSearchResponse {
    private String name;
    private String nickname;
    private String email;
    private String mobilePhoneNo;
    private Gender gender;

    public UserSearchResponse(User user) {
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.mobilePhoneNo = user.getMobilePhoneNo();
        this.gender = user.getGender();
    }
}
