package com.hellozjf.test.roboticrover.constant;

import lombok.Getter;

/**
 * @author hellozjf
 */
@Getter
public enum AngleEnum {
    EAST(Math.PI * 0, "东"),
    NORTH(Math.PI * 0.5, "北"),
    WEST(Math.PI * 1, "西"),
    SOUTH(Math.PI * 1.5, "南"),
    CIRCLE(Math.PI * 2, "一圈360度"),
    QUARTER_CIRCLE(CIRCLE.code / 4, "四分之一圈90度"),
    ;
    private Double code;
    private String description;

    AngleEnum(Double code, String description) {
        this.code = code;
        this.description = description;
    }
}
