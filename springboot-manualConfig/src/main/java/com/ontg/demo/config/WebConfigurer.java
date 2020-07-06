package com.counect.einvoice.cubeservice.config;

import com.counect.einvoice.cubeservice.Intercepter.EinvoiceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

/**
 * springboot拦截器配置
 */
@Configuration
@ConditionalOnBean(name = "distributed")//只有分布式情况下才拦截
public class WebConfigurer implements WebMvcConfigurer {

    private static List<String> ALLOWPATHS= Arrays.asList("/css/**","/document.html","/img/**","/js/**");

    @Autowired
    private EinvoiceInterceptor einvoiceInterceptor;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(einvoiceInterceptor).addPathPatterns("/**")
                .excludePathPatterns(ALLOWPATHS);
    }
}
