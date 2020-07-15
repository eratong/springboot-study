package com.ontg.demo.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.sql.DataSource;

/**
 * 加载外部配置
 */
@Slf4j
@ConditionalOnBean(name = "distributed") //	当给定的在bean存在时,则实例化当前Bean
@Configuration
public class ModeConfig implements InitializingBean {

    @Autowired
    private Distributed distributed;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("含有分布式配置，执行分布式配置");
        log.info("External configuration exit,start distributed configuration");
    }
    /**
     * 外部数据库配置
     */
    @Configuration
    @MapperScan(basePackages = "com.counect.einvoice.cubeservice.mapper.cubeMapper", sqlSessionFactoryRef = "sqlSessionFactory2")
    class DBConfig2{
        //einvoice
        @Bean(name = "dataSource2")
        public DataSource getDateSource() {
            //外部配置文件没有配einvoice吧
            String url = "jdbc:mysql://"+distributed.loadNodeConfig.getHost()+":"+distributed.loadNodeConfig.getPort()+"/"+"einvoice"+"?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=GMT%2B8";
            String username = distributed.loadNodeConfig.getUsername();
            String password = distributed.loadNodeConfig.getPassword();
            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
            return dataSourceBuilder.url(url).username(username).password(password).build();
        }

        @Bean(name = "sqlSessionFactory2")
        public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource datasource)
                throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(datasource);
            bean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/cubeMapper/*.xml"));
            return bean.getObject();
        }
        @Bean("sqlSessionTemplate2")
        public SqlSessionTemplate sqlSessionTemplate2(@Qualifier("sqlSessionFactory2") SqlSessionFactory sessionfactory) {
            return new SqlSessionTemplate(sessionfactory);
        }
    }
    @Configuration
    @MapperScan(basePackages = "com.counect.einvoice.cubeservice.mapper.filePaserNodeMapper", sqlSessionFactoryRef = "sqlSessionFactory3")
    class DBConfig3{
        //filePaser四个表
        @Bean(name = "dataSource3")
        public DataSource getDateSource3() {
            String url = distributed.loadNodeConfig.getUrl();
            String username = distributed.loadNodeConfig.getUsername();
            String password = distributed.loadNodeConfig.getPassword();
            DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
            return dataSourceBuilder.url(url).username(username).password(password).build();
        }
        @Bean(name = "sqlSessionFactory3")
        public SqlSessionFactory sqlSessionFactory3(@Qualifier("dataSource3") DataSource datasource)
                throws Exception {
            SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
            bean.setDataSource(datasource);
            bean.setMapperLocations(
                    new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/filePaserNodeMapper/*.xml"));
            return bean.getObject();
        }
        @Bean("sqlSessionTemplate3")
        public SqlSessionTemplate sqlSessionTemplate3(@Qualifier("sqlSessionFactory3") SqlSessionFactory sessionfactory) {
            return new SqlSessionTemplate(sessionfactory);
        }
    }
    /**
     * 注册中心redis
     */
    @Configuration
    class RedisFileConfig{

        @Bean(name = "redisPoolRegister")
        public GenericObjectPoolConfig redisPoolRegister() {
            GenericObjectPoolConfig<Object> objectGenericObjectPoolConfig = new GenericObjectPoolConfig<>();
            objectGenericObjectPoolConfig.setMaxWaitMillis(-1);
            objectGenericObjectPoolConfig.setMaxIdle(8);
            objectGenericObjectPoolConfig.setMinIdle(0);
            objectGenericObjectPoolConfig.setMaxTotal(200);
            return objectGenericObjectPoolConfig;
        }
        @Bean(name = "redisConfigForRegister")
        public RedisStandaloneConfiguration redisConfigForRegister() {
            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
            redisStandaloneConfiguration.setHostName(distributed.loadNodeConfig.getRedisHost());
            redisStandaloneConfiguration.setPort(Integer.parseInt(distributed.loadNodeConfig.getRedisPort()));
            redisStandaloneConfiguration.setDatabase(Integer.parseInt(distributed.loadNodeConfig.getRedisDataBase()));
            redisStandaloneConfiguration.setPassword(distributed.loadNodeConfig.getRedisPassword());
            return redisStandaloneConfiguration;
        }

        @Primary
        @Bean(name = "factoryForRegister")
        public LettuceConnectionFactory factoryForRegister(@Qualifier("redisPoolRegister") GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfigForRegister ) {
            redisConfigForRegister.setHostName(distributed.loadNodeConfig.getRedisHost());//默认匹配拿不到hoseName 在这里设置一下
            LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
            return new LettuceConnectionFactory(redisConfigForRegister, clientConfiguration);
        }

        @Bean("redisTemplateForRegister")
        public RedisTemplate<String, Object> redisTemplateForRegister(@Qualifier("factoryForRegister") RedisConnectionFactory factoryForRegister) {

            RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
            template.setConnectionFactory(factoryForRegister);
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
}
