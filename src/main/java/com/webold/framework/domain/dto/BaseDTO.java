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

/**
 * @param <T> is the Object that you need used in data
 */
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

    /**
     * @param e is an Object of RunTime Exception
     * @return this
     */
    public BaseDTO<T> orElseThrow(@NotNull ServiceException e) {
        if (!isPresent())
            throw e;
        else
            return this;
    }

    /**
     * @param e is an Object of RunTime Exception
     * @return this
     * @apiNote this method Throw An Exception if the Result Not Present
     */
    public BaseDTO<T> ifNotSuccessThrow(@NotNull ServiceException e) {
        if (Boolean.FALSE.equals(status.name().equals(Status.SUCCESS.name())))
            throw e;
        else
            return this;
    }

    /**
     * @param r the New Response Object
     * @return this or new Object
     */
    public BaseDTO<T> orElse(T r) {
        if (Boolean.FALSE.equals(status.name().equals(Status.SUCCESS.name())))
            return GeneralResponse.successCustomResponse(r);
        else
            return this;
    }

    /**
     * @param action the new action that you need Run concurrent
     * @return this
     */
    public BaseDTO<T> ifPresent(@NotNull Runnable action) {
        if (isPresent())
            action.run();
        return this;
    }

    /**
     * @param e      is an Object of RunTime Exception
     * @param action the new action that you need Run concurrent
     */
    public void orElseCallAndThrow(@NotNull ServiceException e, @NotNull Runnable action) {
        if (!isPresent()) {
            action.run();
            throw e;
        }
    }

    /**
     * @param action the new action that you need Run concurrent
     * @return this
     */
    public BaseDTO<T> call(@NotNull Runnable action) {
        action.run();
        return this;
    }


    public boolean equalObj(Object o) {
        return Boolean.TRUE.equals(data.equals(o));
    }

    /**
     * @param o      the object for equals in data
     * @param action the new action that you need Run concurrent
     * @return the result true / false
     */
    public Boolean equalCall(@NotNull Object o, @NotNull Runnable action) {
        boolean equals = equalObj(o);
        if (equals)
            action.run();
        return equals;
    }

    /**
     * @param o the object for equals in data
     * @param e is an Object of RunTime Exception
     * @return this
     */
    public BaseDTO<T> notEqualThrow(@NotNull Object o, @NotNull @NotNull ServiceException e) {
        boolean equals = equalObj(o);
        if (!equals)
            throw e;
        return this;
    }

    /**
     * @param e is an Object of RunTime Exception
     * @return Stream<THIS>
     */
    public Stream<T> stream(@NotNull ServiceException e) {
        if (isPresent())
            return Stream.of(data);
        else
            throw e;
    }
}
