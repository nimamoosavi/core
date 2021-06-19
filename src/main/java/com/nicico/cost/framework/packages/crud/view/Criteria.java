package com.nicico.cost.framework.packages.crud.view;

import lombok.Data;

import java.util.List;

@Data
public class Criteria {
    private String fieldName;
    private Operator operator;
    private Object value;
    private String start;
    private String end;
    private List<Criteria> criteria;
}
