package implementation.robot;

import implementation.environement.Environement;
import objet.Box;
import objet.Position;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;


public class RobotImpl extends Robot {
    private enum State{ haveBox, noHaveBox};
    
	
	private Environement environement;
    private Position position;
    private Box box = null;
    private static Random r;
    private int id;
    private StringBuilder log;
    private State state;
    private Map<Position, Object> localEnv;
    private int maxX = 79;
    private int maxY = 28;

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
        
        //percevoir
    	localEnv = environement.getPerception(this, position.getX(), position.getY());
        
    	//decider
        newPosition = decider();
        
        //agir
        if(newPosition != null){
        	position = newPosition;
        }
 
        
        /*int t = r.nextInt(4);
>>>>>>> dev_amine
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
<<<<<<< HEAD
            if (position.getX() < 80 -1)
=======
            if (position.getX() < maxX)
>>>>>>> dev_amine
                position.setX(position.getX() + 1);
        }*/
    }
    
    boolean dansDepZon(int x, int y){   	
    	if((x >= 71 && x <= maxX) && (y >= 5 && y <= maxY) ){
    		return true;
    	}    	
    	return false; 
    }
    
    private Position decider(){    	
    	
    	Position newPosition;
    	Object obj;		
    	
		if(state == State.noHaveBox){
			newPosition = new Position (position.getX()-1, position.getY());
			if( newPosition.getX() >= 0){
				System.out.println("pass check devant");
				obj = localEnv.get(newPosition);
							
				if(obj != null && obj instanceof  Box){
					box = (Box)obj;
					environement.robotTakeBox(this, box);
					state = State.haveBox;
					return (new Position(position.getX(), position.getY()));
				}
				
				if(obj == null){		
					System.out.println("Robot "+id+"avance devant");
					return newPosition;
				}
			}
	
			newPosition = new Position (position.getX(), position.getY()+1);
			if(newPosition.getY() <= 29){
				System.out.println("pass check gauche");
				obj = localEnv.get(newPosition);
				if(obj != null && obj instanceof  Box){
					box = (Box)obj;
					environement.robotTakeBox(this,box );
					state = State.haveBox;
					return (new Position(position.getX(), position.getY()));
				}
				
				if(localEnv.get(newPosition) == null ){
	    			System.out.println("Robot "+id+"avance � gauche");
	    			return newPosition;
	    		}
			}
			
			
			newPosition = new Position (position.getX(), position.getY()-1);
			if(newPosition.getY() >= 0){
				System.out.println("pass check droite");
				obj = localEnv.get(newPosition);
				if(obj != null && obj instanceof  Box){
					box = (Box)obj;
					environement.robotTakeBox(this, box);
					state = State.haveBox;
					return (new Position(position.getX(), position.getY()));
				}			
				if( obj == null ){		
	    			System.out.println("Robot "+id+"avance � droite");
	    			return newPosition;
	    		}
			}
					
    		newPosition = new Position (position.getX()+1, position.getY());
    		if(newPosition.getX() <= maxX){
    			System.out.println("pass check recule");
    			obj = localEnv.get(newPosition);
				if(obj != null && obj instanceof  Box){
					environement.robotTakeBox(this, (Box)obj);
					state = State.haveBox;
					return (new Position(position.getX(), position.getY()));
				}
    			
    			if(localEnv.get(newPosition) == null){
        			System.out.println("Robot "+id+"recule");
        			return newPosition;
        		}
    		}
    		
		}
		else if(state == State.haveBox){
			newPosition = new Position (position.getX()+1, position.getY());
			
			if(localEnv.get(newPosition) == null && newPosition.getX() <= maxX){	
					
				if(dansDepZon(newPosition.getX(), newPosition.getY())){
					environement.robotPutBox(this, box);
					state = State.noHaveBox;
					return (new Position(position.getX(), position.getY()));
				}
				else{
					System.out.println("Robot "+id+"avance devant");
					return newPosition;
				}		
			}
			
			newPosition = new Position (position.getX(), position.getY()+1);
			if(localEnv.get(newPosition) == null && newPosition.getY() <= 29){	
				
				if(dansDepZon(newPosition.getX(), newPosition.getY())){
					environement.robotPutBox(this, box);
					state = State.noHaveBox;
					return (new Position(position.getX(), position.getY()));
				}
				else{
					System.out.println("Robot "+id+"avance � gauche");
					return newPosition;
				}	
    		}
			newPosition = new Position (position.getX(), position.getY()-1);
    		if(localEnv.get(newPosition) == null && newPosition.getY() >= 0){	
    			
    			if(dansDepZon(newPosition.getX(), newPosition.getY())){
					environement.robotPutBox(this, box);
					state = State.noHaveBox;
					return (new Position(position.getX(), position.getY()));
				}
				else{
					System.out.println("Robot "+id+"avance � droite");
	    			return newPosition;
				}	
    					
    		}
    		newPosition = new Position (position.getX()-1, position.getY());
			if(localEnv.get(newPosition) == null && newPosition.getX() >= 0){
				if(dansDepZon(newPosition.getX(), newPosition.getY())){
					environement.robotPutBox(this, box);
					state = State.noHaveBox;
					return (new Position(position.getX(), position.getY()));
				}
				else{
					System.out.println("Robot "+id+"avance devant");
					return newPosition;
				}				
			}
		}
     	System.out.println("robot meurt");
     	environement.robotKillHimself(this);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result
				+ ((position == null) ? 0 : position.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RobotImpl other = (RobotImpl) obj;
		if (id != other.id)
			return false;
		if (position == null) {
			if (other.position != null)
				return false;
		} else if (!position.equals(other.position))
			return false;
		return true;
	}

	    

}
