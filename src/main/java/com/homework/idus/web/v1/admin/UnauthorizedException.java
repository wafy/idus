package com.homework.idus.web.v1.admin;

/**
 * 인증토큰이 없으면 에러를 던집니다.
 */
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(final String message) {
        super(message);
    }
}
