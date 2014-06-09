package implementation.gui.newUI;

import implementation.gui.GUIImpl;
import implementation.robot.Robot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class JBRobotEmpty extends JBRobotAbstract {
	private MapView mapView;

	public JBRobotEmpty(Robot robot, MapView Panel){
		super(robot);
		mapView = Panel;
		this.setBackground(GUIImpl.colorRobot);
		this.setSize(new Dimension(8,8));
		this.setPreferredSize(new Dimension(8,8));
		
		this.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("click");
				mapView.selectRobots(JBRobotEmpty.this);
				JLabel lab;
				if(ref == null)
					lab= new JLabel("test");
				else{
					lab = new JLabel(ref.toString());
				}
				lab.setVisible(true);
				mapView.detailPanel.add(lab);
				mapView.detailPanel.setVisible(true);			
			}
		});
		this.setEnabled(true);
	}
}
