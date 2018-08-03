package com.hellozjf.test.roboticrover.constant;

import lombok.Getter;

/**
 * 机器人漫游车的指令
 * @author hellozjf
 */
@Getter
public enum CommandEnum {
    MOVE("M", "移动"),
    LEFT("L", "左转"),
    RIGHT("R", "右转"),
    ;
    private String code;
    private String description;

    CommandEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
