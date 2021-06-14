package com.nicico.cost.framework.anotations;

import com.nicico.cost.framework.enums.authorize.HttpRequestType;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Inherited
public @interface Unauthorized {
    HttpRequestType [] types() default HttpRequestType.NONE;

    String[] urls() default "";
}
