package interfaces;

import Environement.Map.Box;
import Environement.Map.Robot;




public interface IEnvAction {
	public void robotMoved(Robot r, int x, int y);

	public void robotTakeBox(Robot r, Box b);
	public void robotPutBox(Robot r, Box b);
	
	public void robotKillHimself(Robot r);
	public void robotCreated(Robot r);

	
}
