package com.ontg.demo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnResource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Configuration(proxyBeanMethods = false)
@ConditionalOnProperty(name = "properties.node.config")
@ConditionalOnResource(resources = "${properties.node.config}")
public class Distributed{

    @Autowired
    public LoadNodeConfig loadNodeConfig;

    @Data
    @Component
    @PropertySource("${properties.node.config}")
    public class LoadNodeConfig {
        //node.code用来和注册中心比较是否一致
        @Value("${node.code}")
        private String nodeCode;
        //数据库
        @Value("${node.db.username}")
        private String username ;
        @Value("${node.db.password}")
        private String password ;
        @Value("${node.db.host}")
        private String host ;
        @Value("${node.db.port}")
        private String port ;
        @Value("${node.das.db.name}")
        private String name ;

        private String url;

        public String getUrl() {
            StringBuilder str=new StringBuilder();
            str.append("jdbc:mysql://").append(host).append(":").append(port).append("/").append(name).append("?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&autoReconnect=true&serverTimezone=GMT%2B8");
            return str.toString();
        }
        //redis
        @Value("${master.redis.host}")
        private String redisHost;

        @Value("${master.redis.port}")
        private String redisPort;

        @Value("${master.redis.database}")
        private String redisDataBase;

        @Value("${master.redis.password}")
        private String redisPassword;
    }

    @Override
    public String toString() {
        return "Distributed{" +
                "loadNodeConfig=" + loadNodeConfig +
                '}';
    }
}
