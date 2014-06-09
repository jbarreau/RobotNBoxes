package implementation.gui.newUI;

import implementation.gui.GUIImpl;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;

public class JBNone extends JButton {

	public JBNone(){
		this.setBackground(GUIImpl.colorBackGround);
		this.setSize(new Dimension(8,8));
		this.setPreferredSize(new Dimension(8,8));
		this.setBorder(null);
		this.setEnabled(false);
	}
}
