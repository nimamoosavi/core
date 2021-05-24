package com.nicico.cost.framework.service.exception;

import com.nicico.cost.framework.domain.dto.Notification;
import com.nicico.cost.framework.enums.ResultStatus;
import com.nicico.cost.framework.enums.exception.ExceptionEnum;
import com.nicico.cost.framework.enums.warn.Warning;
import com.nicico.cost.framework.utility.impl.ApplicationResourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationException {

    @Autowired
    ApplicationResourceImpl applicationResource;


    @Value("${ENVIRONMENT_NOT_FOUND.code}")
    private String environmentNotFoundCode;

    @Value("${ENVIRONMENT_NOT_FOUND.message}")
    private String environmentNotFoundMessage;

    private static final String CODE = ".code";
    private static final String MESSAGE = ".message";

    private String expCode;
    private String expMessage;

    public ServiceException createApplicationException(String exceptionKey) {
        try {
            expCode = applicationResource.getResourceText(exceptionKey.concat(CODE));
            expMessage = applicationResource.getResourceText(exceptionKey.concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(expCode)).exceptionMessage(expMessage).build();
    }


    public ServiceException createApplicationException(ExceptionEnum exceptionEnum) {
        try {
            expCode = applicationResource.getResourceText(exceptionEnum.name().concat(CODE));
            expMessage = applicationResource.getResourceText(exceptionEnum.name().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(expCode)).exceptionMessage(expMessage).build();
    }

    public Notification createApplicationWarning(Warning warning) {
        try {
            expMessage = applicationResource.getResourceText(warning.name().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return Notification.builder().notify(expMessage).status(ResultStatus.WARN).build();
    }

    public List<Notification> createApplicationWarning(Warning[] warning) {
        List<Notification> notifications = new ArrayList<>();
        for (Warning warningObj : warning) {
            try {
                expMessage = applicationResource.getResourceText(warningObj.name().concat(MESSAGE));
                Notification notification = Notification.builder().notify(expMessage).status(ResultStatus.WARN).build();
                notifications.add(notification);
            } catch (Exception e) {
                throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                        .exceptionMessage(environmentNotFoundMessage)
                        .httpStatus(HttpStatus.BAD_GATEWAY).build();
            }
        }
        return notifications;
    }

    public ServiceException createApplicationException(String exceptionKey, HttpStatus httpStatus) {
        try {
            expCode = applicationResource.getResourceText(exceptionKey.concat(CODE));
            expMessage = applicationResource.getResourceText(exceptionKey.concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(expCode)).httpStatus(httpStatus).exceptionMessage(expMessage).build();
    }


    public ServiceException createApplicationException(ExceptionEnum exceptionEnum, HttpStatus httpStatus) {
        try {
            expCode = applicationResource.getResourceText(exceptionEnum.name().concat(CODE));
            expMessage = applicationResource.getResourceText(exceptionEnum.name().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(expCode))
                .httpStatus(httpStatus).exceptionMessage(expMessage).build();
    }
}
