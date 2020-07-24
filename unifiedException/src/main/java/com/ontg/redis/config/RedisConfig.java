package com.ontg.redis.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontg.redis.Exception.RedisCustomExecption;
import io.lettuce.core.cluster.ClusterClientOptions;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

/**
 * https://blog.csdn.net/yangyangye/article/details/102553614
 */
@Configuration
@EnableCaching
public class RedisConfig extends CachingConfigurerSupport {


    private static final Logger log = LoggerFactory.getLogger(RedisConfig.class);
    @Value("${spring.redis.host}")
    private String hostName;
    /**
     * 配置lettuce连接池
     * @return
     */
    @Bean(name = "redisPoolCustom")
    @Primary
    @ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
    public GenericObjectPoolConfig redisPoolCustom() {
        return new GenericObjectPoolConfig<>();
    }

    /**
     * 配置 redis数据源
     * @return
     */
    @Bean("redisConfigCustom")
    @ConfigurationProperties(prefix = "spring.redis")
    public RedisStandaloneConfiguration redisConfigCustom() {
        return new RedisStandaloneConfiguration();
    }

    @Bean("connectionFactory")
    public LettuceConnectionFactory factory(GenericObjectPoolConfig redisPoolCustom, RedisStandaloneConfiguration redisConfigCustom) {
        ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder()
                .autoReconnect(true)//false自动重连关闭
                .build();

        redisConfigCustom.setHostName(hostName);//默认匹配拿不到hoseName 在这里设置一下
        LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(redisPoolCustom)
                .commandTimeout(Duration.ofSeconds(10))//todo 设置超时时间
                .clientOptions(clusterClientOptions).build();
        return new LettuceConnectionFactory(redisConfigCustom, clientConfiguration);
    }
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        // 设置自动key的生成规则，配置spring boot的注解，进行方法级别的缓存
        // 使用：进行分割，可以很多显示出层级关系 // 这里其实就是new了一个KeyGenerator对象，
        // 只是这是lambda表达式的写法，我感觉很好用，大家感兴趣可以去了解下
        //
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append(":");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(":" + String.valueOf(obj));
            }
            String rsToUse = String.valueOf(sb);
            log.info("自动生成Redis Key -> [{}]", rsToUse);
            return rsToUse;
        };
    }

    /**
     * 管理缓存
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).build();
        return redisCacheManager;
    }

    //防止redis入库序列化乱码的问题
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) { //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        //解决使用jackson发生UnrecognizedPropertyException异常
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(om);
        // 配置redisTemplate
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer); // key序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer); // value序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        // Hash key序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        // Hash value序列化
        redisTemplate.afterPropertiesSet();

//        BoundValueOperations<Object, Object> test = redisTemplate.boundValueOps("test");
//        test.set("test");
//        System.out.println(test.get());
        return redisTemplate;
    }

//
//    @Configuration
//    @EnableCaching
//    public class AppCacheErrorHandler implements CacheErrorHandler {
//
//        @Override
//        public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
//            System.out.println("11111111111");
//            RedisErrorException(e, o);
//        }
//
//        @Override
//        public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
//            System.out.println("11111111111");
//            RedisErrorException(e, o);
//        }
//
//        @Override
//        public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
//            System.out.println("11111111111");
//            RedisErrorException(e, o);
//        }
//
//        @Override
//        public void handleCacheClearError(RuntimeException e, Cache cache) {
//            System.out.println("11111111111");
//            RedisErrorException(e, null);
//        }
//    }

//    @Override
//    public CacheErrorHandler errorHandler() {
//        return new RedisConfig.AppCacheErrorHandler();
//    }

    /**
       * redis数据操作异常处理 这里的处理：在日志中打印出错误信息，但是放行
       * 保证redis服务器出现连接等问题的时候不影响程序的正常运行，使得能够出问题时不用缓存
       *
       * @return
       */
//  @Bean
//  @Override
//  public CacheErrorHandler errorHandler() {
//
//      log.info("redis 异常处理");
//    CacheErrorHandler cacheErrorHandler = new CacheErrorHandler() {
//
//      @Override
//      public void handleCachePutError(RuntimeException exception, Cache cache,
//                      Object key, Object value) {
//        RedisErrorException(exception, key);
//      }
//
//      @Override
//      public void handleCacheGetError(RuntimeException exception, Cache cache,
//                      Object key) {
//        RedisErrorException(exception, key);
//      }
//
//      @Override
//      public void handleCacheEvictError(RuntimeException exception, Cache cache,
//                       Object key) {
//        RedisErrorException(exception, key);
//      }
//
//      @Override
//      public void handleCacheClearError(RuntimeException exception, Cache cache) {
//        RedisErrorException(exception, null);
//      }
//    };
//    return cacheErrorHandler;
//  }

    protected void RedisErrorException(Exception exception, Object key) {
        log.error("redis异常：key=[{}]", key, exception);
        throw new RedisCustomExecption("success");
    }
}
