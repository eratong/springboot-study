//package com.ontg.demo.config;
//
//import io.lettuce.core.resource.ClientResources;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.RedisConnectionFactory;
//import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
//import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
//
//import java.net.UnknownHostException;
//
//@Configuration
//public class LettuceConnectionConfiguration {
//
//
//    @Bean
//    @ConditionalOnMissingBean(RedisConnectionFactory.class)
//    public LettuceConnectionFactory redisConnectionFactory(ClientResources clientResources)
//            throws UnknownHostException {
//        LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(clientResources,
//                this.properties.getLettuce().getPool());
//        return createLettuceConnectionFactory(clientConfig);
//    }
//
//
//    private LettuceConnectionFactory createLettuceConnectionFactory(LettuceClientConfiguration clientConfiguration) {
//        if (getSentinelConfig() != null) {
//            return new LettuceConnectionFactory(getSentinelConfig(), clientConfiguration);
//        }
//        if (getClusterConfiguration() != null) {
//            return new LettuceConnectionFactory(getClusterConfiguration(), clientConfiguration);
//        }
//        return new LettuceConnectionFactory(getStandaloneConfig(), clientConfiguration);
//    }
//
//}
