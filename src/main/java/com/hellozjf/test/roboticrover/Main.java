package com.hellozjf.test.roboticrover;

import com.hellozjf.test.roboticrover.domainobject.Platform;
import com.hellozjf.test.roboticrover.domainobject.RoboticRover;
import com.hellozjf.test.roboticrover.util.InputUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

/**
 * 程序主入口
 *
 * @author hellozjf
 */
@Slf4j
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 获取平台左上角的坐标，如果漫游车在(0, 0)到(platformX, platformY)以外会掉落，并打印RIP
        // 以后有别的漫游车在相同的位置执行相同的指令，就会拒绝执行
        String platformRightTopPosition = scanner.nextLine();
        Platform platform = InputUtils.getPlatformFormInput(platformRightTopPosition);

        while (scanner.hasNextLine()) {

            // 获取巡逻车的位置
            String roboticRoverPosition = scanner.nextLine();
            RoboticRover roboticRover = InputUtils.getRoboticRoverFormInput(roboticRoverPosition);

            // 获取巡逻车的命令
            String commands = scanner.nextLine();

            log.info("{}", platform.doCommands(roboticRover, commands));
        }
    }
}
