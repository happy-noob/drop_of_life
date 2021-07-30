package com.sword.shaoguang_deblur.config;/*
 *    Create By Yelson Li on 2021/7/29.
 *
 */
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebAppConfiguration implements WebMvcConfigurer{
    //定制资源映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //意思是：url中读取到/upload时，就会自动将/upload解析成D:/idea/java_workspace/image/upload
        registry.addResourceHandler("/predeblur/**", "/predeblur/Deblurring/**").addResourceLocations("file:D:/predeblur/", "file:D:/predeblur/Deblurring/");
        /**
         * Linux系统
         * registry.addResourceHandler("/upload/**").addResourceLocations("file:/home/image/upload/");
         */
    }
}
