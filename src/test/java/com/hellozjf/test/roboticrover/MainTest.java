package com.hellozjf.test.roboticrover;

import com.hellozjf.test.roboticrover.domainobject.Platform;
import com.hellozjf.test.roboticrover.domainobject.RoboticRover;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author hellozjf
 */
public class MainTest {

    private static final Logger log = LoggerFactory.getLogger(MainTest.class);

    @Test
    public void normalTest() {
        Platform platform = new Platform("5 5");
        RoboticRover roboticRover = new RoboticRover("1 2 N");
        String commands = "LMLMLMLMM";
        Assert.assertEquals("1 3 N", platform.doCommands(roboticRover, commands));
    }

    @Test
    public void normalTest2() {
        Platform platform = new Platform("5 5");
        RoboticRover roboticRover = new RoboticRover("3 3 E");
        String commands = "MMRMMRMRRM";
        Assert.assertEquals("5 1 E", platform.doCommands(roboticRover, commands));
    }

    @Test
    public void ripTest1() {
        Platform platform = new Platform("5 5");
        RoboticRover roboticRover = new RoboticRover("0 0 N");
        String commands = "MLMML";
        Assert.assertEquals("0 1 W RIP", platform.doCommands(roboticRover, commands));
    }

    @Test
    public void ripTest2() {
        Platform platform = new Platform("5 5");

        // 先让一辆巡逻车出界
        RoboticRover roboticRover = new RoboticRover("3 2 E");
        String commands = "MMMMLMRM";
        Assert.assertEquals("5 2 E RIP", platform.doCommands(roboticRover, commands));

        // 再让一辆巡逻车在同样的地方出界
        RoboticRover roboticRover2 = new RoboticRover("5 0 W");
        String commands2 = "MRMRMLMRMLM";
        Assert.assertEquals("5 3 N", platform.doCommands(roboticRover2, commands2));
    }
}