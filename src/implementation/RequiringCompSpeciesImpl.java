package implementation;

import java.util.ArrayList;
import java.util.List;

import implementation.gui.GUIImpl;
import interfaces.IMain;
import MainSys.EcoRobot.Robot;
import MainSys.Environement;
import MainSys.GUI;
import MainSys.GameManager;
import MainSys.MainSys.RequiringCompSpecies;

public class RequiringCompSpeciesImpl extends RequiringCompSpecies {
	private GUI gui ;
	private GameManager gameManager ;
	private Environement environement ;
	private List<Robot> robots = new ArrayList<Robot>();

	private static int robot_id = 1;
	
	public static int getNewRobotId(){
		robot_id ++;
		return robot_id-1;
	}
	@Override
	protected IMain make_main() {
		// TODO Auto-generated method stub
		return new IMain() {
			
			@Override
			public Robot createRobot() {
				// TODO Auto-generated method stub
				int k = getNewRobotId();
				return new RobotImpl(k, "robot num "+k);
			}
		};
	}

	@Override
	protected GUI make_gui() {
		gui = new GUIImpl();
		return gui;
	}

	@Override
	protected GameManager make_gameManager() {
		gameManager = new GameManagerImpl();
		return gameManager;
	}

	@Override
	protected Environement make_environement() {
		// TODO Auto-generated method stub
		for (int i = 0 ; i < 50 ; i++){
			Robot r = provides().main().createRobot();
			robots.add(r);
		}
		return new EnvironementImpl(robots);
	}

}
