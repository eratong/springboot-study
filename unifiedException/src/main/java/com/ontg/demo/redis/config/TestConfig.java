package com.ontg.demo.redis.config;

import com.ontg.demo.redis.pojo.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }
}
