package com.hellozjf.test.roboticrover.util;

import com.hellozjf.test.roboticrover.constant.AngleEnum;
import com.hellozjf.test.roboticrover.constant.DirectionEnum;
import com.hellozjf.test.roboticrover.constant.ErrorEnum;
import com.hellozjf.test.roboticrover.exception.RoboticRoverException;

/**
 * 进行类型转化，例如将N转化为90度
 * @author hellozjf
 */
public class ConverterUtils {

    /**
     * 将方向转化为角度，例如N转化为90度
     * @param direction 方向
     * @return 角度
     */
    public static Integer getAngleByDirection(String direction) {
        if (direction.equalsIgnoreCase(DirectionEnum.EAST.getCode())) {
            return AngleEnum.EAST.getCode();
        } else if (direction.equalsIgnoreCase(DirectionEnum.NORTH.getCode())) {
            return AngleEnum.NORTH.getCode();
        } else if (direction.equalsIgnoreCase(DirectionEnum.WEST.getCode())) {
            return AngleEnum.WEST.getCode();
        } else if (direction.equalsIgnoreCase(DirectionEnum.SOUTH.getCode())) {
            return AngleEnum.SOUTH.getCode();
        } else {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_DIRECTION.getCode(),
                    ErrorEnum.UNKNOWN_DIRECTION.getDescription() + ":" + direction);
        }
    }

    /**
     * 将角度转化为方向
     * @param angle 角度
     * @return 方向
     */
    public static String getDirectionByAngle(Integer angle) {
        Integer ang = angle % AngleEnum.CIRCLE.getCode();
        if (ang < 0) {
            ang += AngleEnum.CIRCLE.getCode();
        }
        if (ang.equals(AngleEnum.EAST.getCode())) {
            return DirectionEnum.EAST.getCode();
        } else if (ang.equals(AngleEnum.NORTH.getCode())) {
            return DirectionEnum.NORTH.getCode();
        } else if (ang.equals(AngleEnum.WEST.getCode())) {
            return DirectionEnum.WEST.getCode();
        } else if (ang.equals(AngleEnum.SOUTH.getCode())) {
            return DirectionEnum.SOUTH.getCode();
        } else {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_DIRECTION.getCode(),
                    ErrorEnum.UNKNOWN_DIRECTION.getDescription() + ":" + angle);
        }
    }
}
