package implementation;

import interfaces.IRunner;
import MainSys.MainSys.Robot;

public class RobotImpl extends Robot {
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
}
