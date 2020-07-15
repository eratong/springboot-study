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
// * 加载yml里数据库配置 filepaser其它表
// */
//@Configuration
//@MapperScan(basePackages = "com.counect.einvoice.cubeservice.mapper.filePaserMapper", sqlSessionFactoryRef = "sqlSessionFactory1")
//public class DataSourceConfig1 {
//
//    @Value("${distributed.db1.url}")
//    private String url ;
//
//    @Bean(name = "dataSource1")
//    @ConfigurationProperties(prefix = "distributed.db1")
//    public DataSource getDateSource1() {
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        return dataSourceBuilder.url(url).build();
//    }
//
//    @Bean(name = "sqlSessionFactory1")
//    @Primary
//    public SqlSessionFactory sqlSessionFactory1(@Qualifier("dataSource1") DataSource datasource)
//            throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(datasource);
//        bean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/filePaserMapper/*.xml"));
//        return bean.getObject();
//    }
//    @Bean("sqlSessionTemplate1")
//    @Primary
//    public SqlSessionTemplate sqlSessionTemplate1(
//            @Qualifier("sqlSessionFactory1") SqlSessionFactory sessionfactory) {
//        return new SqlSessionTemplate(sessionfactory);
//    }
//
//}
