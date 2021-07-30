package com.sword.shaoguang_deblur.base;/*
 *    Create By Yelson Li on 2021/4/20.
 *
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface IgnoreCosmoResult {
}
