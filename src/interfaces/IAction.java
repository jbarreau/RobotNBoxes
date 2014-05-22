package interfaces;

public interface IAction {
	//moving functions
	public void goUp();
	public void goDown();
	public void goRight();
	public void goLeft();
	
	//boxes functions
	//public Box takeBox(Direction from);
	public void takeBox();
	public void putDownBox(); 
}
