package com.homework.idus.core.exception;

/**
 * 사용자를 찾을 수 없으면 던집니다.
 */
public class UserNotFountException extends RuntimeException {
    public UserNotFountException(final String message) {
        super(message);
    }
}
