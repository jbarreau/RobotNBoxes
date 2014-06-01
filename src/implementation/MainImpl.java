package implementation;

import implementation.gui.GUIImpl;
import Environement.Environement;
import MainSys.GUI;
import MainSys.GameManager;
import MainSys.MainSys;



public class MainImpl extends MainSys{
	private GUI gui ;
	private GameManager gameManager ;
	private Environement environement ;
	@Override
	protected Environement make_environement() {
		environement = new EnvironementImpl();
		return environement;
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
	protected Robot make_Robot() {
		MainSys.Robot robot = new MainSys.Robot(){
			
		};
		return robot;
	}

}
