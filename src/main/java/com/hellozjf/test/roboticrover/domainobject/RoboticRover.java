package com.hellozjf.test.roboticrover.domainobject;

import com.hellozjf.test.roboticrover.constant.CommandEnum;
import com.hellozjf.test.roboticrover.constant.DirectionEnum;
import com.hellozjf.test.roboticrover.constant.ErrorEnum;
import com.hellozjf.test.roboticrover.exception.RoboticRoverException;

/**
 * @author hellozjf
 */
public class RoboticRover {

    /**
     * 巡逻车的x坐标
     */
    private Integer x;

    /**
     * 巡逻车的y坐标
     */
    private Integer y;

    /**
     * 巡逻车的方向
     */
    private String direction;

    /**
     * 巡逻车上一次x坐标
     */
    private Integer previousX;

    /**
     * 巡逻车上一次y坐标
     */
    private Integer previousY;

    public RoboticRover(Integer x, Integer y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.previousX = null;
        this.previousY = null;
    }

    public RoboticRover(String roboticRoverPosition) {

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

        this.x = roboticRoverX;
        this.y = roboticRoverY;
        this.direction = roboticRoverDirection;
        this.previousX = null;
        this.previousY = null;
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

        // 保存上一次巡逻车的位置
        previousX = x;
        previousY = y;

        // 更新巡逻车的位置
        if (direction.equals(DirectionEnum.NORTH.getCode())) {
            y++;
        } else if (direction.equals(DirectionEnum.SOUTH.getCode())) {
            y--;
        } else if (direction.equals(DirectionEnum.WEST.getCode())) {
            x--;
        } else if (direction.equals(DirectionEnum.EAST.getCode())) {
            x++;
        } else {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_DIRECTION.getCode(),
                    ErrorEnum.UNKNOWN_COMMOAND.getDescription() + ":" + direction);
        }
    }

    private void turnLeft() {
        if (direction.equals(DirectionEnum.NORTH.getCode())) {
            direction = DirectionEnum.WEST.getCode();
        } else if (direction.equals(DirectionEnum.SOUTH.getCode())) {
            direction = DirectionEnum.EAST.getCode();
        } else if (direction.equals(DirectionEnum.WEST.getCode())) {
            direction = DirectionEnum.SOUTH.getCode();
        } else if (direction.equals(DirectionEnum.EAST.getCode())) {
            direction = DirectionEnum.NORTH.getCode();
        } else {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_DIRECTION.getCode(),
                    ErrorEnum.UNKNOWN_COMMOAND.getDescription() + ":" + direction);
        }
    }

    private void turnRight() {
        if (direction.equals(DirectionEnum.NORTH.getCode())) {
            direction = DirectionEnum.EAST.getCode();
        } else if (direction.equals(DirectionEnum.SOUTH.getCode())) {
            direction = DirectionEnum.WEST.getCode();
        } else if (direction.equals(DirectionEnum.WEST.getCode())) {
            direction = DirectionEnum.NORTH.getCode();
        } else if (direction.equals(DirectionEnum.EAST.getCode())) {
            direction = DirectionEnum.SOUTH.getCode();
        } else {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_DIRECTION.getCode(),
                    ErrorEnum.UNKNOWN_COMMOAND.getDescription() + ":" + direction);
        }
    }

    public String getXYAndDirection() {
        String ret = x + " " + y + " " + direction;
        return ret;
    }

    public String getPreviousXYAndDirection() {
        String ret = previousX + " " + previousY + " " + direction;
        return ret;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public String getDirection() {
        return direction;
    }

    public Integer getPreviousX() {
        return previousX;
    }

    public Integer getPreviousY() {
        return previousY;
    }
}
