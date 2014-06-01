package implementation;

import implementation.gui.GUIImpl;
import interfaces.IMain;

import java.util.ArrayList;
import java.util.List;

import Environement.Environement;
import MainSys.GUI;
import MainSys.GameManager;
import MainSys.MainSys;



public class MainImpl extends MainSys{
	private GUI gui ;
	private GameManager gameManager ;
	private Environement environement ;
	private List<Robot> robots = new ArrayList<Robot>();

	private int robot_id = 1;
	
	private int getNewRobotId(){
		robot_id ++;
		return robot_id-1;
	}
	
	
	public MainImpl() {
		
	}
	
	@Override
	protected Environement make_environement() {
		environement = new EnvironementImpl(robots);
		System.out.println("new environement");
		return environement;
	}

	@Override
	protected GUI make_gui() {
		gui = new GUIImpl();
		System.out.println("new gui");
		return gui;
	}

	@Override
	protected GameManager make_gameManager() {
		gameManager = new GameManagerImpl();
		System.out.println("new game manager");
		return gameManager;
	}
	@Override
	protected Robot make_Robot(int id, String descr) {
		Robot robot = new RobotImpl(id, descr);
		robots.add(robot);
		System.out.println("new robot");
		return robot;

	}
	
	@Override
	protected void start() {
		// TODO Auto-generated method stub
		super.start();
		for (int i = 0 ; i < 50 ; i++){
			int idd = getNewRobotId();
			newRobot(idd, "robot num "+idd);	
		}
	}

	@Override
	protected IMain make_main() {
		// TODO Auto-generated method stub
		return new IMain() {
			
			@Override
			public void createRobot() {
				int i = getNewRobotId();
				newRobot(i, "robot num "+i);
				
			}
		};
	}


}
