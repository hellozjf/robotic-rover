package com.hellozjf.test.roboticrover.exception;

import com.hellozjf.test.roboticrover.constant.ErrorEnum;
import lombok.Getter;

/**
 * 巡逻车异常
 * @author hellozjf
 */
@Getter
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
}
