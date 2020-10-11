package ir.webold.framework.exception;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class ServiceException extends RuntimeException {
    Integer exceptionCode;
    String exceptionMessage;
    HttpStatus httpStatus;
}
