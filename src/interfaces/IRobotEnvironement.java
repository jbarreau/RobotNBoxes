package interfaces;


import objet.Box;
import objet.Obstacle;
import Robot.Robot;

public interface IRobotEnvironement {
//perception
	public Box[] findForBoxes(Robot r);
	public Obstacle[] findForObstacles(Robot r);
	
	public Robot[] findForRobots(Robot r);
	public Robot[] findForRobotsWithBox(Robot r);
	
// action
	public void robotMoved(Robot r, int x, int y);

	public void robotTakeBox(Robot r, Box b, int x, int y);
	public void robotPutBox(Robot r, Box b, int x, int y);
	
	public void robotKillHimself(Robot r, int x, int y);
	public void robotCreated(Robot r, int x, int y);


}
