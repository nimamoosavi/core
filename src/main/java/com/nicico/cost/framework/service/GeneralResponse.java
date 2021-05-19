package com.nicico.cost.framework.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.ResultStatus;
import com.nicico.cost.framework.utility.ApplicationResource;

import java.util.List;

public interface GeneralResponse<R> {


    static <G> BaseDTO<G> successCustomResponse(G o) {
        return BaseDTO.<G>builder().data(o)
                .resultCode(ApplicationResource.successResource().getResultCode())
                .resultMessage(ApplicationResource.successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

    static <G> BaseDTO<Iterable<G>> successCustomListResponse(Iterable<G> o) {
        return BaseDTO.<Iterable<G>>builder().data(o)
                .resultCode(ApplicationResource.successResource().getResultCode())
                .resultMessage(ApplicationResource.successResource().getResultMessage())
                .status(ResultStatus.SUCCESS).build();
    }

    BaseDTO<R> successResponse(R o);

    BaseDTO<List<R>> successListResponse(List<R> o);

}
