package app.ladderproject.core.aop.impl;

import app.ladderproject.core.service.exception.ApplicationException;
import app.ladderproject.core.service.exception.ServiceException;
import app.ladderproject.core.anotations.Unauthorized;
import app.ladderproject.core.aop.UnauthorizedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static app.ladderproject.core.enums.exception.Exceptions.NOT_IMPLEMENT;

@Component
@RequiredArgsConstructor
public class UnauthorizedImpl implements UnauthorizedService {
    private final ApplicationException<ServiceException> applicationException;

    @Override
    public void unauthorized(Unauthorized unauthorized) {
        throw applicationException.createApplicationException(NOT_IMPLEMENT, HttpStatus.NOT_IMPLEMENTED);
    }
}
