package com.sword.shaoguang_deblur.config;/*
 *    Create By Yelson Li on 2021/4/19.
 *
 */
import com.sword.shaoguang_deblur.filter.ShaoGuangInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Autowired
    private ShaoGuangInterceptor shaoGuangInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // todo 平台getCode接口，是否需要拦截器返回新token
        registry.addInterceptor(shaoGuangInterceptor).addPathPatterns("/**");
    }
}
