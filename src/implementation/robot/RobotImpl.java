package implementation.robot;

import implementation.environement.Environement;
import objet.Box;
import objet.Position;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;


public class RobotImpl extends Robot {
    private enum State{ haveBox, noHaveBox};
    
	
	private Environement environement;
    private Position position;
    private Box box = null;
    private static Random r;
    private int id;
    private StringBuilder log;
    private State state;

    public RobotImpl(Position Pposition, Environement e, int Pid) {
        position = Pposition;
        environement = e;
        r = new Random(environement.getRobotsGUI().hashCode());
        id = Pid;
        log = new StringBuilder(" ");
        state = State.noHaveBox;
    }

    @Override
    public String getLog() {
        return log.toString();
    }
    
    public void appendLog(String text){
    	log.append(text);
    }
    
    @Override
    public Position getPosition() {
        return position;
    }
    
    public boolean getState(){
    	if(state == State.noHaveBox)
    		return false;
    	return true;
    }
    public int get(){
    	return 0;
    }
    @Override
    public Box getBox() {
        return box;
    }

    @Override
    public void play() {	
        System.out.println("play" + id);
        Position newPosition;
        newPosition = avancer();
        
        if(newPosition != null){
        	position.setX(newPosition.getX());
        	position.setY(newPosition.getY());
        }
        
        /*int t = r.nextInt(4);
        if (t == 0) {//gauche
            if (position.getX() > 0)
                position.setX(position.getX() - 1);
        } else if (t == 1) { //haut
            if (position.getY() > 0)
                position.setY(position.getY() - 1);
        } else if (t == 2) {//bas
            if (position.getY() < 29)
                position.setY(position.getY() + 1);
        } else if (t == 3) {//droite
            if (position.getX() < 80)
                position.setX(position.getX() + 1);
        }*/
    }
    
    private Position avancer(){    	
    	Map<Position, Object> localEnv;
    	Position newPosition;
    	Box b;
    	
    	// get localEnv
    	localEnv = environement.getPerception(this, position.getX(), position.getY());
    		
    	
		if(state == State.noHaveBox){
			newPosition = new Position (position.getX()-1, position.getY());
			if(localEnv.get(newPosition) == null && newPosition.getX() > 0){		
				System.out.println("Robot "+id+"avance devant");
				return newPosition;
			}
			newPosition = new Position (position.getX(), position.getY()-1);
			if(localEnv.get(newPosition) == null && newPosition.getY() > 0){		
    			System.out.println("Robot "+id+"avance à droite");
    			return newPosition;
    		}
			newPosition = new Position (position.getX(), position.getY()+1);
    		if(localEnv.get(newPosition) == null && newPosition.getY() < 29){
    			System.out.println("Robot "+id+"avance à gauche");
    			return newPosition;
    		}
    		newPosition = new Position (position.getX()+1, position.getY());
    		if(localEnv.get(newPosition) == null && newPosition.getX() < 80){
    			System.out.println("Robot "+id+"recule");
    			return newPosition;
    		}
		}
		else if(state == State.haveBox){
			newPosition = new Position (position.getX()+1, position.getY());
			if(localEnv.get(newPosition) == null && newPosition.getX() < 80){		
				System.out.println("Robot "+id+"avance devant");
				return newPosition;
			}
			newPosition = new Position (position.getX(), position.getY()+1);
			if(localEnv.get(newPosition) == null && newPosition.getY() < 29){		
    			System.out.println("Robot "+id+"avance à gauche");
    			return newPosition;
    		}
			newPosition = new Position (position.getX(), position.getY()-1);
    		if(localEnv.get(newPosition) == null && newPosition.getY() > 0){		
    			System.out.println("Robot "+id+"avance à droite");
    			return newPosition;
    		}
    		newPosition = new Position (position.getX()-1, position.getY());
			if(localEnv.get(newPosition) == null && newPosition.getX() > 0){		
				System.out.println("Robot "+id+"avance devant");
				return newPosition;
			}
		}
     	System.out.println("robot meurt");
    	return null;
    }
/*    public void play(){
    	
    	
    	
    	
    	    	
    	if(state == State.noHaveBox){
    		
    		position.setX(position.getX()+1);
    	}
    	else if(state == State.haveBox){
    		position.setX(position.getX()-1);
    	}
    }*/
/*
    @Override
	protected IRunner make_runner() {
		// TODO Auto-generated method stub
		return new IRunner() {
			
			@Override
			public void play() {
				// TODO Auto-generated method stub
				System.out.println("play");
				//eco_parts().gameManager().
				
				
			}
		};
	}
	@Override
	protected IConnaissance make_connaissance() {
		// TODO Auto-generated method stub
		return new IConnaissance() {
            @Override
            public String getLog() {
                return "";
            }

            @Override
            public Position getPosition() {
                return position;
            }

            @Override
            public getBox getBox() {
                return box;
            }
        };
	}
*/
    /**/

}
