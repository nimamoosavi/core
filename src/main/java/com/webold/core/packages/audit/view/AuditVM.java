package com.webold.core.packages.audit.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditVM {
    private AuditHeader header;
    private Object detail;
    private AuditLocation auditLocation;
}
