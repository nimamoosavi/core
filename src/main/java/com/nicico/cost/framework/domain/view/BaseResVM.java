package com.nicico.cost.framework.domain.view;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResVM<I extends Serializable> {
    I id;
}
