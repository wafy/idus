package com.homework.idus.web.v1.user.login.query;

import lombok.Getter;

/**
 * 로그인 응답을 처리합니다.
 */
@Getter
public class LoginResponse {
    private String accessToken;

    public LoginResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
