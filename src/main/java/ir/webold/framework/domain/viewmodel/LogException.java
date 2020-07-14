package ir.webold.framework.domain.viewmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogException {
    String className;
    String methodName;
    String responseCode;
    String responseMessage;
    Object inputObject;
    String exceptionClass;
    String exceptionMethod;
    Integer exceptionLine;
    String exceptionMessage;
}
