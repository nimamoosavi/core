package com.webold.framework.packages.crud.view;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Query {
    private Criteria criteria;
    private List<Sort> sorts;
}
