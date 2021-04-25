package com.webold.core.packages.audit.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditVM {
    /**
     * header is the reference of log
     */
    private AuditHeader header;
    /**
     * data is java Object that we can log it
     */
    private Object detail;
    /**
     * auditLocation is the list of locations that we can log it
     */
    private AuditLocation auditLocation;
}
