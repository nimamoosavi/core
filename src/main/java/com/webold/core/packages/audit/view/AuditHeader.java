package com.webold.core.packages.audit.view;


import com.webold.core.packages.audit.enums.Status;
import lombok.Builder;
import lombok.Data;
import org.springframework.boot.logging.LogLevel;

import java.sql.Timestamp;

@Data
@Builder
public class AuditHeader {
    private String method;
    private String aClass;
    private String appName;
    private String correlationId;
    private String instanceId;
    private Timestamp time;
    private String token;
    private String uri;
    private Status status;
    private LogLevel level;
}
