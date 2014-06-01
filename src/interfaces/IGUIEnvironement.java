package interfaces;

import java.util.Map;

import objet.Box;
import objet.Obstacle;
import objet.Position;
import MainSys.EcoRobot.Robot;

public interface IGUIEnvironement {
	public Map<Robot, Position> getRobots();
	public Map<Box, Position> getBoxes();
	public Map<Obstacle, Position> getObstacles();
}
