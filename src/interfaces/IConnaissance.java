package interfaces;

import objet.Position;
import objet.Box;

public interface IConnaissance {
    public String getLog();
    public Position getPosition();
    public Box Box(); //return null if no Box
}
