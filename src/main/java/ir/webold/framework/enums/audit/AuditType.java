package ir.webold.framework.enums.audit;

import lombok.Getter;

@Getter
public enum AuditType {
    BEFORE,
    AFTERRETURNING,
    AFTERTROWING,
    AROUND
}
