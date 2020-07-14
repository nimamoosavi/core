package ir.webold.framework.domain.dto;


import io.swagger.annotations.ApiModelProperty;
import ir.webold.framework.enums.ResultStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class BaseDTO<T> {
    @ApiModelProperty(value = "${baseDTO.resultCode.sample}")
    private Integer resultCode;
    @ApiModelProperty(value = "${baseDTO.result.message.sample}")
    private String resultMessage;
    private ResultStatus status;
    private T data;
}
