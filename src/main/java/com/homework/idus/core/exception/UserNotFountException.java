package com.homework.idus.core.exception;

import com.homework.idus.config.handler.BusinessException;

/**
 * 사용자를 찾을 수 없으면 던집니다.
 */
public class UserNotFountException extends BusinessException {
    public UserNotFountException(final String message) {
        super(message);
    }
}
