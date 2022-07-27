package com.homework.idus.config.handler;

public abstract class BusinessException extends RuntimeException {

    public BusinessException() {}

    public BusinessException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return getMessage();
    }

}
