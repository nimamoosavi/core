package ir.webold.framework.utility;

import ir.webold.framework.enums.exception.ExceptionEnum;
import ir.webold.framework.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ApplicationException {

    ApplicationResource applicationResource;
    @Value("${ENVIRONMENT_NOT_FOUND.code}")
    private String environmentNotFoundCode;

    @Value("${ENVIRONMENT_NOT_FOUND.message}")
    private String environmentNotFoundMessage;

    private static final String CODE = ".code";
    private static final String MESSAGE = ".message";

    private String code;
    private String text;

    @Autowired
    public ApplicationException(ApplicationResource applicationResource) {
        this.applicationResource = applicationResource;
    }

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
