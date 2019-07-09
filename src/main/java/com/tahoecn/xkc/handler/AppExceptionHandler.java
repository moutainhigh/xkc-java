package com.tahoecn.xkc.handler;

import com.tahoecn.xkc.common.exception.AppUserLoginException;
import com.tahoecn.xkc.converter.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class AppExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(value = AppUserLoginException.class)
    public Result handleException(Exception e) {
        AppUserLoginException userLoginException = (AppUserLoginException) e;
        return Result.errormsg(userLoginException.getErrorCode(), userLoginException.getMessage());
    }
}
