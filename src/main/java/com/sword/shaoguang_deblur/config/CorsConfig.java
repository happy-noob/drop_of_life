package com.sword.shaoguang_deblur.config;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")

//                        "http://localhost:8020",
//                        "https://192.168.0.122:8020",
//                        "https://127.0.0.1:8020")
//                .allowCredentials(true)
                .allowedHeaders("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .maxAge(3600L);
    }
}
