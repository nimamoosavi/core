package com.webold.core.utility;


import com.webold.core.enums.exception.ExceptionEnum;
import com.webold.core.domain.dto.BaseDTO;
import com.webold.core.enums.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static com.webold.core.service.GeneralService.successCustomResponse;


@Component
public class ApplicationResource {

    private final Environment environment;
    private final ApplicationException applicationException;

    @Autowired
    public ApplicationResource(Environment environment, ApplicationException applicationException) {
        this.environment = environment;
        this.applicationException = applicationException;
    }

    private static Integer successCode;
    private static String successText;

    @Value("${SUCCESS.code}")
    public void setSuccessCode(Integer successCode) {
        ApplicationResource.successCode = successCode;
    }

    @Value("${SUCCESS.message}")
    public void setSuccessText(String successText) {
        ApplicationResource.successText = successText;
    }

    public BaseDTO<String> getResourceData(String resourceText) {
        String text = environment.getProperty(resourceText);
        return successCustomResponse(text);
    }

    public String getResourceText(String resourceText) {
        return environment.getProperty(resourceText);
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
        return BaseDTO.builder().resultCode(successCode).resultMessage(successText).status(ResultStatus.SUCCESS).build();
    }


}
