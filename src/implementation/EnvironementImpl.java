package implementation;

import interfaces.IGUIEnvironement;
import interfaces.IRobotEnvironement;
import interfaces.MapObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import objet.Box;
import objet.Obstacle;
import objet.Position;
import Environement.Environement;
import Robot.Robot;

public class EnvironementImpl extends Environement {
	
	Map<Robot, Position> robotMap = new HashMap<Robot, Position>();
	Map<Box, Position> boxMap = new HashMap<Box, Position>();
	Map<Obstacle, Position> obstacleMap = new HashMap<Obstacle, Position>();

	@Override
	protected IGUIEnvironement make_gui() {
		// TODO Auto-generated method stub
		return new IGUIEnvironement() {
			
			@Override
			public Map<Position, MapObject> update() {
				 Map<Position, MapObject> ret = new HashMap<Position, MapObject>();

				 //None
				 for (int i = 0 ; i< 80; i++){
					 for (int j = 0 ; j < 30 ;  j++){
						 	ret.put(new Position(i ,j), MapObject.None);
					 }
				 }
				 //box
				 for (int i = 0 ; i< 10 ; i++){
					 for (int j = 5 ; j < 20 ;  j++){
						 	System.out.println("Box : " + i +" "+ j);
						 	ret.put(new Position(i, j ), MapObject.Box);
					 }
				 }
				 //obstacles
				 for (int i = 25 ; i< 53 ; i++){
					 for (int j = 1 ; j < 29 ;  j++){
						 	System.out.println("obstacles : " + i +" "+ j);
						 	ret.put(new Position(i, j ), MapObject.Obstacle);
					 }
				 }
				 //robots
				 Random r = new Random();
				 r.setSeed(r.nextInt());;
			 	 int nbR = 0;
				 while(nbR < 80){
					 	int rI = r.nextInt(15)+10;
					 	int rJ = r.nextInt(30);
						 System.out.println("robot : " + rI +" "+ rJ);
					 	ret.put(new Position(rI, rJ ), MapObject.RobotEmpty);
					 	nbR ++;
				 }
				 return ret;
			}
		};
	}
	
	protected IRobotEnvironement make_robotEnvironement() {
		return new IRobotEnvironement() {
			
			@Override
			public void robotTakeBox(Robot r, Box b, int x, int y) {
				Position p = new Position(x, y);
				boxMap.remove(b);
			}
			
			@Override
			public void robotPutBox(Robot r, Box b, int x, int y) {
				Position p = new Position(x, y);
				boxMap.put(b, p);
			}
			
			@Override
			public void robotMoved(Robot r, int x, int y) {
				Position p = new Position(x, y);
				robotMap.put(r, p);
			}
			
			@Override
			public void robotKillHimself(Robot r, int x, int y) {
				Position p = new Position(x, y);
				robotMap.remove(r);
				
			}
			
			@Override
			public void robotCreated(Robot r, int x, int y) {
				Position p = new Position(x, y);
				robotMap.put(r, p);
			}
			
			@Override
			public Robot[] findForRobotsWithBox(Robot r) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Robot[] findForRobots(Robot r) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Box[] findForBoxes(Robot r) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public Obstacle[] findForObstacles(Robot r) {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
