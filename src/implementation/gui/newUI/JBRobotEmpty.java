package implementation.gui.newUI;

import implementation.gui.GUIImpl;
import implementation.robot.Robot;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

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
		JBRobotEmpty other = (JBRobotEmpty) obj;
		if (ref == null) {
			if (other.ref != null)
				return false;
		} else if (!ref.equals(other.ref))
			return false;
		return true;
	}
	
}
