package implementation;
/*
import java.util.ArrayList;
import java.util.List;

import implementation.environement.Environement;
import implementation.gameManager.GameManager;
import implementation.gameManager.GameManagerImpl;
import implementation.gui.GUIImpl;
import MainSys.EcoRobot.Robot;
import MainSys.Environement;
import MainSys.GUI;
import MainSys.GameManager;
import MainSys.MainSys.RequiringCompSpecies;
import implementation.robot.Robot;
import implementation.robot.RobotImpl;

public class RequiringCompSpeciesImpl extends RequiringCompSpecies {
	private GUI gui ;
	private GameManager gameManager ;
	private Environement environement ;
	private List<Robot> robots = new ArrayList<Robot>();
	
	public RequiringCompSpeciesImpl(){
		System.out.println("RequiringCompSpeciesImpl created");
	}

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
		for (int i = 0 ; i < 50 ; i++){
			Robot r = provides().main().createRobot();
			robots.add(r);
		}
		environement = new EnvironementImpl(robots);
		System.out.println("creating env");
		return environement;
	}

}
*/