package com.wzz.neko.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * NoToken
 * 具有此注解的接口不需要携带token访问
 * @author wzzfarewell
 * @date 2020/1/10
 **/
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoToken {
    boolean required() default true;
}