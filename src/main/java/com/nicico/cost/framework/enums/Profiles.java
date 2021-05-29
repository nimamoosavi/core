package com.nicico.cost.framework.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @apiNote this class used for profiles Type in Applications
 */
@Getter
@AllArgsConstructor
public enum Profiles {
    TEST("test"),
    DEVELOPMENT("dev"),
    PRODUCTION("prod");

    private final String name;
}
