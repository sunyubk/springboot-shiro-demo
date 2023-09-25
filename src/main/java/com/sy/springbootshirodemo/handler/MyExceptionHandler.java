package com.sy.springbootshirodemo.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @ClassName MyExceptionHandler
 * @Description
 * @Author sunyu
 * @Date 2023/9/25 14:26
 * @Version 1.0
 **/
@Slf4j
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public String authorizationException(Exception e) {
        log.error("异常内容----------------{}", e.getMessage());
        return "403";
    }
}
