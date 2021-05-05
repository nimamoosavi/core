package com.nicico.cost.framework.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nicico.cost.framework.enums.ResultStatus;
import com.nicico.cost.framework.exception.ServiceException;
import com.nicico.cost.framework.service.GeneralResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Stream;

@Builder
@AllArgsConstructor
@Data
public class BaseDTO<T> {
    private Integer resultCode;
    private String resultMessage;
    private ResultStatus status;
    private T data;
    private List<NotificationDTO> notifies;


    @JsonIgnore
    public boolean isPresent() {
        return !Boolean.TRUE.equals(data == null);
    }

    public BaseDTO<T> orElseThrow(@NotNull ServiceException e) {
        if (!isPresent())
            throw e;
        else
            return this;
    }

    public BaseDTO<T> orElse(T r) {
        return GeneralResponse.successCustomResponse(r);
    }

    public BaseDTO<T> ifPresent(@NotNull Runnable action) {
        if (isPresent())
            action.run();
        return this;
    }

    public void orElseCallAndThrow(@NotNull ServiceException e, @NotNull Runnable action) {
        if (!isPresent()) {
            action.run();
            throw e;
        }
    }

    public BaseDTO<T> call(@NotNull Runnable action) {
        action.run();
        return this;
    }


    public boolean equalObj(Object o) {
        return Boolean.TRUE.equals(data.equals(o));
    }

    public Boolean equalCall(@NotNull Object o, @NotNull Runnable action) {
        boolean equals = equalObj(o);
        if (equals)
            action.run();
        return equals;
    }

    public BaseDTO<T> notEqualThrow(@NotNull Object o, @NotNull @NotNull ServiceException e) {
        boolean equals = equalObj(o);
        if (!equals)
            throw e;
        return this;
    }

    public Stream<T> stream(@NotNull ServiceException e) {
        if (isPresent())
            return Stream.of(data);
        else
            throw e;
    }
}
