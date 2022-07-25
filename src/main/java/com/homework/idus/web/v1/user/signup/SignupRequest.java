package com.homework.idus.web.v1.user.signup;

import com.homework.idus.core.user.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원가입 요청 DTO 객체.
 */
@Getter
@NoArgsConstructor
public class SignupRequest {

    private String name;
    private String nickname;
    private String password;
    private String mobilePhoneNo;
    private String email;
    private Gender gender;

}
