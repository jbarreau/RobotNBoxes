package interfaces;


import java.util.Map;

import objet.Box;
import objet.Position;
import Robot.Robot;

public interface IRobotEnvironement {
//perception
	/*public Box[] findForBoxes(Robot r);
	public Obstacle[] findForObstacles(Robot r);
	
	public Robot[] findForRobots(Robot r);
	public Robot[] findForRobotsWithBox(Robot r);*/
	
	public Map<Position, Object> getPerception(Robot r, int x, int y);
	
// action
	public void robotMoved(Robot r, int x, int y);

	public void robotTakeBox(Robot r, Box b, int x, int y);
	public void robotPutBox(Robot r, Box b, int x, int y);
	
	public void robotKillHimself(Robot r);
	public void robotCreated(Robot r, int x, int y);


}
