package com.ontg.redis.Exception;

import io.lettuce.core.RedisCommandTimeoutException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(basePackages = {"com.ontg.redis.Task"})
public class RedisExceptionHandler {

    //声明要捕获的异常
    @ExceptionHandler(RedisCustomExecption.class)
    public String ExcepitonHandler(HttpServletRequest request, Exception e) {
        return "error";
    }

    @ExceptionHandler(RedisCommandTimeoutException.class)
    public String RedisCommandTimeoutExceptionHandler() {
        System.out.println("RedisCommandTimeoutExceptionHandler start ******************************");
        return "error";
    }

    @ExceptionHandler(RuntimeException.class)
    public String Redistest() {
        System.out.println("RuntimeException start ******************************");
        return "error";
    }
}
