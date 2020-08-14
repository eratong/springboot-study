package com.counect.einvoice.cubeservice.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 加载yml里数据库配置
 * 如果外部注册中心redis不存在 就配置这个redis
 */
@Configuration
@ConditionalOnMissingBean(name = "distributed")
public class Redis2Config {
    @Value("${distributed.redis.host}")
    private String hostName;

    /**
     * 配置lettuce连接池
     *
     * @return
     */
    @Bean(name = "redisPoolRegister")
    @ConfigurationProperties(prefix = "distributed.redis.lettuce.pool")
    public GenericObjectPoolConfig redisPoolRegister() {
        return new GenericObjectPoolConfig<>();
    }

    /**
     * 配置 redis数据源
     *
     * @return
     */
    @Bean("redisConfigForRegister")
    @ConfigurationProperties(prefix = "distributed.redis")
    public RedisStandaloneConfiguration redisConfigForRegister() {
        return new RedisStandaloneConfiguration();
    }

    @Bean("factoryForRegister")
    @Primary
    public LettuceConnectionFactory factory(@Qualifier("redisPoolRegister") GenericObjectPoolConfig redisPoolRegister, @Qualifier("redisConfigForRegister") RedisStandaloneConfiguration redisConfigForRegister) {
        redisConfigForRegister.setHostName(hostName);//默认匹配拿不到hoseName 在这里设置一下
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPoolRegister).build();
        return new LettuceConnectionFactory(redisConfigForRegister, clientConfiguration);
    }

    @Bean("redisTemplateForRegister")
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("factoryForRegister") RedisConnectionFactory factory) {
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

    @Bean("stringRedisTemplateForRegister")
    public StringRedisTemplate stringRedisTemplateForRegister(@Qualifier("factoryForRegister") RedisConnectionFactory factoryForRegister) {
        return new StringRedisTemplate(factoryForRegister);
    }

}