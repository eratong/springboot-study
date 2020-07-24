package com.ontg.redis.config;

import com.ontg.redis.pojo.TestBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public TestBean testBean(){
        return new TestBean();
    }
}
