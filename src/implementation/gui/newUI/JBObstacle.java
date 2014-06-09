package implementation.gui.newUI;

import implementation.gui.GUIImpl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class JBObstacle extends JButton {

	public JBObstacle(){
		this.setBackground(GUIImpl.colorObstacle);
		this.setSize(new Dimension(8,8));
		this.setPreferredSize(new Dimension(8,8));
		this.setEnabled(false);
		this.setBorder(null);
	}
}
