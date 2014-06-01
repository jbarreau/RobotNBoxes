package implementation;

import interfaces.IEnvManager;
import interfaces.IGUIEnvironement;
import interfaces.MapObject;
import interfaces.Position;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Environement.Environement;
import MainSys.MainSys.Robot;

public class EnvironementImpl extends Environement{
	private int robot_id = 1;
	private Random r = new Random( Map.class.hashCode() );
	private Map<Position, MapObject> corridors = new HashMap<Position, MapObject>();
	private List<Robot> robots;
	
	public EnvironementImpl(List<Robot> Probots){
		robots = Probots;
		for (int i = 25 ; i < 55 ; i++){
			for (int j = 1 ; j < 29 ; j++){
				corridors.put(new Position(i, j), MapObject.Obstacle);
			}
		}
	}
	private void randomCorridor(){
		corridors.clear();
		int newYCorr_1 = r.nextInt(30);
		r.nextInt(30);r.nextInt(30);
		int newYCorr_2 = r.nextInt(30); 
		
		for (int i = 25 ; i < 55 ; i++){
			for (int j = 0 ; j < 30 ; j++){
				if (j != newYCorr_1 && j != newYCorr_2){
					corridors.put(new Position(i, j), MapObject.Obstacle);
				}
			}
		}
	}
	
	private int getNewRobotId(){
		robot_id ++;
		return robot_id-1;
	}
	
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
					 for (int j = 5 ; j < 25 ;  j++){
						 	ret.put(new Position(i, j ), MapObject.Box);
					 }
				 }
				 //Obstacles
				 for (Position p : corridors.keySet()){
					 ret.put(p, MapObject.Obstacle);
				 }
				 //robots
			 	 int nbR = 0;
				 while(nbR < 160){
					 	int rI = r.nextInt(15)+10;
					 	int rJ = r.nextInt(30);
					 	ret.put(new Position(rI, rJ, getNewRobotId() ), MapObject.RobotEmpty);
					 	nbR ++;
				 }
				 //Full Robots
				 r.setSeed(r.nextInt());;
			 	 int nbRF = 0;
				 while(nbRF < 160){
					 	int rI = r.nextInt(15)+(80-10-15);
					 	int rJ = r.nextInt(30);
					 	ret.put(new Position(rI, rJ, getNewRobotId() ), MapObject.RobotFull);
					 	nbRF ++;
				 }
				 return ret;
			}

			@Override
			public String getLog(int robotId) {
				// TODO Auto-generated method stub
				return null;
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
					System.out.println("p : "+p.getX()+","+p.getY()+" : "+p.getId());
				}
				
			}
		};
	}

}
