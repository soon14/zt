package com.chinaunicom.js.adapter.exception;

import com.chinaunicom.js.common.core.entity.vo.Result;
import com.chinaunicom.js.common.web.exception.DefaultGlobalExceptionHandlerAdvice;
import com.chinaunicom.log.LoggerHelp;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandlerAdvice extends DefaultGlobalExceptionHandlerAdvice {

    @ExceptionHandler(value = {DuplicateKeyException.class})
    public Result duplicateKeyException(DuplicateKeyException ex) {
        LoggerHelp.error("duplicate key:{}", ex.getMessage());
        return Result.fail("主键冲突");
    }
}