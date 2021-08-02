package app.ladderproject.core.utility;

import org.aspectj.lang.JoinPoint;

import java.lang.annotation.Annotation;

public interface GeneralUtility {

    <T extends Annotation> T findClassAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass);

    <T extends Annotation> T findMethodAnnotationInAOP(JoinPoint joinPoint, Class<T> tClass);
}
