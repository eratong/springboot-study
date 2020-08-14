package com.ontg.redis.service;

import io.lettuce.core.RedisCommandTimeoutException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.stereotype.Service;

@Service
public class RedisTimeOutHandler {

    public void redisTimeOut(Exception e) {

        if (e.getMessage().toLowerCase().contains("redis")) {
            if (e instanceof QueryTimeoutException) {
                System.out.println("exit");
                System.exit(0);
            }
        }
        //todo 断连
//        RedisException
    }
}
