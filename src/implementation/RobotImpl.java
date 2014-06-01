package implementation;

import interfaces.IConnaissance;
import interfaces.IRunner;

import java.awt.Robot;

import MainSys.EcoRobot;

public class RobotImpl extends MainSys.EcoRobot.Robot {
	public RobotImpl(int id, String descr){
		
	}

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
		};
	}

	/**/

}
