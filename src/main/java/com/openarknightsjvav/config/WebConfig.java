package com.openarknightsjvav.config;

import com.openarknightsjvav.interceptor.PrintLogInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: WebConfig
 * Package: com.openarknightsjvav.config
 * Description:
 *
 * @author Raincc
 * @Create 2023/11/7 13:10
 * @Version 1.0
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private PrintLogInterceptor printLogInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(printLogInterceptor).addPathPatterns("/**").excludePathPatterns("/error", "/event");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}

