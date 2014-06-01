package implementation;

import MainSys.EcoRobot;
import MainSys.MainSys;



public class MainImpl extends MainSys{
	private RequiringCompSpecies requiringCompSpecies;
	
	@Override
	protected void start() {
		super.start();
		requiringCompSpecies = new RequiringCompSpeciesImpl();
		newRequiringCompSpecies();
	}



	@Override
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
