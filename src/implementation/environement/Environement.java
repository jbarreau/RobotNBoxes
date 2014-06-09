package implementation.environement;

import implementation.robot.Robot;
import implementation.robot.RobotImpl;
import interfaces.IEnvManager;
import interfaces.IEnvironement;
import interfaces.IGUIEnvironement;
import interfaces.IRobotEnvironement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.sun.corba.se.spi.ior.MakeImmutable;

import objet.Box;
import objet.Obstacle;
import objet.Position;

/**
 * Created by julien on 07/06/14.
 */
public class Environement implements IEnvironement, IGUIEnvironement, IEnvManager, IRobotEnvironement, Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5331068523188525395L;
	int boxesUp = 0;
    int boxesDown = 0;
    //List<Robot> robots = new LinkedList<Robot>();
    Map<Robot, Position> robotMap = new HashMap<Robot, Position>();
    Map<Box, Position> boxMap = new HashMap<Box, Position>();
    Map<Obstacle, Position> obstacleMap = new HashMap<Obstacle, Position>();
    private Random r = new Random(Map.class.hashCode());
    private static int ids = 1;

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
        	if ( mapKey.getPosition().equals(p)) {
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
	public Map<Position, Object> getPerception(Robot robot) {
		Map<Position, Object> perceptionMap = new HashMap<Position, Object>();
		for (int i = robot.getPosition().getX()-3 ; i < robot.getPosition().getX()+3 ; i++) {
			for (int j = robot.getPosition().getY()-3 ; j < robot.getPosition().getY()+3 ; j++) {
				Position p = new Position(i, j);
				if(getRobotKey(robotMap, p) != null) {
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
	public void robotMoved(Robot robot) {
		System.out.println("testeeeee  "+robot.getPosition().getX()+" "+ robot.getPosition().getY());
		robotMap.put(robot, robot.getPosition());
	}

	@Override
	public void robotTakeBox(Robot robot, Box b) {
		boxMap.remove(b);
		boxesUp++;
	}

	@Override
	public void robotPutBox(Robot robot, Box b) {
		boxesDown++;
	}

	@Override
	public void robotKillHimself(Robot robot) {
		robotMap.remove(robot);
		
	}

	
	@Override
	public void robotCreated(Robot robot) {
		robotMap.put(robot, robot.getPosition());
	}

	@Override
	public void changeCorridor() {
		randomCorridor();
	}

	@Override
    public Serializable SerializeSystem() {
        return this;
    }

    @Override
    public void unserializeSystem(Serializable save) {
        instance = (Environement) save;
    }

	@Override
	public Map<Robot, Position> getRobotsGUI() {
		return robotMap;
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
	public void createRobot() {
		Position p = new Position(r.nextInt(30),r.nextInt(25));
		while(robotMap.containsValue(p) || boxMap.containsValue(p) || obstacleMap.containsValue(p)) {
			p = new Position(r.nextInt(30),r.nextInt(25));
		}
		Robot robot = new RobotImpl(p, this, nextId());
		robotMap.put(robot, p);
	}

	@Override
	public List<Robot> getRobots() {
		List<Robot> robots = new ArrayList<Robot>();
		for(Robot robot:robotMap.keySet()) {
			robots.add(robot);
		}
		return robots;
	}

}
