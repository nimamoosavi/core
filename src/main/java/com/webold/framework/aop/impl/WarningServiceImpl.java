package com.webold.framework.aop.impl;

import com.webold.framework.anotations.Warnings;
import com.webold.framework.aop.WarningService;
import com.webold.framework.domain.dto.BaseDTO;
import com.webold.framework.domain.dto.Notification;
import com.webold.framework.enums.warn.Warning;
import com.webold.framework.service.exception.ApplicationException;
import com.webold.framework.service.exception.ServiceException;
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
