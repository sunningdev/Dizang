package com.dizang.config;

import com.dizang.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public R<?> handleException(Exception e) {
        log.error("系统异常: {}", e.getMessage(), e);
        return R.fail("系统繁忙，请稍后再试");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public R<?> handleIllegalArgument(IllegalArgumentException e) {
        return R.fail(400, e.getMessage());
    }
}