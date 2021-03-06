package com.hellozjf.test.roboticrover.constant;

/**
 * @author hellozjf
 */
public enum ErrorEnum {
    RIP(1, "小车掉落"),
    UNKNOWN_DIRECTION(2, "未知方向"),
    INPUT_ERROR(3, "输入错误"),
    UNKNOWN_COMMOAND(4, "未知指令"),
    ;
    private Integer code;
    private String description;

    ErrorEnum(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
