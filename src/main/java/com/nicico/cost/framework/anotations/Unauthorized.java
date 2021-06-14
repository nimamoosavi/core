package com.nicico.cost.framework.anotations;

import com.nicico.cost.framework.enums.authorize.HttpRequestType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Unauthorized {
    HttpRequestType [] types() default HttpRequestType.NONE;

    String[] urls() default "";
}
