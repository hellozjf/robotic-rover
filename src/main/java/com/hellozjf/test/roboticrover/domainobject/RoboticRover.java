package com.hellozjf.test.roboticrover.domainobject;

import com.hellozjf.test.roboticrover.constant.AngleEnum;
import com.hellozjf.test.roboticrover.constant.CommandEnum;
import com.hellozjf.test.roboticrover.constant.ErrorEnum;
import com.hellozjf.test.roboticrover.exception.RoboticRoverException;
import com.hellozjf.test.roboticrover.util.ConverterUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hellozjf
 */
@Data
@Slf4j
public class RoboticRover {

    private Place place;

    public RoboticRover(Integer x, Integer y, String direction) {
        place = new Place(x, y, ConverterUtils.getAngleByDirection(direction));
    }

    public RoboticRover(Place place) {
        this.place = place;
    }

    /**
     * 巡逻车执行命令
     * @param cmd
     */
    public void command(String cmd) {
        if (cmd.equalsIgnoreCase(CommandEnum.MOVE.getCode())) {
            move();
        } else if (cmd.equalsIgnoreCase(CommandEnum.LEFT.getCode())) {
            turnLeft();
        } else if (cmd.equalsIgnoreCase(CommandEnum.RIGHT.getCode())) {
            turnRight();
        } else {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_COMMOAND.getCode(),
                    ErrorEnum.UNKNOWN_COMMOAND + ":" + cmd);
        }
    }

    private void move() {

        // 获取x和y的增量
        double addX = Math.cos(Math.toRadians(place.getAngle()));
        double addY = Math.sin(Math.toRadians(place.getAngle()));

//        log.debug("addX = {}, addY = {}", addX, addY);

        // 获取漫游车移动后的坐标
        int nextX = place.getX() + (int) addX;
        int nextY = place.getY() + (int) addY;

//        log.debug("nextX = {}, nextY = {}", nextX, nextY);

        // 更新漫游车坐标
        place.setX(nextX);
        place.setY(nextY);
    }

    private void turnLeft() {
        place.setAngle(place.getAngle() + AngleEnum.QUARTER_CIRCLE.getCode());
    }

    private void turnRight() {
        place.setAngle(place.getAngle() - AngleEnum.QUARTER_CIRCLE.getCode());
    }
}
