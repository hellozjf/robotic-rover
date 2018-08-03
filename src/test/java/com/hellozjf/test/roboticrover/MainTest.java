package com.hellozjf.test.roboticrover;

import com.hellozjf.test.roboticrover.domainobject.Platform;
import com.hellozjf.test.roboticrover.domainobject.RoboticRover;
import com.hellozjf.test.roboticrover.util.InputUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

/**
 * @author hellozjf
 */
@Slf4j
public class MainTest {

    @Test
    public void normalTest() {
        Platform platform = InputUtils.getPlatformFormInput("5 5");
        RoboticRover roboticRover = InputUtils.getRoboticRoverFormInput("1 2 N");
        String commands = "LMLMLMLMM";
        Assert.assertEquals("1 3 N", platform.doCommands(roboticRover, commands));
    }

    @Test
    public void normalTest2() {
        Platform platform = InputUtils.getPlatformFormInput("5 5");
        RoboticRover roboticRover = InputUtils.getRoboticRoverFormInput("3 3 E");
        String commands = "MMRMMRMRRM";
        Assert.assertEquals("5 1 E", platform.doCommands(roboticRover, commands));
    }

    @Test
    public void ripTest1() {
        Platform platform = InputUtils.getPlatformFormInput("5 5");
        RoboticRover roboticRover = InputUtils.getRoboticRoverFormInput("0 0 N");
        String commands = "MLMML";
        Assert.assertEquals("0 1 W RIP", platform.doCommands(roboticRover, commands));
    }

    @Test
    public void ripTest2() {
        Platform platform = InputUtils.getPlatformFormInput("5 5");

        // 先让一辆巡逻车出界
        RoboticRover roboticRover = InputUtils.getRoboticRoverFormInput("3 2 E");
        String commands = "MMMMLMRM";
        Assert.assertEquals("5 2 E RIP", platform.doCommands(roboticRover, commands));

        // 再让一辆巡逻车在同样的地方出界
        RoboticRover roboticRover2 = InputUtils.getRoboticRoverFormInput("5 0 W");
        String commands2 = "MRMRMLMRMLM";
        Assert.assertEquals("5 3 N", platform.doCommands(roboticRover2, commands2));
    }
}