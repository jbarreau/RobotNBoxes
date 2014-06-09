package interfaces;

import implementation.robot.Robot;

import java.util.Map;

import objet.Box;
import objet.Obstacle;
import objet.Position;

public interface IGUIEnvironement {
    public Map<Robot, Position> getRobotsGUI();

    public Map<Box, Position> getBoxes();

    public Map<Obstacle, Position> getObstacles();
}
