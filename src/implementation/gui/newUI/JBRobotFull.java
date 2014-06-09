package implementation.gui.newUI;

import implementation.gui.GUIImpl;
import implementation.robot.Robot;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JBRobotFull extends JBRobotAbstract{
	private MapView mapView;
	
	public JBRobotFull(Robot robot, MapView Panel){
		super(robot);
		mapView = Panel;
		this.setBackground(GUIImpl.colorRobotWithBox);
		this.setSize(new Dimension(8,8));
		this.setPreferredSize(new Dimension(8,8));
		this.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("click");
				mapView.detailPanel.add(new JLabel(ref.toString()));
				mapView.selectRobots(JBRobotFull.this);
			}
		});
		this.setEnabled(true);
	}
}
