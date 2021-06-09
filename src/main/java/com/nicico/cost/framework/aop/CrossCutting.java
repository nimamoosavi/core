package com.nicico.cost.framework.aop;

import com.nicico.cost.framework.anotations.Unauthorized;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@RequiredArgsConstructor
public class CrossCutting {

    private final WarningService warningService;
    private final UnauthorizedService unauthorizedService;

    @Pointcut("@annotation(com.nicico.cost.framework.anotations.Warnings)")
    public void warnings() {
        // Do Nothing ,Aop Running
    }

    @Pointcut("@annotation(com.nicico.cost.framework.anotations.Unauthorized)")
    public void unauthorized() {
        // Do Nothing ,Aop Running
    }

    @AfterReturning(pointcut = "warnings()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        warningService.warnings(joinPoint, result);
    }

    @Before("unauthorized()")
    public void logBefore(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Unauthorized annotation = method.getAnnotation(Unauthorized.class);
        unauthorizedService.unauthorized(annotation);
    }


}
