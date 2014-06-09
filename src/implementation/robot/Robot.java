package implementation.robot;

import interfaces.IConnaissance;
import interfaces.IRunner;

import java.io.Serializable;

/**
 * Created by julien on 07/06/14.
 */
public abstract class Robot implements IConnaissance, IRunner, Serializable {
	public abstract boolean equals(Object obj) ;
	public abstract int hashCode() ;
}
