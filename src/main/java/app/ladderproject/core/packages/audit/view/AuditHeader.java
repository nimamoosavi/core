package app.ladderproject.core.packages.audit.view;


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
    private AuditFactory.Status status;
    private LogLevel level;
    private AuditFactory.AuditType type;
}
