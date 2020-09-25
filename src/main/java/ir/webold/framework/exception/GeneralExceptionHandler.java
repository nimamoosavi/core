package ir.webold.framework.exception;


import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.enums.ResultStatus;
import ir.webold.framework.utility.ApplicationResource;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


public class GeneralExceptionHandler {

    @Autowired
    private ApplicationResource applicationResource;

    @ExceptionHandler(ServiceException.class)
    protected ResponseEntity<BaseDTO<Object>> serviceException(ServiceException e) {
        BaseDTO<Object> baseDTO = BaseDTO.builder().resultCode(e.exceptionCode).resultMessage(e.exceptionMessage).status(ResultStatus.ERROR).build();
        return new ResponseEntity<>(baseDTO, e.httpStatus != null ? e.httpStatus : HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<BaseDTO<String>> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        FieldError error = e.getBindingResult().getFieldErrors().get(0);
        BaseDTO<String> baseDTO = BaseDTO.<String>builder().resultCode(
                Integer.valueOf(applicationResource.getResourceText("application.message.validationError.code")))
                .resultMessage(String.format(
                        applicationResource.getResourceText("application.message.validationError.text"),
                        (error.getField())
                )).status(ResultStatus.ERROR).build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<BaseDTO<String>> handleConstraintViolationException(ConstraintViolationException e) {
        ConstraintViolation<?> error = e.getConstraintViolations().iterator().next();
        String fieldName = ((PathImpl) error.getPropertyPath()).getLeafNode().getName();
        BaseDTO<String> baseDTO = BaseDTO.<String>builder().resultCode(
                Integer.valueOf(applicationResource.getResourceText("application.message.validationError.code")))
                .resultMessage(String.format(
                        applicationResource.getResourceText("application.message.validationError.text"),
                        (fieldName)
                )).status(ResultStatus.ERROR).build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<BaseDTO<String>> handleException(Exception e) {
        BaseDTO<String> baseDTO = BaseDTO.<String>builder().resultCode(
                Integer.valueOf(applicationResource.getResourceText("application.message.unknownError.code")))
                .resultMessage(e.getMessage()
                ).status(ResultStatus.ERROR).build();
        return new ResponseEntity<>(baseDTO, HttpStatus.BAD_REQUEST);
    }

}