package com.nicico.cost.framework.aop.impl;

import com.nicico.cost.framework.anotations.Unauthorized;
import com.nicico.cost.framework.aop.UnauthorizedService;
import com.nicico.cost.framework.enums.authorize.HttpRequestType;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.service.exception.ServiceException;
import com.nicico.cost.framework.utility.RequestUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.nicico.cost.framework.enums.exception.ExceptionEnum.NOT_IMPLEMENT;

@Component
@RequiredArgsConstructor
public class UnauthorizedImpl implements UnauthorizedService {
    private final ApplicationException<ServiceException> applicationException;
    private final RequestUtility applicationRequest;

    @Override
    public void unauthorized(Unauthorized unauthorized) {
        HttpRequestType[] httpRequestTypes = unauthorized.types();
        if (httpRequestTypes.length > 0)
            checkHttpMethod(httpRequestTypes);
        if (Boolean.FALSE.equals(unauthorized.urls()[0].equals("")))
            checkUrls(unauthorized.urls());
    }


    private void checkHttpMethod(HttpRequestType[] httpRequestTypes) {
        for (HttpRequestType httpRequestType : httpRequestTypes) {
            check(httpRequestType, HttpMethod.POST);
            check(httpRequestType, HttpMethod.GET);
            check(httpRequestType, HttpMethod.DELETE);
            check(httpRequestType, HttpMethod.PUT);
            check(httpRequestType, HttpMethod.OPTIONS);
            if (Boolean.TRUE.equals(httpRequestType.key().equalsIgnoreCase(HttpRequestType.ALL.name())))
                throw applicationException.createApplicationException(NOT_IMPLEMENT, HttpStatus.NOT_IMPLEMENTED);
        }
    }

    private void check(HttpRequestType httpRequestType, HttpMethod httpMethod) {
        if (Boolean.TRUE.equals(httpRequestType.key().equalsIgnoreCase(httpMethod.name())) &&
                Boolean.TRUE.equals(applicationRequest.getMethod().equalsIgnoreCase(httpMethod.name())))
            throw applicationException.createApplicationException(NOT_IMPLEMENT, HttpStatus.NOT_IMPLEMENTED);
    }

    private void checkUrls(String[] urls) {
        for (String url : urls) {
            if (Boolean.TRUE.equals(applicationRequest.getRequestURI().equalsIgnoreCase(url)))
                throw applicationException.createApplicationException(NOT_IMPLEMENT, HttpStatus.NOT_IMPLEMENTED);
        }
    }
}
