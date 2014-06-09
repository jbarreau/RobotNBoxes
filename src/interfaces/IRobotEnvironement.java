package interfaces;


import implementation.robot.Robot;
import objet.Box;
import objet.Position;

import java.util.Map;


public interface IRobotEnvironement {
    //perception
	public Map<Position, Object> getPerception(Robot r);

    // action
    public void robotMoved(Robot robot);

    public void robotTakeBox(Robot robot, Box b);

    public void robotPutBox(Robot robot, Box b);

    public void robotKillHimself(Robot robot);

    public void robotCreated(Robot robot);


}
