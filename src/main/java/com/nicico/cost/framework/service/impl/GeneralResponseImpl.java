package com.nicico.cost.framework.service.impl;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.ResultStatus;
import com.nicico.cost.framework.service.GeneralResponse;
import com.nicico.cost.framework.utility.ApplicationResource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneralResponseImpl<R> implements GeneralResponse<R> {
    public BaseDTO<R> successResponse(R o) {
        return BaseDTO.<R>builder().data(o)
                .resultCode(ApplicationResource.successResource().getResultCode())
                .resultMessage(ApplicationResource.successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

    public BaseDTO<List<R>> successListResponse(List<R> o) {
        return BaseDTO.<List<R>>builder().data(o)
                .resultCode(ApplicationResource.successResource().getResultCode())
                .resultMessage(ApplicationResource.successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }
}
