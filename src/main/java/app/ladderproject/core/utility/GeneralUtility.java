package app.ladderproject.core.utility;

import app.ladderproject.core.domain.dto.BaseDTO;
import org.aspectj.lang.JoinPoint;

import java.lang.annotation.Annotation;

public interface GeneralUtility {

    <T extends Annotation> T findClassAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass);

    <T extends Annotation> T findMethodAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass);

    /**
     * @param text input text for Hash
     * @apiNote this method used default SHA-256 for hash your text
     * @return hash of text
     */
    BaseDTO<String> hash(String text);
}
