package implementation.gui;

import implementation.robot.Robot;
import interfaces.MapObject;

import java.util.Map;

import objet.Position;

public interface GUI {
	public Map<Position, MapObject> update();
	public Map<Position, Robot> getRobotsWithPosition();
}
