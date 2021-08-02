package app.ladderproject.core.aop.impl;

import app.ladderproject.core.enums.exception.ExceptionEnum;
import app.ladderproject.core.service.exception.ApplicationException;
import app.ladderproject.core.service.exception.ServiceException;
import app.ladderproject.core.anotations.Unauthorized;
import app.ladderproject.core.aop.UnauthorizedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UnauthorizedImpl implements UnauthorizedService {
    private final ApplicationException<ServiceException> applicationException;

    @Override
    public void unauthorized(Unauthorized unauthorized) {
        throw applicationException.createApplicationException(ExceptionEnum.NOT_IMPLEMENT, HttpStatus.NOT_IMPLEMENTED);
    }
}
