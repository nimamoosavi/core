package ir.webold.framework.exception;

import lombok.*;
import org.springframework.http.HttpStatus;


@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceException extends RuntimeException {
    Integer exceptionCode;
    String exceptionMessage;
    HttpStatus httpStatus;
}
