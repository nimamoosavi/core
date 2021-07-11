package com.nicico.cost.framework.aop.impl;

import com.nicico.cost.framework.anotations.Unauthorized;
import com.nicico.cost.framework.aop.UnauthorizedService;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.service.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.nicico.cost.framework.enums.exception.ExceptionEnum.ACCESS_DENIED;

@Component
@RequiredArgsConstructor
public class UnauthorizedImpl implements UnauthorizedService {
    private final ApplicationException<ServiceException> applicationException;

    @Override
    public void unauthorized(Unauthorized unauthorized) {
        throw applicationException.createApplicationException(ACCESS_DENIED, HttpStatus.UNAUTHORIZED);
    }
}
