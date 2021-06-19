package com.nicico.cost.framework.packages.crud.view;

import lombok.Getter;

@Getter
public enum Operator {
    EQUALS,
    NOT_EQUALS,
    GREATER_THAN,
    LESS_THAN,
    GREATER_OR_EQUALS,
    LESS_OR_EQUALS,
    CONTAINS,
    START_WITH,
    END_WITH,
    IS_CONTAIN,
    IS_START_WITH,
    IS_END_WITH,
    NOT_CONTAIN,
    NOT_START_WITH,
    NOT_END_WITH,
    IS_NOT_CONTAIN,
    IS_NOT_START_WITH,
    IS_NOT_WND_WITH,
    IS_BETWEEN,
    IS_BLANK,
    NOT_BLANK,
    IS_NULL,
    IS_NOT_NULL,
    AND,
    NOT,
    OR,
    BETWEEN;
}
