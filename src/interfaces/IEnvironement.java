package interfaces;

import implementation.robot.Robot;

import java.util.List;

public interface IEnvironement {
    public List<Robot> getRobots();
    public Robot createRobot();
}
