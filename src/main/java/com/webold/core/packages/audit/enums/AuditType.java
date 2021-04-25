package com.webold.core.packages.audit.enums;

import lombok.Getter;

@Getter
public enum AuditType {
    BEFORE,
    AFTER_RETURNING,
    AFTER_TROWING,
    AROUND
}
