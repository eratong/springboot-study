package com.ontg.feigntest2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class Feigntest2Application {

    public static void main(String[] args) {
        SpringApplication.run(Feigntest2Application.class, args);
    }

}
