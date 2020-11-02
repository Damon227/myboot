package com.example.myboot2.config;

import com.example.myboot2.interceptor.FileTypeInterceptor;
import com.example.myboot2.interceptor.SomeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class MyWebMvcConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    private FileTypeInterceptor fileTypeInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        SomeInterceptor si = new SomeInterceptor();
        registry.addInterceptor(si)
                .addPathPatterns("/**")
                .excludePathPatterns("/test/second");

        registry.addInterceptor(fileTypeInterceptor);
    }
}
