package com.nicico.cost.framework.utility.impl;


import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.Status;
import com.nicico.cost.framework.enums.exception.ExceptionEnum;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.service.exception.ServiceException;
import com.nicico.cost.framework.utility.ResourceUtility;
import com.nicico.cost.framework.utility.view.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.nicico.cost.framework.service.GeneralResponse.successCustomResponse;


@Component
public class ResourceUtilityImpl implements ResourceUtility {

    @Autowired
    public Environment environment;
    @Autowired
    public ApplicationException<ServiceException> applicationException;


    private static String successCode;
    private static String successText;

    @Value("${SUCCESS.code}")
    public void setSuccessCode(String successCode) {
        ResourceUtilityImpl.successCode = successCode;
    }

    @Value("${SUCCESS.message}")
    public void setSuccessText(String successText) {
        ResourceUtilityImpl.successText = successText;
    }

    public BaseDTO<String> getResourceData(String resourceText) {
        String text = environment.getProperty(resourceText);
        return successCustomResponse(text);
    }

    public String getResourceText(String resourceText) {
        return environment.getProperty(resourceText);
    }

    @Override
    public String getResourceText(Message message) {
        return environment.getProperty(message.key());
    }

    public BaseDTO<String> getResourcesData(String resourceText) {
        String text;
        try {
            text = environment.getProperty(resourceText);
        } catch (Exception e) {
            throw applicationException.createApplicationException(ExceptionEnum.ENVIRONMENT_NOT_FOUND, HttpStatus.BAD_GATEWAY);
        }
        return successCustomResponse(text);
    }

    public static BaseDTO<Object> successResource() {
        return BaseDTO.builder().code(successCode).message(successText).status(Status.SUCCESS).build();
    }


}