package com.homework.idus.web.v1.user.login;

import com.homework.idus.core.user.query.UserLoginDescription;


/**
 * 로그인 요청을 처리합니다.
 */
public class LoginRequest implements UserLoginDescription {

    private String name;
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
