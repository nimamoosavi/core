package ir.webold.framework.enums.audit;

import lombok.Getter;

@Getter
public enum AuditType {
    BEFORE,
    AFTER_RETURNING,
    AFTER_TROWING,
    AROUND
}
