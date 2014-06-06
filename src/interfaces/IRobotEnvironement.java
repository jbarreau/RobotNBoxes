package interfaces;


import java.util.Map;

import objet.Box;
import objet.Position;
import MainSys.EcoRobot.Robot;

public interface IRobotEnvironement {
//perception
	public Map<Position, Object> getPerception(Robot robot);
	
// action
	public void robotMoved(Robot robot, int x, int y);

	public void robotTakeBox(Robot robot, Box b);
	public void robotPutBox(Robot robot, Box b);
	
	public void robotKillHimself(Robot robot);
	public void robotCreated(Robot robot);


}
