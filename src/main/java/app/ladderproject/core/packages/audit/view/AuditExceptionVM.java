package app.ladderproject.core.packages.audit.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditExceptionVM {
    private String excClazz;
    private String excMethod;
    private Integer excLine;
    private String excMessage;
    private String excCode;
}
