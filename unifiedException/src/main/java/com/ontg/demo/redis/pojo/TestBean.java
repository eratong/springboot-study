package com.ontg.demo.redis.pojo;

import javax.annotation.PreDestroy;

public class TestBean {

    @PreDestroy
    public void preDestory(){
        System.out.println("TestBean destoried---------");
    }
}
