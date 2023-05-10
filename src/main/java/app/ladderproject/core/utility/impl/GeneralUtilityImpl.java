package app.ladderproject.core.utility.impl;

import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.service.exception.ApplicationException;
import app.ladderproject.core.service.exception.ServiceException;
import app.ladderproject.core.utility.GeneralUtility;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

import static app.ladderproject.core.enums.exception.Exceptions.INTERNAL_SERVER;
import static app.ladderproject.core.service.GeneralResponse.successCustomResponse;

@Component
@RequiredArgsConstructor
class GeneralUtilityImpl implements GeneralUtility {

    private final ApplicationException<ServiceException> applicationException;


    public <T extends Annotation> T findClassAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass) {
        return (T) joinPoint.getSignature().getDeclaringType().getDeclaredAnnotation(tClass);
    }

    @Override
    public <T extends Annotation> T findMethodAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod().getAnnotation(tClass);
    }

    public BaseDTO<String> hash(String text) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            String hashResult = Base64.getEncoder().encodeToString(hash);
            return successCustomResponse(hashResult);
        } catch (Exception e) {
            throw applicationException.createApplicationException(INTERNAL_SERVER, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
