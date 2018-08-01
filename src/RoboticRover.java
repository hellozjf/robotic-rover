import java.util.Scanner;

/**
 * @author hellozjf
 */
public class RoboticRover {

    // 机器人漫游车的方向
    public static final String DIRECTION_N = "N";
    public static final String DIRECTION_S = "S";
    public static final String DIRECTION_E = "E";
    public static final String DIRECTION_W = "W";

    // 机器人漫游车的指令
    public static final String COMMAND_M = "M";
    public static final String COMMAND_L = "L";
    public static final String COMMAND_R = "R";

    private Integer x;
    private Integer y;
    private String direction;
    private Integer platformX;
    private Integer platformY;

    public RoboticRover(Integer x, Integer y, String direction, Integer platformX, Integer platformY) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.platformX = platformX;
        this.platformY = platformY;
    }

    /**
     * 巡逻车执行命令
     * @param cmd
     */
    public void command(String cmd) {
        if (cmd.equalsIgnoreCase(COMMAND_M)) {
            move();
        } else if (cmd.equalsIgnoreCase(COMMAND_L)) {
            turnLeft();
        } else if (cmd.equalsIgnoreCase(COMMAND_R)) {
            turnRight();
        } else {
            throw new RuntimeException("Unsupport command = " + cmd);
        }
    }

    /**
     * 巡逻车获取当前位置和方向，以空格分隔
     * @return
     */
    public String getInfo() {
        return x + " " + y + " " + direction;
    }

    private void move() {
        if (direction.equalsIgnoreCase(DIRECTION_W)) {
            judgeOutside(x - 1, y);
            --x;
        } else if (direction.equalsIgnoreCase(DIRECTION_E)) {
            judgeOutside(x + 1, y);
            ++x;
        } else if (direction.equalsIgnoreCase(DIRECTION_N)) {
            judgeOutside(x, y + 1);
            ++y;
        } else if (direction.equalsIgnoreCase(DIRECTION_S)) {
            judgeOutside(x, y - 1);
            --y;
        } else {
            throw new RuntimeException("Unknown direction = " + direction);
        }
    }

    private void judgeOutside(Integer judgeX, Integer judgeY) {
        if (judgeX < 0 || judgeX > platformX || judgeY < 0 || judgeY > platformY) {
            throw new RuntimeException("Can not move outside! Will go to [" + judgeX + "," + judgeY + "]");
        }
    }

    private void turnLeft() {
        if (direction.equalsIgnoreCase(DIRECTION_W)) {
            direction = DIRECTION_S;
        } else if (direction.equalsIgnoreCase(DIRECTION_E)) {
            direction = DIRECTION_N;
        } else if (direction.equalsIgnoreCase(DIRECTION_N)) {
            direction = DIRECTION_W;
        } else if (direction.equalsIgnoreCase(DIRECTION_S)) {
            direction = DIRECTION_E;
        } else {
            throw new RuntimeException("Unknown direction = " + direction);
        }
    }

    private void turnRight() {
        if (direction.equalsIgnoreCase(DIRECTION_W)) {
            direction = DIRECTION_N;
        } else if (direction.equalsIgnoreCase(DIRECTION_E)) {
            direction = DIRECTION_S;
        } else if (direction.equalsIgnoreCase(DIRECTION_N)) {
            direction = DIRECTION_E;
        } else if (direction.equalsIgnoreCase(DIRECTION_S)) {
            direction = DIRECTION_W;
        } else {
            throw new RuntimeException("Unknown direction = " + direction);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 获取平台左上角的坐标，我不太明白为什么需要这个坐标
        // 所以我假设漫游车只能在(0, 0)到(platformX, platformY)之间漫游，超出坐标会抛出异常
        String platformRightTopPosition = scanner.nextLine();
        String[] platformSplits = platformRightTopPosition.split(" ");
        if (platformSplits.length != 2) {
            throw new RuntimeException("Get platform right top position error! ErrorString = " + platformRightTopPosition);
        }
        Integer platformX = Integer.valueOf(platformSplits[0]);
        Integer platformY = Integer.valueOf(platformSplits[1]);

        while (scanner.hasNextLine()) {

            // 获取巡逻车的位置以及命令
            String roboticRoverPosition = scanner.nextLine();
            String roboticRoverCommands = scanner.nextLine();
            String[] roboticRoverSplits = roboticRoverPosition.split(" ");
            if (roboticRoverSplits.length != 3) {
                throw new RuntimeException("Get roboticRoverPosition error! ErrorString = " + roboticRoverPosition);
            }

            // 构造巡逻车
            Integer roboticRoverX = Integer.valueOf(roboticRoverSplits[0]);
            Integer roboticRoverY = Integer.valueOf(roboticRoverSplits[1]);
            String roboticRoverDirection = roboticRoverSplits[2];
            RoboticRover roboticRover = new RoboticRover(roboticRoverX, roboticRoverY, roboticRoverDirection, platformX, platformY);

            // 让巡逻车开始巡逻
            for (int i = 0; i < roboticRoverCommands.length(); i++) {
                roboticRover.command(roboticRoverCommands.substring(i, i + 1));
            }

            // 输出巡逻车位置和方向
            System.out.println(roboticRover.getInfo());
        }
    }
}
