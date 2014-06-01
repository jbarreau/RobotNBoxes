package interfaces;

import java.util.Map;

import objet.Box;
import objet.Obstacle;
import MainSys.MainSys.Robot;

public interface IGUIEnvironement {
	public Map<Robot, MapObject> getRobots();
	public Map<Box, MapObject> getBoxes();
	public Map<Obstacle, MapObject> getObstacles();
	public String getLog(int robotId);

}
