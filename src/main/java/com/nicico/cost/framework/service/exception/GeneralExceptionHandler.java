package com.nicico.cost.framework.service.exception;


import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.Status;
import com.nicico.cost.framework.mapper.jackson.Mapper;
import com.nicico.cost.framework.utility.response.impl.ApplicationResourceImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


public abstract class GeneralExceptionHandler {

    @Autowired
    private ApplicationResourceImpl applicationResource;
    @Autowired
    private Mapper mapper;

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<BaseDTO<Object>> serviceException(ServiceException e) {
        BaseDTO<Object> baseDTO = BaseDTO.builder().code(e.exceptionCode).message(e.exceptionMessage).status(Status.ERROR).build();
        return new ResponseEntity<>(baseDTO, e.httpStatus != null ? e.httpStatus : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseDTO<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        BaseDTO<String> baseDTO = BaseDTO.<String>builder().code(
                applicationResource.getResourceText("application.message.validationError.code"))
                .message(String.format(
                        applicationResource.getResourceText("application.message.validationError.message"),
                        (error.getField())
                )).status(Status.ERROR).build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<BaseDTO<String>> handleConstraintViolationException(ConstraintViolationException e) {
        ConstraintViolation<?> error = e.getConstraintViolations().iterator().next();
        String fieldName = ((PathImpl) error.getPropertyPath()).getLeafNode().getName();
        BaseDTO<String> baseDTO = BaseDTO.<String>builder().code(
                applicationResource.getResourceText("application.message.validationError.code"))
                .message(String.format(
                        applicationResource.getResourceText("application.message.validationError.message"),
                        (fieldName)
                )).status(Status.ERROR).build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<BaseDTO<String>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        BaseDTO<String> baseDTO = BaseDTO.<String>builder().code(
                applicationResource.getResourceText("JSON_CAST_ERROR.code"))
                .message(applicationResource.getResourceText("JSON_CAST_ERROR.message"))
                .status(Status.ERROR).build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseDTO<String>> handleException(Exception e) {
        BaseDTO<String> baseDTO = BaseDTO.<String>builder().code(
                applicationResource.getResourceText("application.message.unknownError.code"))
                .message(applicationResource.getResourceText("application.message.unknownError.message")).status(Status.ERROR).build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

}
