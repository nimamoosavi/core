package ir.webold.framework.domain.dto;


import ir.webold.framework.enums.ResultStatus;
import ir.webold.framework.exception.ServiceException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

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

    public BaseDTO<T> orElseThrow(@NotNull ServiceException e) {
        if (!isPresent())
            throw e;
        else
            return this;
    }

    public BaseDTO<T> orElse(T t) {
        return successCustomResponse(t);
    }

    public BaseDTO<T> ifPresent(@NotNull Runnable action) {
        if (isPresent())
            action.run();
        return this;
    }

    public void orElseCallAndThrow(@NotNull ServiceException e,@NotNull Runnable action) {
        if (!isPresent()) {
            action.run();
            throw e;
        }
    }

    public BaseDTO<T> call(@NotNull Runnable action) {
        action.run();
        return this;
    }

    public boolean equal(T t) {
        if (Boolean.TRUE.equals(data.equals(t)))
            return true;
        else
            return false;
    }
}
