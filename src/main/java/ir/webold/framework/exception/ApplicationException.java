package ir.webold.framework.exception;

import ir.webold.framework.enums.exception.ExceptionEnum;
import ir.webold.framework.utility.ApplicationResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ApplicationException {

    @Autowired
    ApplicationResource applicationResource;
    @Value("${ENVIREMENTNOTFOUNDE.code}")
    private String environmentNotFoundCode;

    @Value("${ENVIREMENTNOTFOUNDE.message}")
    private String environmentNotFoundMessage;

    public static final String CODE = ".code";
    public static final String MESSAGE = ".message";

    String code;
    String text;


    public ServiceException createApplicationException(String exceptionKey) {
        try {
            code = applicationResource.getResourceText(exceptionKey.concat(CODE));
            text = applicationResource.getResourceText(exceptionKey.concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(code)).exceptionMessage(text).build();
    }


    public ServiceException createApplicationException(ExceptionEnum exceptionEnum) {
        try {
            code = applicationResource.getResourceText(exceptionEnum.name().concat(CODE));
            text = applicationResource.getResourceText(exceptionEnum.name().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(code)).exceptionMessage(text).build();
    }

    public ServiceException createApplicationException(String exceptionKey, HttpStatus httpStatus) {
        try {
            code = applicationResource.getResourceText(exceptionKey.concat(CODE));
            text = applicationResource.getResourceText(exceptionKey.concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(code)).httpStatus(httpStatus).exceptionMessage(text).build();
    }


    public ServiceException createApplicationException(ExceptionEnum exceptionEnum, HttpStatus httpStatus) {
        try {
            code = applicationResource.getResourceText(exceptionEnum.name().concat(CODE));
            text = applicationResource.getResourceText(exceptionEnum.name().concat(MESSAGE));
        } catch (Exception e) {
            throw ServiceException.builder().exceptionCode(Integer.valueOf(environmentNotFoundCode))
                    .exceptionMessage(environmentNotFoundMessage)
                    .httpStatus(HttpStatus.BAD_GATEWAY).build();
        }
        return ServiceException.builder().exceptionCode(Integer.valueOf(code))
                .httpStatus(httpStatus).exceptionMessage(text).build();
    }
}
