package com.ontg.demo.service.impl;

import com.ontg.demo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

//https://www.jianshu.com/p/2c20096fa964
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Override
    public Boolean checkConnection()  {
        RedisConnectionFactory redisConnectionFactory = stringRedisTemplate.getConnectionFactory();
        RedisConnection redisConnection = redisConnectionFactory.getConnection();
        Boolean flag = redisConnection.isClosed();

        System.out.println(!flag);
        return !flag;
    }
}
