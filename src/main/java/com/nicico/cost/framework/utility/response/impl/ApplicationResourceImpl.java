package com.nicico.cost.framework.utility.response.impl;


import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.Status;
import com.nicico.cost.framework.enums.exception.ExceptionEnum;
import com.nicico.cost.framework.service.exception.ApplicationException;
import com.nicico.cost.framework.utility.response.Message;
import com.nicico.cost.framework.utility.response.ApplicationResource;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.nicico.cost.framework.service.GeneralResponse.successCustomResponse;


@Component
@RequiredArgsConstructor
public class ApplicationResourceImpl implements ApplicationResource {

    private final Environment environment;
    private final ApplicationException applicationException;


    private static Integer successCode;
    private static String successText;

    @Value("${SUCCESS.code}")
    public void setSuccessCode(Integer successCode) {
        ApplicationResourceImpl.successCode = successCode;
    }

    @Value("${SUCCESS.message}")
    public void setSuccessText(String successText) {
        ApplicationResourceImpl.successText = successText;
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
        return BaseDTO.builder().resultCode(successCode).resultMessage(successText).status(Status.SUCCESS).build();
    }


}
