package implementation;

import java.awt.Robot;

import interfaces.IConnaissance;
import interfaces.IRunner;
import MainSys.EcoRobot;

public class EcoRobotImpl extends EcoRobot {


	@Override
	protected Robot make_Robot() {
		int k = RequiringCompSpeciesImpl.getNewRobotId();
		return new RobotImpl(k, "robot num "+k);
		
		/*return new EcoRobot.Robot() {
			
			@Override
			protected IRunner make_runner() {
				// TODO Auto-generated method stub
				return new IRunner() {
					
					@Override
					public void play() {
						System.out.println("percevoir decider agir");
					}
				};
			}
			
			@Override
			protected IConnaissance make_connaissance() {
				// TODO Auto-generated method stub
				return new IConnaissance() {
				};
			}
		};*/
	}

}
