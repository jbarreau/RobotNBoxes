package interfaces;


import Environement.Map.Box;
import Environement.Map.Obstacle;
import Environement.Map.Robot;

public interface IEnvPerception {
	public Box[] findForBoxes(Robot r);
	public Obstacle[] findForObstacles(Robot r);
	
	public Robot[] findForRobots(Robot r);
	public Robot[] findForRobotsWithBox(Robot r);
}
