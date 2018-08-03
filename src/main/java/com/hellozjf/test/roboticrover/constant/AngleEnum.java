package com.hellozjf.test.roboticrover.constant;

import lombok.Getter;

/**
 * @author hellozjf
 */
@Getter
public enum AngleEnum {
    EAST(0, "东"),
    NORTH(90, "北"),
    WEST(180, "西"),
    SOUTH(270, "南"),
    CIRCLE(360, "一圈360度"),
    QUARTER_CIRCLE(CIRCLE.code / 4, "四分之一圈90度"),
    ;
    private Integer code;
    private String description;

    AngleEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }
}
