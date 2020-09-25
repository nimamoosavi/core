package ir.webold.framework.domain.dto;

import ir.webold.framework.exception.ServiceException;

import javax.validation.constraints.NotNull;

import static ir.webold.framework.service.GeneralService.successCustomResponse;

public class OptionalDTO<R> {

    BaseDTO<R> r;

    public OptionalDTO(BaseDTO<R> r) {
        this.r = r;
    }

    public boolean isPresent() {
        if (Boolean.TRUE.equals(r == null))
            return false;
        else
            return true;
    }

    public BaseDTO<R> orElseThrow(@NotNull ServiceException e) {
        if (!isPresent())
            throw e;
        else
            return r;
    }

    public BaseDTO<R> orElse(R r) {
        return successCustomResponse(r);
    }

    public BaseDTO<R> ifPresent(@NotNull Runnable action) {
        if (isPresent())
            action.run();
        return r;
    }

    public void orElseCallAndThrow(@NotNull ServiceException e, @NotNull Runnable action) {
        if (!isPresent()) {
            action.run();
            throw e;
        }
    }

    public BaseDTO<R> call(@NotNull Runnable action) {
        action.run();
        return r;
    }

    public boolean equal(Object t) {
        if (Boolean.TRUE.equals(r.equals(t)))
            return true;
        else
            return false;
    }
}
