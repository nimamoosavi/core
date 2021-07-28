package com.webold.framework.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webold.framework.enums.Status;
import com.webold.framework.service.GeneralResponse;
import com.webold.framework.service.exception.ServiceException;
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
    private String code;
    private String message;
    private Status status;
    private T data;
    private List<Notification> notifies;


    @JsonIgnore
    public boolean isPresent() {
        return !Status.ERROR.equals(status);
    }

    public BaseDTO<T> orElseThrow(@NotNull ServiceException e) {
        if (!isPresent())
            throw e;
        else
            return this;
    }

    public BaseDTO<T> ifNotSuccessThrow(@NotNull ServiceException e) {
        if (Boolean.FALSE.equals(status.name().equals(Status.SUCCESS.name())))
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
