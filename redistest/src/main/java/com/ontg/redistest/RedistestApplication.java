package com.ontg.redistest;

import com.ontg.redistest.service.RedisReconnaction;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedistestApplication {

    public static void main(String[] args) {
        new RedisReconnaction().sn();
        SpringApplication.run(RedistestApplication.class, args);
    }

}
