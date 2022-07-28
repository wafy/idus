package com.homework.idus.config.handler;

import com.homework.idus.core.exception.ExistException;
import com.homework.idus.core.exception.UserExistException;
import com.homework.idus.core.exception.UserNotFountException;
import com.homework.idus.web.v1.admin.UnauthorizedException;
import com.homework.idus.web.v1.axiom.ApiResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.homework.idus")
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ApiResponseModel<?> UnauthorizedExceptionHandle(UnauthorizedException e) {
        return buildErrorResponse(e);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFountException.class)
    public ApiResponseModel<?> UserNotFountExceptionHandle(UserNotFountException e) {
        return buildErrorResponse(e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserExistException.class)
    public ApiResponseModel<?> UserExistExceptionHandle(UserExistException e) {
        return buildErrorResponse(e);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(ExistException.class)
    public ApiResponseModel<?> ExistExceptionHandle(ExistException e) {
        return buildErrorResponse(e);
    }


    public ApiResponseModel<?> buildErrorResponse(BusinessException ex) {
        return ApiResponseModel.builder()
                .status(false)
                .message(ex.getMessage())
                .data(null)
                .build();
    }
}
