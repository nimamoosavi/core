package com.nicico.cost.framework.anotations;

import com.nicico.cost.framework.enums.authorize.UnauthorizedType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Unauthorized {
    UnauthorizedType type() default UnauthorizedType.ALL;
}