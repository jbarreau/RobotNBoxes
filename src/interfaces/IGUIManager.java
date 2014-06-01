package interfaces;

import java.io.Serializable;

public interface IGUIManager {
	public void pause();
	public void stop();
	public void play();
	public void speed(double d);// normal speed is 1
	public void changeCorridor();
	public Serializable saveState();
	public void loadState(Serializable state);

}
