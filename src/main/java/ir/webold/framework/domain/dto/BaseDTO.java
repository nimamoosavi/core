package ir.webold.framework.domain.dto;


import ir.webold.framework.enums.ResultStatus;
import ir.webold.framework.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Optional;

import static ir.webold.framework.service.GeneralService.successCustomResponse;

@Builder
@Getter
@AllArgsConstructor
public class BaseDTO<T> {
    private Integer resultCode;
    private String resultMessage;
    private ResultStatus status;
    private T data;


    public boolean isPresent() {
        if (Boolean.TRUE.equals(data == null))
            return false;
        else
            return true;
    }

    public BaseDTO<T> orElseThrow(ServiceException e) {
        if (!isPresent())
            throw e;
        else
            return this;
    }

    public BaseDTO<T> orElse(T t) {
        return successCustomResponse(t);
    }

    public Optional<T> optional() {
        return Optional.of(data);
    }
}
