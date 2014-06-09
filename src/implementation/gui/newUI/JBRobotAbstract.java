package implementation.gui.newUI;

import implementation.robot.Robot;

import javax.swing.JButton;

public abstract class JBRobotAbstract extends JButton {
	public Robot ref = null;
	
	public JBRobotAbstract(Robot robot){
		ref = robot;
	}

	
}