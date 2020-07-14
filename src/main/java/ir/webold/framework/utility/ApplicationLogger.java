package ir.webold.framework.utility;


import ir.webold.framework.aop.AuditService;
import ir.webold.framework.enums.LogLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLogger {
    @Autowired
    AuditService auditService;

    public void log(LogLevel level, String clazz, String method, String message) {
        auditService.aroundLog(level, clazz, method, message);
    }

}
