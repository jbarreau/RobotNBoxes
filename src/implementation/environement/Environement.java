package implementation.environement;

import implementation.robot.Robot;
import implementation.robot.RobotImpl;
import interfaces.IEnvManager;
import interfaces.IEnvironement;
import interfaces.IGUIEnvironement;
import interfaces.IRobotEnvironement;
import objet.Box;
import objet.Obstacle;
import objet.Position;

import java.io.Serializable;
import java.util.*;

/**
 * Created by julien on 07/06/14.
 */
public class Environement implements IEnvironement, IGUIEnvironement, IEnvManager, IRobotEnvironement, Serializable {
    int boxesUp = 0;
    int boxesDown = 0;
    List<Robot> robots = new LinkedList<Robot>();
    Map<Robot, Position> robotMap = new HashMap<Robot, Position>();
    Map<Box, Position> boxMap = new HashMap<Box, Position>();
    Map<Obstacle, Position> obstacleMap = new HashMap<Obstacle, Position>();
    private Random r = new Random(Map.class.hashCode());
    private static int ids = 1;
    private int nextRobotY = 7;
    private int robotX = 7;

    public static int nextId() {
        ids++;
        return ids - 1;
    }

    private static Environement instance = null;

    public static Environement getInstance() {
        if (instance == null) {
            instance = new Environement();
        }
        return instance;
    }

    public Environement() {
        //box
        for (int i = 0; i < 10; i++) {
            for (int j = 5; j < 25; j++) {
                boxMap.put(new Box(), new Position(i, j));
            }
        }
        // Obstacles
        for (int i = 25; i < 55; i++) {
            for (int j = 1; j < 29; j++) {
                obstacleMap.put(new Obstacle(), new Position(i, j));
            }
        }
    }

    @Override
    public Map<Robot, Position> getRobotsGUI() {
        return robotMap;
    }

    @Override
    public List<Robot> getRobots() {
        return robots;
    }

    @Override
    public Robot createRobot() {
    	
    	/*Robot r = new RobotImpl(new Position(robotX, nextRobotY), Environement.getInstance(), nextId());
    	nextRobotY++;*/
    	Random rand = new Random();
    	int x = 10 + rand.nextInt(25 - 10);
    	int y = 1 + rand.nextInt(30 - 1);
    	Robot r = new RobotImpl(new Position(x, y), Environement.getInstance(), nextId());
        robots.add(r);
        return r;
    }

    @Override
    public Map<Box, Position> getBoxes() {
        return boxMap;
    }

    @Override
    public Map<Obstacle, Position> getObstacles() {
        return obstacleMap;
    }

    @Override
    public void changeCorridor() {
        randomCorridor();
    }

    @Override
    public Serializable SerializeSystem() {
        List<Serializable> ret = new ArrayList<Serializable>();
        ret.add(new Position(0, 0));
        ret.add(new Position(1, 1));
        ret.add(new Position(2, 2));
        ret.add(new Position(3, 3));
        //return (Serializable)ret;
        return this;
    }

    @Override
    public void unserializeSystem(Serializable save) {
        instance = (Environement) save;
    }

    private void randomCorridor() {
        obstacleMap.clear();
        int newYCorr_1 = r.nextInt(30);
        r.nextInt(30);
        r.nextInt(30);
        int newYCorr_2 = r.nextInt(30);

        for (int i = 25; i < 55; i++) {
            for (int j = 0; j < 30; j++) {
                if (j != newYCorr_1 && j != newYCorr_2) {
                    obstacleMap.put(new Obstacle(), new Position(i, j));
                }
            }
        }
    }

    private Robot getRobotKey(Map<Robot, Position> map, Position p) {
        Robot key = null;
        for (Robot mapKey : map.keySet()) {
            if (map.get(mapKey).equals(p)) {
                key = mapKey;
                break;
            }
        }
        return key;
    }

    private Box getBoxKey(Map<Box, Position> map, Position p) {
        Box key = null;
        for (Box mapKey : map.keySet()) {
            if (map.get(mapKey).equals(p)) {
                key = mapKey;
                break;
            }
        }
        return key;
    }

    private Obstacle getObstacleKey(Map<Obstacle, Position> map, Position p) {
        Obstacle key = null;
        for (Obstacle mapKey : map.keySet()) {
            if (map.get(mapKey).equals(p)) {
                key = mapKey;
                break;
            }
        }
        return key;
    }

    @Override
    public Map<Position, Object> getPerception(Robot r, int x, int y) {
		Map<Position, Object> perceptionMap = new HashMap<Position, Object>();
		for (int i = x-3 ; i < x+3 ; i++) {
			for (int j = y-3 ; j < y+3 ; j++) {
				Position p = new Position(i, j);
				if(robotMap.containsValue(p)) {
					perceptionMap.put(p, getRobotKey(robotMap, p));
				}
				else if(boxMap.containsValue(p)) {
					perceptionMap.put(p, getBoxKey(boxMap, p));
				}
				else if(obstacleMap.containsValue(p)) {
					perceptionMap.put(p, getObstacleKey(obstacleMap, p));
				}
				else {
					perceptionMap.put(p, null);
				}
			}
		}
		return perceptionMap;
	}

    @Override
    public void robotMoved(Robot robot, int x, int y) {
        robotMap.get(robot).setX(x);
        robotMap.get(robot).setX(y);
    }

    @Override
    public void robotTakeBox(Robot robot, Box b) {
    	boxMap.remove(b);
    }

    @Override
    public void robotPutBox(Robot robot, Box b) {
    	
    }

    @Override
    public void robotKillHimself(Robot robot) {
    	robotMap.remove(robot);
    }

    @Override
    public void robotCreated(Robot robot) {
    	robotMap.put(robot, robot.getPosition());
    }
}
