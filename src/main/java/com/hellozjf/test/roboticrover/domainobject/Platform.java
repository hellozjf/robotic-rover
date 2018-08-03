package com.hellozjf.test.roboticrover.domainobject;

import com.hellozjf.test.roboticrover.constant.CommandEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 平台对象，它将控制漫游车进行各种操作
 * @author hellozjf
 */
@Data
@Slf4j
public class Platform {

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

    /**
     * 记录漫游车掉落点的位置信息
     */
    private List<Place> ripPlaceList = new ArrayList<>();

    /**
     * 使漫游车在平台内按照指令行动
     * @param roboticRover
     * @param commands
     */
    public String doCommands(RoboticRover roboticRover, String commands) {
        log.debug("init place = {}", roboticRover.getPlace().getDirectionInfo());
        // 让巡逻车开始巡逻
        for (int i = 0; i < commands.length(); i++) {

            String command = commands.substring(i, i + 1);
            if (command.equalsIgnoreCase(CommandEnum.MOVE.getCode()) &&
                    ripPlaceList.indexOf(roboticRover.getPlace()) != -1) {
                // 说明之前有巡逻车在这个位置掉落过，那么应该忽略这个命令
                continue;
            }
            roboticRover.command(command);

            // 判断巡逻车有没有越过边界
            int roboticRoverX = roboticRover.getPlace().getX();
            int roboticRoverY = roboticRover.getPlace().getY();
            if (roboticRoverX < minX || roboticRoverX > maxX ||
                    roboticRoverY < minY || roboticRoverY > maxY) {

                // 获取巡逻车掉落前的位置和方向
                Place place = getFallPlace(roboticRover);

                // 记下该位置
                ripPlaceList.add(place);

                // 返回掉落信息
                String ret = place.getDirectionInfo() + " RIP";
                log.debug("command = {}, place = {}", command, ret);
                return ret;
            }

            log.debug("command = {}, place = {}", command, roboticRover.getPlace().getDirectionInfo());
        }

        return roboticRover.getPlace().getDirectionInfo();
    }

    /**
     * 让巡逻车回到掉落前的位置
     * @param roboticRover
     */
    private Place getFallPlace(RoboticRover roboticRover) {
        int x = roboticRover.getPlace().getX();
        int y = roboticRover.getPlace().getY();
        if (x < minX) {
            x = minX;
        } else if (x > maxX) {
            x = maxX;
        } else if (y < minY) {
            y = minY;
        } else if (y > maxY) {
            y = maxY;
        }

        // 返回掉落前的位置和方向
        Place place = new Place(x, y, roboticRover.getPlace().getAngle());
        return place;
    }
}
