package ir.webold.framework.aop;

import ir.webold.framework.domain.viewmodel.AuditException;
import ir.webold.framework.domain.viewmodel.AuditReqVM;
import ir.webold.framework.enums.audit.AuditType;
import ir.webold.framework.enums.audit.LogLevel;
import ir.webold.framework.exception.ServiceException;
import ir.webold.framework.utility.ApplicationLogger;
import ir.webold.framework.utility.ApplicationRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class AuditService {


    @Value("${spring.application.name}")
    private String microserviceName;

    @Autowired
    ApplicationRequest applicationRequest;
    @Autowired
    ApplicationLogger applicationLogger;


    public static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
    }

    @Pointcut("within(@ir.webold.framework.anotations.Log *)")
    public void log() {
    }


    @AfterReturning(pointcut = "service() || log()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        List<Object> input = Arrays.asList(joinPoint.getArgs());
        String rrn = applicationRequest.getHeader("rrn");
        String authorization = applicationRequest.getHeader("Authorization");
        AuditReqVM auditReqVM = AuditReqVM.builder().input(input)
                .method(methodName)
                .clazz(clazz)
                .microServiceName(microserviceName)
                .result(result)
                .rrn(rrn)
                .token(authorization)
                .type(AuditType.AFTERRETURNING)
                .level(LogLevel.INFO.name())
                .time(new SimpleDateFormat(DATE_PATTERN).format(new Timestamp(System.currentTimeMillis())))
                .build();
        applicationLogger.log(auditReqVM, LogLevel.INFO);

    }

    @Before("service() || log()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        List<Object> input = Arrays.asList(joinPoint.getArgs());
        String rrn = applicationRequest.getHeader("rrn");
        String authorization = applicationRequest.getHeader("Authorization");
        AuditReqVM auditReqVM = AuditReqVM.builder().input(input)
                .method(methodName)
                .clazz(clazz)
                .microServiceName(microserviceName)
                .rrn(rrn)
                .token(authorization)
                .type(AuditType.BEFORE)
                .level(LogLevel.INFO.name())
                .time(new SimpleDateFormat(DATE_PATTERN).format(new Timestamp(System.currentTimeMillis())))
                .build();
        applicationLogger.log(auditReqVM, LogLevel.INFO);
    }

    @AfterThrowing(pointcut = "service() || log()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        List<Object> input = Arrays.asList(joinPoint.getArgs());
        String rrn = applicationRequest.getHeader("rrn");
        String authorization = applicationRequest.getHeader("Authorization");
        AuditException auditException;
        if (exception instanceof ServiceException){
            auditException = AuditException.builder().excClazz(exception.getStackTrace()[0].getClassName())
                    .excMethod(exception.getStackTrace()[0].getMethodName())
                    .excLine(exception.getStackTrace()[0].getLineNumber())
                    .excMessage(((ServiceException) exception).getExceptionMessage()).excCode(((ServiceException) exception).getExceptionCode()).build();
        }else {
            auditException = AuditException.builder().excClazz(exception.getStackTrace()[0].getClassName())
                    .excMethod(exception.getStackTrace()[0].getMethodName())
                    .excLine(exception.getStackTrace()[0].getLineNumber())
                    .excMessage(exception.getMessage()).build();
        }
        AuditReqVM auditReqVM = AuditReqVM.builder().input(input)
                .method(methodName)
                .clazz(clazz)
                .microServiceName(microserviceName)
                .rrn(rrn)
                .token(authorization)
                .type(AuditType.AFTERTROWING)
                .level(LogLevel.ERROR.name())
                .exception(auditException)
                .time(new SimpleDateFormat(DATE_PATTERN).format(new Timestamp(System.currentTimeMillis())))
                .build();
        applicationLogger.log(auditReqVM, LogLevel.ERROR);
    }


}
