package com.hellozjf.test.roboticrover.domainobject;

import com.hellozjf.test.roboticrover.constant.CommandEnum;
import com.hellozjf.test.roboticrover.constant.ErrorEnum;
import com.hellozjf.test.roboticrover.exception.RoboticRoverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 平台对象，它将控制漫游车进行各种操作
 * @author hellozjf
 */
public class Platform {

    private static final Logger log = LoggerFactory.getLogger(Platform.class);

    /**
     * 平台最小X坐标
     */
    private Integer minX;

    /**
     * 平台最小Y坐标
     */
    private Integer minY;

    /**
     * 平台最大X坐标
     */
    private Integer maxX;

    /**
     * 平台最大Y坐标
     */
    private Integer maxY;

    public Platform(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public Platform(String platformRightTopPosition) {
        String[] platformSplits = platformRightTopPosition.split(" ");
        if (platformSplits.length != 2) {
            throw new RoboticRoverException(ErrorEnum.INPUT_ERROR.getCode(),
                    ErrorEnum.INPUT_ERROR.getDescription() + ":" + platformRightTopPosition);
        }
        Integer platformX = Integer.valueOf(platformSplits[0]);
        Integer platformY = Integer.valueOf(platformSplits[1]);

        this.minX = 0;
        this.minY = 0;
        this.maxX = platformX;
        this.maxY = platformY;
    }

    /**
     * 记录掉落的漫游车
     */
    private List<RoboticRover> ripRoboticRoverList = new ArrayList<RoboticRover>();

    /**
     * 使漫游车在平台内按照指令行动
     * @param roboticRover
     * @param commands
     */
    public String doCommands(RoboticRover roboticRover, String commands) {
        log.debug("init x = {}, y = {}, direction = {}",
                roboticRover.getX(),
                roboticRover.getY(),
                roboticRover.getDirection());
        // 让巡逻车开始巡逻
        for (int i = 0; i < commands.length(); i++) {

            String command = commands.substring(i, i + 1);
            if (command.equalsIgnoreCase(CommandEnum.MOVE.getCode()) &&
                    willRoboticRoverFall(roboticRover)) {
                // 说明之前有巡逻车在这个位置掉落过，那么应该忽略这个命令
                continue;
            }
            roboticRover.command(command);

            // 判断巡逻车有没有越过边界
            int roboticRoverX = roboticRover.getX();
            int roboticRoverY = roboticRover.getY();
            if (roboticRoverX < minX || roboticRoverX > maxX ||
                    roboticRoverY < minY || roboticRoverY > maxY) {

                // 记下这辆掉落的巡逻车
                ripRoboticRoverList.add(roboticRover);

                // 返回掉落信息
                String ret = roboticRover.getPreviousXYAndDirection() + " RIP";
                log.debug("command = {}, ret = {}", command, ret);
                return ret;
            }

            log.debug("command = {}, ret = {}", command, roboticRover.getXYAndDirection());
        }

        return roboticRover.getXYAndDirection();
    }

    /**
     * 从已掉落的巡逻车的上一次位置和方向信息，来判断这次要执行MOVE命令的巡逻车会不会重蹈覆辙
     * @param roboticRover
     * @return
     */
    private boolean willRoboticRoverFall(RoboticRover roboticRover) {
        for (RoboticRover ripRoboticRover : ripRoboticRoverList) {
            if (ripRoboticRover.getPreviousX().equals(roboticRover.getX()) &&
                    ripRoboticRover.getPreviousY().equals(roboticRover.getY()) &&
                    ripRoboticRover.getDirection().equals(roboticRover.getDirection())) {
                return true;
            }
        }
        return false;
    }

    public Integer getMinX() {
        return minX;
    }

    public void setMinX(Integer minX) {
        this.minX = minX;
    }

    public Integer getMinY() {
        return minY;
    }

    public void setMinY(Integer minY) {
        this.minY = minY;
    }

    public Integer getMaxX() {
        return maxX;
    }

    public void setMaxX(Integer maxX) {
        this.maxX = maxX;
    }

    public Integer getMaxY() {
        return maxY;
    }

    public void setMaxY(Integer maxY) {
        this.maxY = maxY;
    }
}
