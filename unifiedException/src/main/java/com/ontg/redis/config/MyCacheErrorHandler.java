package com.ontg.redis.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.CacheInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCache;

@Configuration
public class MyCacheErrorHandler {

    private static final Logger log = LoggerFactory.getLogger(MyCacheErrorHandler.class);

    @Bean
    CacheInterceptor createCacheInterceptor(CacheInterceptor cacheInterceptor) {
        cacheInterceptor.setErrorHandler(errorHandler());
        return cacheInterceptor;
    }


    public CacheErrorHandler errorHandler() {
        System.out.println("errorHandler start ----");
        return new CacheErrorHandler() {
            @Override
            public void handleCacheGetError(RuntimeException exception, Cache cache, Object key) {
                System.out.println("errorHandler start 1----");

                if (cache instanceof RedisCache) {
                    log.error("cahce get error -> " + exception.getMessage());
                } else {
                    throw exception;
                }
            }

            @Override
            public void handleCachePutError(RuntimeException exception, Cache cache, Object key, Object value) {
                System.out.println("errorHandler start 2----");

                if (cache instanceof RedisCache) {
                    log.error("cahce get Put -> " + exception.getMessage());
                } else {
                    throw exception;
                }
            }

            @Override
            public void handleCacheEvictError(RuntimeException exception, Cache cache, Object key) {
                if (cache instanceof RedisCache) {
                    System.out.println("errorHandler start 3----");

                    log.error("cahce get Evict -> " + exception.getMessage());
                } else {
                    throw exception;
                }
            }

            @Override
            public void handleCacheClearError(RuntimeException exception, Cache cache) {
                System.out.println("errorHandler start 4----");

                if (cache instanceof RedisCache) {
                    log.error("cahce get Clear -> " + exception.getMessage());
                } else {
                    throw exception;
                }
            }
        };
    }
}
