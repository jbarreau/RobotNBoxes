package implementation.gui.newUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import implementation.robot.Robot;

import javax.swing.JButton;
import javax.swing.JLabel;

public abstract class JBRobotAbstract extends JButton {
	public Robot ref = null;
	
	public JBRobotAbstract(Robot robot){
		ref = robot;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ref == null) ? 0 : ref.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JBRobotAbstract other = (JBRobotAbstract) obj;
		if (ref == null) {
			if (other.ref != null)
				return false;
		} else if (!ref.equals(other.ref))
			return false;
		return true;
	}
	
	
}