package com.webold.framework.service.exception.impl;

import com.webold.framework.domain.dto.Notification;
import com.webold.framework.enums.Status;
import com.webold.framework.service.exception.ApplicationException;
import com.webold.framework.service.exception.ServiceException;
import com.webold.framework.utility.ResourceUtility;
import com.webold.framework.utility.view.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationExceptionImpl implements ApplicationException<ServiceException> {

    @Autowired
    ResourceUtility applicationResource;


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
            throw ServiceException.builder().exceptionCode(environmentNotFoundCode)
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(expCode).exceptionMessage(expMessage).build();
    }


    public ServiceException createApplicationException(Message message) {
        try {
            expCode = applicationResource.getResourceText(message.key().concat(CODE));
            expMessage = applicationResource.getResourceText(message.key().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(environmentNotFoundCode)
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(expCode).exceptionMessage(expMessage).build();
    }

    public Notification createApplicationWarning(Message message) {
        try {
            expMessage = applicationResource.getResourceText(message.key().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(environmentNotFoundCode)
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
                throw ServiceException.builder().exceptionCode(environmentNotFoundCode)
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
            throw ServiceException.builder().exceptionCode(environmentNotFoundCode)
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(expCode).httpStatus(httpStatus).exceptionMessage(expMessage).build();
    }


    public ServiceException createApplicationException(Message message, HttpStatus httpStatus) {
        try {
            expCode = applicationResource.getResourceText(message.key().concat(CODE));
            expMessage = applicationResource.getResourceText(message.key().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(environmentNotFoundCode)
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(expCode)
                .httpStatus(httpStatus).exceptionMessage(expMessage).build();
    }

}
