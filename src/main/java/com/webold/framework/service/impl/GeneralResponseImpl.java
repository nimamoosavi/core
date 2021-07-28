package com.webold.framework.service.impl;

import com.webold.framework.domain.dto.BaseDTO;
import com.webold.framework.enums.Status;
import com.webold.framework.service.GeneralResponse;
import com.webold.framework.utility.impl.ResourceUtilityImpl;
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
