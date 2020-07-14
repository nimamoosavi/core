package ir.webold.framework.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.domain.viewmodel.AuditException;
import ir.webold.framework.domain.viewmodel.AuditReqVM;
import ir.webold.framework.enums.AuditType;
import ir.webold.framework.enums.LogLevel;
import ir.webold.framework.utility.ApplicationKafka;
import ir.webold.framework.utility.ApplicationRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import static ir.webold.framework.service.GeneralService.successCustomResponse;

@Aspect
@Component
public class AuditService {

    private static final Logger APP_LOG = LoggerFactory.getLogger("APP_LOG");

    @Value("${spring.application.name}")
    private String microserviceName;

    @Value("${kafka.audit.topic}")
    private String logTopic;

    @Autowired
    ApplicationRequest applicationRequest;

    @Autowired
    ApplicationKafka applicationKafka;

    @Autowired
    ObjectMapper objectMapper;

    public static final String DATE_PATTERN = "yyyy/MM/dd HH:mm:ss";

    @Pointcut("within(@org.springframework.stereotype.Service *)")
    public void service() {
    }

    @Pointcut("within(@ir.webold.framework.anotations.Log *)")
    public void log() {
    }


    @AfterReturning(pointcut = "service() || log()",returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        List<Object> input = Arrays.asList(joinPoint.getArgs());
        String rrn = applicationRequest.getHeader("rrn");
        AuditReqVM auditReqVM = AuditReqVM.builder().input(input)
                .method(methodName)
                .clazz(clazz)
                .microServiceName(microserviceName)
                .result(result)
                .rrn(rrn)
                .type(AuditType.AFTERRETURNING)
                .level(LogLevel.INFO.name())
                .time(new SimpleDateFormat(DATE_PATTERN).format(new Timestamp(System.currentTimeMillis())))
                .build();
        String json = convertToJson(auditReqVM);
        applicationKafka.sendMessage(logTopic,json);
        //APP_LOG.info(json);
    }

    @Before("service() || log()")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        List<Object> input = Arrays.asList(joinPoint.getArgs());
        String rrn = applicationRequest.getHeader("rrn");
        AuditReqVM auditReqVM = AuditReqVM.builder().input(input)
                .method(methodName)
                .clazz(clazz)
                .microServiceName(microserviceName)
                .rrn(rrn)
                .type(AuditType.BEFORE)
                .level(LogLevel.INFO.name())
                .time(new SimpleDateFormat(DATE_PATTERN).format(new Timestamp(System.currentTimeMillis())))
                .build();
        String json = convertToJson(auditReqVM);
        applicationKafka.sendMessage(logTopic,json);
        //APP_LOG.info(json);
    }

    @AfterThrowing(pointcut = "service() || log()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        String clazz = joinPoint.getSignature().getDeclaringTypeName();
        List<Object> input = Arrays.asList(joinPoint.getArgs());
        String rrn = applicationRequest.getHeader("rrn");
        AuditException auditException = AuditException.builder().excClazz(exception.getStackTrace()[0].getClassName())
                .excMethod(exception.getStackTrace()[0].getMethodName())
                .excLine(exception.getStackTrace()[0].getLineNumber())
                .excMessage(exception.getMessage()).build();
        AuditReqVM auditReqVM = AuditReqVM.builder().input(input)
                .method(methodName)
                .clazz(clazz)
                .microServiceName(microserviceName)
                .rrn(rrn)
                .type(AuditType.AFTERTROWING)
                .level(LogLevel.ERROR.name())
                .exception(auditException)
                .time(new SimpleDateFormat(DATE_PATTERN).format(new Timestamp(System.currentTimeMillis())))
                .build();
        String json = convertToJson(auditReqVM);
        applicationKafka.sendMessage(logTopic,json);
        //APP_LOG.error(json);
    }

    @Async
    public void aroundLog(LogLevel level, String clazz, String method, String message) {
        String rrn = applicationRequest.getHeader("rrn");
        AuditReqVM auditReqVM = AuditReqVM.builder()
                .method(method)
                .clazz(clazz)
                .microServiceName(microserviceName)
                .rrn(rrn)
                .type(AuditType.AROUND)
                .level(level.name())
                .result(successCustomResponse(message))
                .time(new SimpleDateFormat(DATE_PATTERN).format(new Timestamp(System.currentTimeMillis())))
                .build();
        String json = convertToJson(auditReqVM);
        applicationKafka.sendMessage(logTopic,json);
        //APP_LOG.error(json);

    }


    public String convertToJson(Object o) {
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
