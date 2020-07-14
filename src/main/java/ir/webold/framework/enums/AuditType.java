package ir.webold.framework.enums;

import lombok.Getter;

@Getter
public enum AuditType {
    BEFORE,
    AFTERRETURNING,
    AFTERTROWING,
    AROUND
}
