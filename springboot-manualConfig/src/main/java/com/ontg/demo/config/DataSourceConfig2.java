//package com.counect.einvoice.cubeservice.config;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
///**
// * 加载yml里数据库配置 einvoice
// */
//@ConditionalOnMissingBean(name = "distributed")
//@Configuration
//@MapperScan(basePackages = "com.counect.einvoice.cubeservice.mapper.cubeMapper", sqlSessionFactoryRef = "sqlSessionFactory2")
//public class DataSourceConfig2 {
//
//    @Value("${distributed.db2.url}")
//    private String url ;
//
//    @Bean(name = "dataSource2")
//    @Primary
//    @ConfigurationProperties(prefix = "distributed.db2")
//    public DataSource getDateSource1() {
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        return dataSourceBuilder.url(url).build();
//    }
//
//    @Bean(name = "sqlSessionFactory2")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory2(@Qualifier("dataSource2") DataSource datasource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(datasource);
//        bean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/cubeMapper/*.xml"));
//        return bean.getObject();
//    }
//    @Bean("sqlSessionTemplate2")
//    @Primary
//    public SqlSessionTemplate sqlSessionTemplate1(
//            @Qualifier("sqlSessionFactory2") SqlSessionFactory sessionfactory) {
//        return new SqlSessionTemplate(sessionfactory);
//    }
//
//}
