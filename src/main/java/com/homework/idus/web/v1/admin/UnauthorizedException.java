package com.homework.idus.web.v1.admin;

import com.homework.idus.config.handler.BusinessException;

/**
 * 인증토큰이 없으면 에러를 던집니다.
 */
public class UnauthorizedException extends BusinessException {

    public UnauthorizedException(final String message) {
        super(message);
    }
}
