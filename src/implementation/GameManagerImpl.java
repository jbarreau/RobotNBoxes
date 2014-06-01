package implementation;

import java.io.Serializable;

import sun.font.EAttribute;
import interfaces.IGUIManager;
import interfaces.IRunner;
import MainSys.GameManager;

public class GameManagerImpl extends GameManager {
	private boolean paused = false;
	private boolean stop = false;
	private double speed = 1;
	@Override
	protected IGUIManager make_gui() {
		// TODO Auto-generated method stub
		return new IGUIManager() {

			@Override
			public void pause() {
				paused = true;
			}

			@Override
			public void stop() {
				stop = true;
				
			}

			@Override
			public void play() {
				stop = false;
				paused = false;
			}

			@Override
			public void speed(double s) {
				speed = s;
			}

			@Override
			public void changeCorridor() {
				requires().environement().changeCorridor();
			}

			@Override
			public Serializable saveState() {
				Serializable state = requires().environement().SerializeSystem();
				return state;
			}

			@Override
			public void loadState(Serializable state) {
				requires().environement().unserializeSystem(state);
			}
		};
	}

	@Override
	protected IRunner make_runner() {
		// TODO Auto-generated method stub
		return new IRunner() {
			
			@Override
			public void play() {
				try {
					while(true){
						while ( !stop){
							while (!paused){
								Thread.sleep((long) (1000 / speed));
								provides().runner().play(); // I thing that doesn't work								
							}
							Thread.sleep(100 );
						}
						Thread.sleep(100 );
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		};
	}

}
