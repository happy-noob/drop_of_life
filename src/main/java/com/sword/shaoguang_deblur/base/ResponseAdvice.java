package com.sword.shaoguang_deblur.base;/*
 *    Create By Yelson Li on 2021/4/20.
 *
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        //ture:拦截，false:放行
        // 标注了@CosmoController，且类及方法上都没有标注@IgnoreCosmoResult的方法才进行包装
        return methodParameter.getDeclaringClass().isAnnotationPresent(CosmoController.class)
                && !methodParameter.getDeclaringClass().isAnnotationPresent(IgnoreCosmoResult.class)
                && !methodParameter.getMethod().isAnnotationPresent(IgnoreCosmoResult.class);
    }


    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object o,
                                  MethodParameter methodParameter,
                                  MediaType mediaType,
                                  Class<? extends HttpMessageConverter<?>> aClass,
                                  ServerHttpRequest serverHttpRequest,
                                  ServerHttpResponse serverHttpResponse) {
        ObjectMapper mapper = new ObjectMapper();

        if(o instanceof ResultVO) {
            return o;
        }

        if(Objects.isNull(o)) {
            return ResultVO.success();
        }

        if(o instanceof String) {
            return mapper.writeValueAsString(ResultVO.success(o));
        }

        return ResultVO.success(o);
    }


}
