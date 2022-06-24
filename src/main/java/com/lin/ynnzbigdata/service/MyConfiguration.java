package com.lin.ynnzbigdata.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: linbaobao
 * @Date: 2022/4/28
 * @explain: 解决前后端交互跨域问题
 */

@Configuration
public class MyConfiguration {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:8080","null")
                        .allowedMethods("GET","POST","PUT","OPTIONS","DELETE")
                        .allowCredentials(true)
                        .maxAge(4000);
            }
        };
    }
}
