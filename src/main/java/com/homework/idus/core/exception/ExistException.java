package com.homework.idus.core.exception;

import com.homework.idus.config.handler.BusinessException;

/**
 * 중복데이타가 있으면 던집니다.
 */
public class ExistException extends BusinessException {
    public ExistException(final String message) {
        super(message);
    }
}
