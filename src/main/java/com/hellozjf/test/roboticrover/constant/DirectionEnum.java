package com.hellozjf.test.roboticrover.constant;

import lombok.Getter;

/**
 * 机器人漫游车的方向
 * @author hellozjf
 */
@Getter
public enum DirectionEnum {
    NORTH("N", "北"),
    EAST("E", "东"),
    SOUTH("S", "南"),
    WEST("W", "西"),
    ;
    private String code;
    private String description;

    DirectionEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
