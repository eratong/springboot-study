package com.counect.einvoice.cubeservice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 加载yml里数据库配置
 */
@Configuration
public class RedisConfig {

    @Value("${distributed.redis.host}")
    private String hostName;

    /**
     * 配置lettuce连接池
     *
     * @return
     */
    @Bean(name = "redisPoolCustom")
    @Primary
    @ConfigurationProperties(prefix = "distributed.redis.lettuce.pool")
    public GenericObjectPoolConfig redisPoolCustom() {
        return new GenericObjectPoolConfig<>();
    }

    /**
     * 配置 redis数据源
     *
     * @return
     */
    @Bean("redisConfigCustom")
    @ConfigurationProperties(prefix = "distributed.redis")
    public RedisStandaloneConfiguration redisConfigCustom() {
        return new RedisStandaloneConfiguration();
    }

    /**
     * 配置第一个数据源的连接工厂
     * 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
     *
     * @param redisPoolCustom
     * @param redisConfigCustom
     * @return
     */
    @Bean("factoryLettuce")
    public LettuceConnectionFactory factory(@Qualifier("redisPoolCustom") GenericObjectPoolConfig redisPoolCustom, @Qualifier("redisConfigCustom") RedisStandaloneConfiguration redisConfigCustom) {
        redisConfigCustom.setHostName(hostName);//默认匹配拿不到hoseName 在这里设置一下
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPoolCustom).build();
        return new LettuceConnectionFactory(redisConfigCustom, clientConfiguration);
    }

    /**
     * 配置第一个数据源的RedisTemplate
     * 注意：这里指定使用名称=factory 的 RedisConnectionFactory
     * 并且标识第一个数据源是默认数据源 @Primary
     *
     * @param factory
     * @return
     */
    @Bean("redisTemplateCustom")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("factoryLettuce") RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        // key采用String的序列化方式
        template.setKeySerializer(stringRedisSerializer);
        // hash的key也采用String的序列化方式
        template.setHashKeySerializer(stringRedisSerializer);
        // value序列化方式采用jackson
        template.setValueSerializer(jackson2JsonRedisSerializer);
        // hash的value序列化方式采用jackson
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}

