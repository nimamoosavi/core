package com.nicico.cost.framework.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CrossCutting {

    private final WarningService warningService;

    @Pointcut("@annotation(com.nicico.cost.framework.anotations.Warnings)")
    public void warnings() {
        // Do Nothing ,Aop Running
    }

    @AfterReturning(pointcut = "warnings()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        warningService.warnings(joinPoint, result);
    }


}
