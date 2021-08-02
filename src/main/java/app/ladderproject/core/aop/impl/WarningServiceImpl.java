package app.ladderproject.core.aop.impl;

import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.domain.dto.Notification;
import app.ladderproject.core.enums.warn.Warning;
import app.ladderproject.core.service.exception.ApplicationException;
import app.ladderproject.core.service.exception.ServiceException;
import app.ladderproject.core.anotations.Warnings;
import app.ladderproject.core.aop.WarningService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

@Component
@RequiredArgsConstructor
public class WarningServiceImpl implements WarningService {

    private final ApplicationException<ServiceException> applicationException;


    @Override
    @SuppressWarnings("unchecked")
    public void warnings(JoinPoint joinPoint, Object result) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            Warnings annotation = method.getAnnotation(Warnings.class);
            Warning[] warnings = annotation.warnings();
            if (warnings.length > 0) {
                List<Notification> warning = applicationException.createApplicationWarning(warnings);
                ResponseEntity<BaseDTO<Object>> responseEntity = (ResponseEntity<BaseDTO<Object>>) result;
                BaseDTO<Object> body = responseEntity.getBody();
                if (body != null) {
                    List<Notification> list = body.getNotifies();
                    if (list != null)
                        list.addAll(warning);
                    else{
                        body.setNotifies(warning);
                    }
                }
            }
        } catch (Exception e) {
            // Method Not Throw Any Exception
        }
    }
}
