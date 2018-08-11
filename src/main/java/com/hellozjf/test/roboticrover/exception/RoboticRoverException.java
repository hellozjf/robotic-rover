package com.hellozjf.test.roboticrover.exception;

import com.hellozjf.test.roboticrover.constant.ErrorEnum;

/**
 * 巡逻车异常
 * @author hellozjf
 */
public class RoboticRoverException extends RuntimeException {
    private Integer code;

    public RoboticRoverException(Integer code, String description) {
        super(description);
        this.code = code;
    }

    public RoboticRoverException(ErrorEnum errorEnum) {
        super(errorEnum.getDescription());
        this.code = errorEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }
}
