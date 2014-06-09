package implementation.gui.newUI;

import implementation.gui.GUIImpl;

import java.awt.Dimension;

import javax.swing.JButton;

public class JBZone extends JButton {

	public JBZone(){
		this.setBackground(GUIImpl.colorZoneTo);
		this.setSize(new Dimension(8,8));
		this.setPreferredSize(new Dimension(8,8));
		this.setEnabled(false);
		this.setBorder(null);
	}
}
