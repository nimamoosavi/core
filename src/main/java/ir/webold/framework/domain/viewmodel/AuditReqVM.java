package ir.webold.framework.domain.viewmodel;

import ir.webold.framework.domain.dto.BaseDTO;
import ir.webold.framework.enums.AuditType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class AuditReqVM {
    private String time;
    private String level;
    private String token;
    private String rrn;
    private AuditType type;
    private List<Object> input;
    private Object result;
    private AuditException exception;
    private String microServiceName;
    private String method;
    private String clazz;
}
