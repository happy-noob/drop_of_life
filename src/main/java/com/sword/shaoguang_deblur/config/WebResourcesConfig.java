package com.sword.shaoguang_deblur.config;/*
 *    Create By Yelson Li on 2021/7/29.
 *
 */

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

//@Configuration
public class WebResourcesConfig extends WebMvcConfigurationSupport {
    @Override
    protected void addResourceHandlers(
            ResourceHandlerRegistry registry
    ) {
        registry.addResourceHandler("/static/**").
                addResourceLocations("classpath:/static/");
    }
}
