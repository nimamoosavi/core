package com.nicico.cost.framework.aop;

import com.nicico.cost.framework.anotations.Unauthorized;
import com.nicico.cost.framework.utility.GeneralUtility;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CrossCutting {

    private final WarningService warningService;
    private final UnauthorizedService unauthorizedService;
    private final GeneralUtility generalUtility;

    @Pointcut("@annotation(com.nicico.cost.framework.anotations.Warnings)")
    public void warnings() {
        // Do Nothing ,Aop Running
    }

    @Pointcut("@annotation(com.nicico.cost.framework.anotations.Unauthorized)")
    public void unauthorized() {
        // Do Nothing ,Aop Running
    }

    @Pointcut("within(@com.nicico.cost.framework.anotations.Unauthorized *)")
    public void unauthorizedClass() {
        // Do Nothing ,Aop Running
    }

    @AfterReturning(pointcut = "warnings()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        warningService.warnings(joinPoint, result);
    }

    @Before("unauthorized() || unauthorizedClass()")
    public void logBefore(JoinPoint joinPoint) {
        Unauthorized classAnnotationInAOP = generalUtility.findClassAnnotationInAOP(joinPoint, Unauthorized.class);
        if (classAnnotationInAOP != null)
            unauthorizedService.unauthorized(classAnnotationInAOP);
        Unauthorized annotation = generalUtility.findMethodAnnotationInAOP(joinPoint, Unauthorized.class);
        if (annotation != null)
            unauthorizedService.unauthorized(annotation);
    }


}
