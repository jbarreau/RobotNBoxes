package implementation;

import interfaces.IGUIManager;
import interfaces.IRunner;
import MainSys.GameManager;

public class GameManagerImpl extends GameManager {
	private boolean paused = false;
	private boolean stop = false;
	private int speed = 1;
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
			public void speed(int s) {
				speed = s;
			}
		};
	}

	@Override
	protected IRunner make_runner() {
		// TODO Auto-generated method stub
		return new IRunner() {
			
			@Override
			public void play() {
				while (!paused || !stop)
					try {
						Thread.sleep(1000 / speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
			}
		};
	}

}
