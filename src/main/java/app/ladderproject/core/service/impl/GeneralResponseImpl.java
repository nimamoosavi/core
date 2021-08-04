package app.ladderproject.core.service.impl;

import app.ladderproject.core.domain.dto.BaseDTO;
import app.ladderproject.core.enums.Status;
import app.ladderproject.core.service.GeneralResponse;
import app.ladderproject.core.utility.impl.ResourceUtilityImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneralResponseImpl<R> implements GeneralResponse<R> {
    public BaseDTO<R> successResponse(R o) {
        return BaseDTO.<R>builder().data(o)
                .code(ResourceUtilityImpl.successResource().getCode())
                .message(ResourceUtilityImpl.successResource().getMessage())
                .status(Status.SUCCESS).build();
    }

    public BaseDTO<List<R>> successListResponse(List<R> o) {
        return BaseDTO.<List<R>>builder().data(o)
                .code(ResourceUtilityImpl.successResource().getCode())
                .message(ResourceUtilityImpl.successResource().getMessage())
                .status(Status.SUCCESS).build();
    }
}
