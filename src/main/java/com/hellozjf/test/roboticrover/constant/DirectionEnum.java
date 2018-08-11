package com.hellozjf.test.roboticrover.constant;

/**
 * 机器人漫游车的方向
 * @author hellozjf
 */
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

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
