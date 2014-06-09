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

	
}