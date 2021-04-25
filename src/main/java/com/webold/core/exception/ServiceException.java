package com.webold.core.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ServiceException extends RuntimeException {
    public final Integer exceptionCode;
    public final String exceptionMessage;
    public final HttpStatus httpStatus;
}
