package com.homework.idus.web.v1.user.login.query;

import com.homework.idus.core.user.query.UserLoginDescription;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


/**
 * 로그인 요청을 처리합니다.
 */
public class LoginRequest implements UserLoginDescription {

    @Size(min = 1, max = 20, message = "이름은 최대 20글자 까지 가능합니다")
    @NotBlank(message = "이름은 필수 값입니다")
    @Pattern(regexp = "^[a-zA-Zㄱ-힣]*$", message = "한글 영문 대소문자만 가능합니다")
    private String name;

    @Pattern(regexp = "^((?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{10,})$",
            message = "영문대소문자, 특수문자, 숫자 한개씩 포함 되어야 하며 최소 10자리 이상입니다")
    private String password;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
