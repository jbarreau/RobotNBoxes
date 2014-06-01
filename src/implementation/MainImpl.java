package implementation;

import java.util.ArrayList;
import java.util.List;

import MainSys.EcoRobot;
import MainSys.EcoRobot.Robot;
import MainSys.Environement;
import MainSys.GUI;
import MainSys.GameManager;
import MainSys.MainSys;



public class MainImpl extends MainSys{
	private RequiringCompSpecies requiringCompSpecies;
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
	protected void start() {
		super.start();
		requiringCompSpecies = new RequiringCompSpeciesImpl();
		newRequiringCompSpecies();
	}


	protected RequiringCompSpecies make_RequiringCompSpecies() {
		// TODO Auto-generated method stub
		System.out.println("creating RequiringCompSpecies");
		return requiringCompSpecies;
	}

	@Override
	protected EcoRobot make_ecor() {
		// TODO Auto-generated method stub
		System.out.println("creating EcoRobot");
		return new EcoRobotImpl();
	}

}
