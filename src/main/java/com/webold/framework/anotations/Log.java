package com.webold.framework.anotations;

import com.webold.framework.packages.audit.view.AuditFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Log {
    /**
     * @return the type of Log and Used For CrossCutting
     */
    AuditFactory.AuditType type() default AuditFactory.AuditType.ALL;
}
