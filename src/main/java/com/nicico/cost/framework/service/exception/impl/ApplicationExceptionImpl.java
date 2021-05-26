package com.nicico.cost.framework.service.exception.impl;

import com.nicico.cost.framework.domain.dto.Notification;
import com.nicico.cost.framework.enums.Status;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.service.exception.ServiceException;
import com.nicico.cost.framework.utility.response.Message;
import com.nicico.cost.framework.utility.response.impl.ApplicationResourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationExceptionImpl implements ApplicationException<ServiceException> {

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


    public ServiceException createApplicationException(Message message) {
        try {
            expCode = applicationResource.getResourceText(message.key().concat(CODE));
            expMessage = applicationResource.getResourceText(message.key().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(expCode)).exceptionMessage(expMessage).build();
    }

    public Notification createApplicationWarning(Message message) {
        try {
            expMessage = applicationResource.getResourceText(message.key().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return Notification.builder().notify(expMessage).status(Status.WARN).build();
    }

    public List<Notification> createApplicationWarning(Message[] messages) {
        List<Notification> notifications = new ArrayList<>();
        for (Message warningObj : messages) {
            try {
                expMessage = applicationResource.getResourceText(warningObj.key().concat(MESSAGE));
                Notification notification = Notification.builder().notify(expMessage).status(Status.WARN).build();
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


    public ServiceException createApplicationException(Message message, HttpStatus httpStatus) {
        try {
            expCode = applicationResource.getResourceText(message.key().concat(CODE));
            expMessage = applicationResource.getResourceText(message.key().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(expCode))
                .httpStatus(httpStatus).exceptionMessage(expMessage).build();
    }

}
