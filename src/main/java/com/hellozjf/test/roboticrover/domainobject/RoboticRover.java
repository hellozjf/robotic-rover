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

        // 输入的字符串为"x y direction"，其中direction只能为N/S/W/E
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
        if (cmd.equals(CommandEnum.MOVE.getCode())) {
            move();
        } else if (cmd.equals(CommandEnum.LEFT.getCode()) ||
                cmd.equals(CommandEnum.RIGHT.getCode())) {
            turn(cmd);
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

    /**
     * 传入L或R，使巡逻车改变方向
     * @param cmd L或R
     */
    private void turn(String cmd) {

        // cmd只能是L或者R，否则抛出异常
        if (! (cmd.equals(CommandEnum.LEFT.getCode()) ||
                cmd.equals(CommandEnum.RIGHT.getCode()))) {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_COMMOAND.getCode(),
                    ErrorEnum.UNKNOWN_COMMOAND.getDescription() + ":" + cmd);
        }

        // 方向只能是N, W, S, E四个中的一个，否则抛出异常
        if (! (direction.equals(DirectionEnum.NORTH.getCode()) ||
                direction.equals(DirectionEnum.WEST.getCode()) ||
                direction.equals(DirectionEnum.SOUTH.getCode()) ||
                direction.equals(DirectionEnum.EAST.getCode()))) {
            throw new RoboticRoverException(ErrorEnum.UNKNOWN_DIRECTION.getCode(),
                    ErrorEnum.UNKNOWN_COMMOAND.getDescription() + ":" + direction);
        }

        if (direction.equals(DirectionEnum.NORTH.getCode())) {
            if (cmd.equals(CommandEnum.LEFT.getCode())) {
                // 北左转是西
                direction = DirectionEnum.WEST.getCode();
            } else {
                // 北右转是东
                direction = DirectionEnum.EAST.getCode();
            }
        } else if (direction.equals(DirectionEnum.SOUTH.getCode())) {
            if (cmd.equals(CommandEnum.LEFT.getCode())) {
                // 南左转是东
                direction = DirectionEnum.EAST.getCode();
            } else {
                // 南右转是西
                direction = DirectionEnum.WEST.getCode();
            }
        } else if (direction.equals(DirectionEnum.WEST.getCode())) {
            if (cmd.equals(CommandEnum.LEFT.getCode())) {
                // 西左转是南
                direction = DirectionEnum.SOUTH.getCode();
            } else {
                // 西右转是北
                direction = DirectionEnum.NORTH.getCode();
            }
        } else {
            if (cmd.equals(CommandEnum.LEFT.getCode())) {
                // 东左转是北
                direction = DirectionEnum.NORTH.getCode();
            } else {
                // 东右转是南
                direction = DirectionEnum.SOUTH.getCode();
            }
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
