package com.ontg.redis.controller;

import io.lettuce.core.RedisCommandTimeoutException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/testa")
    public void s() {
        throw new RedisCommandTimeoutException();
    }
}
