package interfaces;

import Environement.Map.Robot;

public interface IAction {
	//  moving functions
	public void goUp();
	public void goDown();
	public void goRight();
	public void goLeft();
	
	//  boxes functions
	//public void takeBox(Box b);
	public void putDownBox(); 
	
	//  fun functions
	public void killMyself();
	public Robot createRobot();
	}
