package com.homework.idus.core.exception;

import com.homework.idus.config.handler.BusinessException;

/**
 * 이미 가입된 사용자이면 던집니다.
 */
public class UserExistException extends BusinessException {
    public UserExistException(final String message) {
        super(message);
    }
}
