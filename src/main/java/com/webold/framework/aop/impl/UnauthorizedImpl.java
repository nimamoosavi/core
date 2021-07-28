package com.webold.framework.aop.impl;

import com.webold.framework.anotations.Unauthorized;
import com.webold.framework.aop.UnauthorizedService;
import com.webold.framework.service.exception.ApplicationException;
import com.webold.framework.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.webold.framework.enums.exception.ExceptionEnum.NOT_IMPLEMENT;

@Component
@RequiredArgsConstructor
public class UnauthorizedImpl implements UnauthorizedService {
    private final ApplicationException<ServiceException> applicationException;

    @Override
    public void unauthorized(Unauthorized unauthorized) {
        throw applicationException.createApplicationException(NOT_IMPLEMENT, HttpStatus.NOT_IMPLEMENTED);
    }
}
