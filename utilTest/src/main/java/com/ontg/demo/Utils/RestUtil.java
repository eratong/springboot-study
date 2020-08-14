package com.ontg.demo.Utils;

import org.springframework.web.client.RestTemplate;


public abstract class RestUtil {
    private final static RestTemplate restTemplate = new RestTemplate();

    public static <T> T doPost(String url, Object requests, Class<T> clazz) {
        return restTemplate.postForObject(url, requests, clazz);
    }

    public static <T> T doGet(String url, Object requests, Class<T> clazz) {
        return restTemplate.postForObject(url, requests, clazz);
    }
}
