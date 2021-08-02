package app.ladderproject.core.packages.crud.view;

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
    NOT_CONTAIN,
    NOT_START_WITH,
    NOT_END_WITH,
    BLANK,
    NOT_BLANK,
    NULL,
    NOT_NULL,
    AND,
    NOT,
    OR
}
