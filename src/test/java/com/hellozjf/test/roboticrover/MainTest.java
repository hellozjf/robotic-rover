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
    public void test() {
        int d = 180;
        double rad = Math.toRadians(d);
        log.debug("rad = {}", rad);
    }

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
}