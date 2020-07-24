//package com.ontg.demo.redis.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.ontg.demo.redis.Exception.RedisCustomExecption;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.cache.annotation.CachingConfigurerSupport;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.cache.interceptor.CacheErrorHandler;
//import org.springframework.cache.interceptor.KeyGenerator;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.redis.cache.RedisCacheManager;
//import org.springframework.data.redis.connection.RedisConfiguration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
//import org.springframework.data.redis.core.BoundValueOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializer;
//import org.springframework.data.redis.serializer.StringRedisSerializer;
//
//import java.time.Duration;
//
///**
// * https://blog.csdn.net/yangyangye/article/details/102553614
// */
//@Configuration
//@EnableCaching
//public class RedisConfig2 extends CachingConfigurerSupport {
//
//
//    @Bean("connectionFactory")
//    public  LettuceConnectionFactory createLettuceConnectionFactory(){
//        return createLettuceConnectionFactory(0,"127.0.0.1",6379,null,8,0,200,2000L,1000L,8000L);
//    }
//    /**
//     * 自定义LettuceConnectionFactory,这一步的作用就是返回根据你传入参数而配置的
//     * LettuceConnectionFactory，
//     * 也可以说是LettuceConnectionFactory的原理了，
//     * 后面我会详细讲解的,各位同学也可先自己看看源码
//
//     这里定义的方法 createLettuceConnectionFactory，方便快速使用
//     */
//    private  LettuceConnectionFactory createLettuceConnectionFactory(
//            int dbIndex, String hostName, int port, String password,
//            int maxIdle,int minIdle,int maxActive,
//            Long maxWait, Long timeOut,Long shutdownTimeOut){
//
//        //redis配置
//        RedisConfiguration redisConfiguration = new
//                RedisStandaloneConfiguration(hostName,port);
//        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(dbIndex);
//        ((RedisStandaloneConfiguration) redisConfiguration).setPassword(password);
//
//        //连接池配置
//        GenericObjectPoolConfig genericObjectPoolConfig =
//                new GenericObjectPoolConfig();
//        genericObjectPoolConfig.setMaxIdle(maxIdle);
//        genericObjectPoolConfig.setMinIdle(minIdle);
//        genericObjectPoolConfig.setMaxTotal(maxActive);
//        genericObjectPoolConfig.setMaxWaitMillis(maxWait);
//
//        //redis客户端配置
//        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
//                builder =  LettucePoolingClientConfiguration.builder().
//                commandTimeout(Duration.ofMillis(timeOut));
//
//        builder.shutdownTimeout(Duration.ofMillis(shutdownTimeOut));
//        builder.poolConfig(genericObjectPoolConfig);
//        LettuceClientConfiguration lettuceClientConfiguration = builder.build();
//
//        //根据配置和客户端配置创建连接
//        LettuceConnectionFactory lettuceConnectionFactory = new
//                LettuceConnectionFactory(redisConfiguration,lettuceClientConfiguration);
//        lettuceConnectionFactory .afterPropertiesSet();
//
//        return lettuceConnectionFactory;
//    }
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) { //设置序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        //解决使用jackson发生UnrecognizedPropertyException异常
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // 配置redisTemplate
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
//        redisTemplate.setConnectionFactory(connectionFactory);
//        RedisSerializer stringSerializer = new StringRedisSerializer();
//        redisTemplate.setKeySerializer(stringSerializer); // key序列化
//        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value序列化
//        redisTemplate.setHashKeySerializer(stringSerializer);
//        // Hash key序列化
//        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
//        // Hash value序列化
//        redisTemplate.afterPropertiesSet();
//
//        BoundValueOperations<Object, Object> test = redisTemplate.boundValueOps("test");
//        test.set("test");
//        System.out.println(test.get());
//        return redisTemplate;}
//}
