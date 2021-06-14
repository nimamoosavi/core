package com.nicico.cost.framework.service;

import com.nicico.cost.framework.domain.dto.BaseDTO;
import com.nicico.cost.framework.enums.Status;
import com.nicico.cost.framework.utility.impl.ResourceUtilityImpl;

import java.util.List;

/**
 * @author nima
 * @since 1.0.1
 * @param <R> the type Of Response that you can return it
 */
public interface GeneralResponse<R> {


    /**
     *
     * @param o the Input Object that you can input to methode
     * @param <G> the Type Of Return BaseDTO Object
     * @return the BaseDTO Object
     */
    static <G> BaseDTO<G> successCustomResponse(G o) {
        return BaseDTO.<G>builder().data(o)
                .code(ResourceUtilityImpl.successResource().getCode())
                .message(ResourceUtilityImpl.successResource().getMessage())
                .status(Status.SUCCESS).build();
    }

    static <G> BaseDTO<List<G>> successCustomListResponse(List<G> o) {
        return BaseDTO.<List<G>>builder().data(o)
                .code(ResourceUtilityImpl.successResource().getCode())
                .message(ResourceUtilityImpl.successResource().getMessage())
                .status(Status.SUCCESS).build();
    }


    BaseDTO<R> successResponse(R o);

    BaseDTO<List<R>> successListResponse(List<R> o);

}
