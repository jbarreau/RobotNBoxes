package interfaces;


import java.util.Map;

import objet.Box;
import objet.Position;
import MainSys.EcoRobot.Robot;

public interface IRobotEnvironement {
//perception
	public Map<Position, Object> getPerception(Robot r, int x, int y);
	
// action
	public void robotMoved(Robot r, int x, int y);

	public void robotTakeBox(Robot r, Box b, int x, int y);
	public void robotPutBox(Robot r, Box b, int x, int y);
	
	public void robotKillHimself(Robot r);
	public void robotCreated(Robot r, int x, int y);


}
