package com.homework.idus.web.v1.user.signup.command;


import com.homework.idus.core.user.Gender;
import com.homework.idus.core.user.command.User;
import lombok.Getter;

/**
 * 회원가입 후 응답 객체를 정의합니다.
 */
@Getter
public class SignupResponse {

    private String name;
    private String nickname;
    private String mobilePhoneNo;
    private String email;
    private Gender gender;

    public SignupResponse(User user) {
        this.name = user.getName();
        this.nickname = user.getNickname();
        this.mobilePhoneNo = user.getMobilePhoneNo();
        this.email = user.getEmail();
        this.gender = user.getGender();
    }
}
