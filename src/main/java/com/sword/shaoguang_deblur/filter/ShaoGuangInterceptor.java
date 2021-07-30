package com.sword.shaoguang_deblur.filter;/*
 *    Create By Yelson Li on 2021/7/27.
 *
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Slf4j
@Component
public class ShaoGuangInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
//        System.out.println("RemoteUser:" + request.getRemoteUser());
//        System.out.println("RemoteHost:" + request.getRemoteHost());
//        System.out.println("RequestURL:" + request.getRequestURL());
//        System.out.println("RemotePort:" + request.getRemotePort());
        //true则放行
        //不拦截跨域请求相关
        if("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        //如果方法上没有加@LoginRequired,无需登录，直接放行
        if (isLoginFree(handler)) {
            return true;
        }
        return true;
    }

    /**
     * 接口是否免登录
     * @param handler
     * @return
     */
    private boolean isLoginFree(Object handler) {
        //判断接口是否支持免登录
        if(handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            //AnnotationUtils是Spring提供的注解工具类
            LoginRequired loginRequiredAnnotation = AnnotationUtils.getAnnotation(method,LoginRequired.class);
            LoginRequired beanRequiredAnnotation = AnnotationUtils.getAnnotation(handlerMethod.getBeanType(),LoginRequired.class);
            //没有加@LoginRequired，不需要登录
            return loginRequiredAnnotation == null && beanRequiredAnnotation == null;
        }
        return true;
    }
}
