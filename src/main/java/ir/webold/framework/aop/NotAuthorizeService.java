package ir.webold.framework.aop;

import ir.webold.framework.enums.exception.ExceptionEnum;
import ir.webold.framework.exception.ServiceException;
import ir.webold.framework.utility.ApplicationException;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class NotAuthorizeService {

    private final AuditService auditService;
    private final ApplicationException applicationException;

    @Pointcut("@annotation(ir.webold.framework.anotations.NotAuthorize)")
    public void notAuthorize() {
        // Do Nothing ,Aop Running
    }

    @Before("notAuthorize()")
    public void notAuthorize(JoinPoint joinPoint){
        ServiceException exception = this.applicationException.createApplicationException(ExceptionEnum.ACCESS_DENIED, HttpStatus.UNAUTHORIZED);
        auditService.logAfterThrowing(joinPoint,exception);
        throw exception;
    }
}
