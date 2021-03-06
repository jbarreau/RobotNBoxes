package implementation;

import interfaces.IEnvManager;
import interfaces.IGUIEnvironement;
import interfaces.IRobotEnvironement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import objet.Box;
import objet.Obstacle;
import objet.Position;
import MainSys.EcoRobot.Robot;
import MainSys.Environement;

public class EnvironementImpl extends Environement {
	
	int boxesUp = 0;
	int boxesDown = 0;
	Map<Robot, Position> robotMap = new HashMap<Robot, Position>();
	Map<Box, Position> boxMap = new HashMap<Box, Position>();
	Map<Obstacle, Position> obstacleMap = new HashMap<Obstacle, Position>();
	private Random r = new Random( Map.class.hashCode() );
	
	public EnvironementImpl(List<Robot> robots) {
		// Robots
		Random r = new Random();
		r.setSeed(r.nextInt());
		for(Robot robot : robots) {
			int rI = r.nextInt(15)+10;
			int rJ = r.nextInt(30);
			robotMap.put(robot, new Position(rI, rJ ));
		}
		//box
		for (int i = 0 ; i< 10 ; i++){
			for (int j = 5 ; j < 25 ; j++){
				boxMap.put(new Box(), new Position(i, j ));
			}
		}
		// Obstacles
		for (int i = 25 ; i < 55 ; i++){
			for (int j = 1 ; j < 29 ; j++){
				obstacleMap.put(new Obstacle(), new Position(i, j));
			}
		}
	}
	
	private void randomCorridor() {
		obstacleMap.clear();
		int newYCorr_1 = r.nextInt(30);
		r.nextInt(30);r.nextInt(30);
		int newYCorr_2 = r.nextInt(30); 
		
		for (int i = 25 ; i < 55 ; i++){
			for (int j = 0 ; j < 30 ; j++){
				if (j != newYCorr_1 && j != newYCorr_2){
					obstacleMap.put(new Obstacle(), new Position(i, j));
				}
			}
		}
	}
	
	private Robot getRobotKey(Map<Robot, Position> map, Position p) {
		Robot key = null;
		for(Robot mapKey : map.keySet()) {
			if(map.get(mapKey).equals(p)) {
				key =  mapKey;
				break;
			}
		}
		return key;
	}
	
	private Box getBoxKey(Map<Box, Position> map, Position p) {
		Box key = null;
		for(Box mapKey : map.keySet()) {
			if(map.get(mapKey).equals(p)) {
				key =  mapKey;
				break;
			}
		}
		return key;
	}
	
	private Obstacle getObstacleKey(Map<Obstacle, Position> map, Position p) {
		Obstacle key = null;
		for(Obstacle mapKey : map.keySet()) {
			if(map.get(mapKey).equals(p)) {
				key =  mapKey;
				break;
			}
		}
		return key;
	}
	
	@Override
	protected IGUIEnvironement make_gui() {
		// TODO Auto-generated method stub
		return new IGUIEnvironement() {

			@Override
			public Map<Robot, Position> getRobots() {
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
		};
	}
	@Override
	protected IEnvManager make_gameManager() {
		// TODO Auto-generated method stub
		return  new IEnvManager() {
			
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
				return (Serializable)ret;
			}

			@Override
			public void unserializeSystem(Serializable save) {
				List<Position> out = (List<Position>) save;
				for (Position p : out){
					System.out.println("p : "+p.getX()+","+p.getY());
				}
				
			}
		};
	}

	@Override
	protected IRobotEnvironement make_robot() {
		// TODO Auto-generated method stub
		return new IRobotEnvironement() {
			
			@Override
			public void robotTakeBox(Robot r, Box b, int x, int y) {
				Position p = new Position(x, y);
				boxMap.remove(b);
				boxesUp++;
			}
			
			@Override
			public void robotPutBox(Robot r, Box b, int x, int y) {
				Position p = new Position(x, y);
				boxesDown++;
			}
			
			@Override
			public void robotMoved(Robot r, int x, int y) {
				Position p = new Position(x, y);
				robotMap.put(r, p);
			}
			
			@Override
			public void robotKillHimself(Robot r) {
				robotMap.remove(r);
			}
			
			@Override
			public void robotCreated(Robot r, int x, int y) {
				Position p = new Position(x, y);
				robotMap.put(r, p);
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
		};
	}

}
