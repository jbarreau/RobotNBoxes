package interfaces;

import java.io.Serializable;

public interface IEnvManager {
	public void changeCorridor();
	public Serializable SerializeSystem();
	public void unserializeSystem(Serializable save );
}
