package implementation;

import implementation.gui.GUIImpl;
import interfaces.IMain;
import Environement.Environement;
import MainSys.GUI;
import MainSys.GameManager;
import MainSys.MainSys;



public class MainImpl extends MainSys{

	@Override
	protected IMain make_main() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Environement make_environement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected GUI make_gui() {
		GUI gui = new GUIImpl();
		return null;
	}

	@Override
	protected GameManager make_gameManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Robot make_Robot() {
		// TODO Auto-generated method stub
		return null;
	}

}
