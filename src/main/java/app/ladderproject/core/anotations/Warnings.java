package app.ladderproject.core.anotations;

import app.ladderproject.core.enums.warn.Warning;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Warnings {
    Warning[] warnings();
}
