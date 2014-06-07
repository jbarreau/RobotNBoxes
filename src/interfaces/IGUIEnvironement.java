package interfaces;

import implementation.robot.Robot;
import objet.Box;
import objet.Obstacle;
import objet.Position;

import java.util.Map;

public interface IGUIEnvironement {
    public Map<Robot, Position> getRobotsGUI();

    public Map<Box, Position> getBoxes();

    public Map<Obstacle, Position> getObstacles();
}
