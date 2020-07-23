//package com.ontg.demo.redis.config;
//
//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.PropertyAccessor;
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
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
//@Configuration
//@EnableCaching
//public class RedisConfigP {
//
//    @Bean("redisConnectionFactory")
//    public  LettuceConnectionFactory createLettuceConnectionFactory(){
//
//        //redis配置
//        RedisConfiguration redisConfiguration = new
//                RedisStandaloneConfiguration("127.0.0.1",6379);
//        ((RedisStandaloneConfiguration) redisConfiguration).setDatabase(0);
////        ((RedisStandaloneConfiguration) redisConfiguration).setPassword();
//
//        //连接池配置
//        GenericObjectPoolConfig genericObjectPoolConfig =
//                new GenericObjectPoolConfig();
//        genericObjectPoolConfig.setMaxIdle(10);
//        genericObjectPoolConfig.setMinIdle(0);
////        genericObjectPoolConfig.setMaxTotal(200);
//        genericObjectPoolConfig.setMaxWaitMillis(2000);
//
//        //redis客户端配置
//        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder
//                builder =  LettucePoolingClientConfiguration.builder().
//                commandTimeout(Duration.ofMillis(2000));
//
////        builder.shutdownTimeout(Duration.ofMillis(8));
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
//
//    @Bean
//    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) { //设置序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        ObjectMapper om = new ObjectMapper();
//        //解决使用jackson发生UnrecognizedPropertyException异常
//        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//
//        jackson2JsonRedisSerializer.setObjectMapper(om);
//        // 配置redisTemplate
//        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory);
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
//        return redisTemplate;
//    }
//}
