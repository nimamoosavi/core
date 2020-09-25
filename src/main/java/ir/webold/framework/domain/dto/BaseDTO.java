package ir.webold.framework.domain.dto;


import ir.webold.framework.enums.ResultStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class BaseDTO<T> extends OptionalDTO<T> {
    private Integer resultCode;
    private String resultMessage;
    private ResultStatus status;
    private T data;

    public BaseDTO(BaseDTO<T> baseDTO) {
        super(baseDTO);
    }
}
