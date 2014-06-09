package implementation.robot;

import implementation.environement.Environement;
import objet.Box;
import objet.Position;

import java.util.Random;


public class RobotImpl extends Robot {
    private Environement environement;
    private Position position;
    private Box box = null;
    private static Random r;
    private int id;

    public RobotImpl(Position Pposition, Environement e, int Pid) {
        position = Pposition;
        environement = e;
        r = new Random(environement.getRobotsGUI().hashCode());
        id = Pid;
    }

    @Override
    public String getLog() {
        return "";
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Box getBox() {
        return box;
    }

    @Override
    public void play() {
        //System.out.println("play" + id);
        int t = r.nextInt(4);
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
            if (position.getX() < 80 -1)
                position.setX(position.getX() + 1);
        }
    }
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
