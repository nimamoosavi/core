package com.nicico.cost.framework.service.impl;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.Status;
import com.nicico.cost.framework.service.GeneralResponse;
import com.nicico.cost.framework.utility.response.impl.ApplicationResourceImpl;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GeneralResponseImpl<R> implements GeneralResponse<R> {
    public BaseDTO<R> successResponse(R o) {
        return BaseDTO.<R>builder().data(o)
                .code(ApplicationResourceImpl.successResource().getCode())
                .message(ApplicationResourceImpl.successResource().getMessage())
                .status(Status.SUCCESS).build();
    }

    public BaseDTO<List<R>> successListResponse(List<R> o) {
        return BaseDTO.<List<R>>builder().data(o)
                .code(ApplicationResourceImpl.successResource().getCode())
                .message(ApplicationResourceImpl.successResource().getMessage())
                .status(Status.SUCCESS).build();
    }
}
