package com.homework.idus.core.exception;

/**
 * 이미 가입된 사용자이면 던집니다.
 */
public class UserExistException extends RuntimeException {
    public UserExistException(final String message) {
        super(message);
    }
}
