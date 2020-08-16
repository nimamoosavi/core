package ir.webold.framework.domain.viewmodel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditException {
    private String excClazz;
    private String excMethod;
    private Integer excLine;
    private String excMessage;
    private Integer excCode;
}
