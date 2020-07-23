package com.ontg.demo.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class UnifiedMain {
    private  static ConfigurableApplicationContext run=null;
    public static void main(String[] args) {
        SpringApplication.run(UnifiedMain.class);
    }
}
