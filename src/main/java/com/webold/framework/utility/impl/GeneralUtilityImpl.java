package com.webold.framework.utility.impl;

import com.webold.framework.utility.GeneralUtility;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

@Component
public class GeneralUtilityImpl implements GeneralUtility {

    public <T extends Annotation> T findClassAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass) {
        return (T) joinPoint.getSignature().getDeclaringType().getDeclaredAnnotation(tClass);
    }

    @Override
    public <T extends Annotation> T findMethodAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(tClass);
    }
}
