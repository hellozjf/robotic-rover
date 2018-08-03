package com.hellozjf.test.roboticrover.util;

import com.hellozjf.test.roboticrover.constant.ErrorEnum;
import com.hellozjf.test.roboticrover.domainobject.Platform;
import com.hellozjf.test.roboticrover.domainobject.RoboticRover;
import com.hellozjf.test.roboticrover.exception.RoboticRoverException;

/**
 * 通过各种输入字符串获取相应对象
 * @author hellozjf
 */
public class InputUtils {

    /**
     * 通过输入的字符串获取Platform对象
     * @param platformRightTopPosition "5 5"这样格式的字符串（不带引号），第一部分是x坐标，第二部分是y坐标
     * @return Platform对象
     */
    public static Platform getPlatformFormInput(String platformRightTopPosition) {

        String[] platformSplits = platformRightTopPosition.split(" ");
        if (platformSplits.length != 2) {
            throw new RoboticRoverException(ErrorEnum.INPUT_ERROR.getCode(),
                    ErrorEnum.INPUT_ERROR.getDescription() + ":" + platformRightTopPosition);
        }
        Integer platformX = Integer.valueOf(platformSplits[0]);
        Integer platformY = Integer.valueOf(platformSplits[1]);

        // 构造出平台对象
        Platform platform = new Platform(0, 0, platformX, platformY);
        return platform;
    }

    /**
     * 通过输入的字符串获取RoboticRover对象
     * @param roboticRoverPosition "2 3 N"这样格式的字符串（不带引号），第一部分是x坐标，第二部分是y坐标，第三部分是方向
     * @return RoboticRover对象
     */
    public static RoboticRover getRoboticRoverFormInput(String roboticRoverPosition) {

        // 输入的字符串为
        String[] roboticRoverSplits = roboticRoverPosition.split(" ");
        if (roboticRoverSplits.length != 3) {
            throw new RoboticRoverException(ErrorEnum.INPUT_ERROR.getCode(),
                    ErrorEnum.INPUT_ERROR.getDescription() + ":" + roboticRoverPosition);
        }

        // 构造巡逻车
        Integer roboticRoverX = Integer.valueOf(roboticRoverSplits[0]);
        Integer roboticRoverY = Integer.valueOf(roboticRoverSplits[1]);
        String roboticRoverDirection = roboticRoverSplits[2];
        RoboticRover roboticRover = new RoboticRover(roboticRoverX, roboticRoverY, roboticRoverDirection);
        return roboticRover;
    }
}
