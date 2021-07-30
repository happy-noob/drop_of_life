package com.sword.shaoguang_deblur.base;/*
 *    Create By Yelson Li on 2021/4/20.
 *
 */

import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@RestController
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CosmoController {
}
