package com.ontg.demo.redis.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RedisExceptionHandler {

	//声明要捕获的异常
	@ExceptionHandler(RedisCustomExecption.class)
	public String ExcepitonHandler(HttpServletRequest request, Exception e) {
	    return "error";
	}
}
