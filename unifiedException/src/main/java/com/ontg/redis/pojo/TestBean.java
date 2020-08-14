package com.ontg.redis.pojo;

import javax.annotation.PreDestroy;

public class TestBean {

    @PreDestroy
    public void preDestory() {
        System.out.println("TestBean destoried---------");
    }


    private String name;

    public static TestBean getTestBean(String name) {
        TestBean testBean = new TestBean();
        testBean.setName(name);
        return testBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
