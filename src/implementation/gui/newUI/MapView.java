package implementation.gui.newUI;

import implementation.gui.GUI;
import implementation.robot.Robot;
import interfaces.MapObject;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import objet.Position;

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Component;
import java.awt.FlowLayout;

public class MapView extends JPanel {
	private static int refreshDelay = 20;//milliseconds
	
	private static int mapWidth = 80 ; //79 mesured
	private static int mapHeight = 30; // 29.6 mesured
	private GUI parent;
	private JPanel panel;
	

	private Map<Position, MapObject> data = null;
	private Map<Position, Robot> robots = null;
	private JBRobotAbstract selectedRobot = null;

	private GridLayout gridLayout;

	public JPanel detailPanel ;


	/**
	 * Create the panel.
	 */
	public MapView(GUI Pparent) {
		parent = Pparent;
		gridLayout = new GridLayout(mapHeight, mapWidth, 0, 0); 
		inizialize();
		reDraw();
		Runnable refreshDatas = new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						//repaint();
						updateUI();
						reDraw();
						Thread.sleep(refreshDelay);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		 
		Thread threadRefresh = new Thread(refreshDatas);
		threadRefresh.start();
	}
	
	
	public void inizialize(){
		setLayout(new GridLayout(2, 1, 1, 0));
		
		//panel
		panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(1, 1, 0, 0));
		
		//detail panel
		detailPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) detailPanel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		flowLayout.setAlignment(FlowLayout.LEADING);
		add(detailPanel);
		
	}

	private void reDraw(){
		data = parent.update();
		robots = parent.getRobotsWithPosition();
		
		JPanel tmp = new JPanel();
		tmp.setBounds(0,0,this.getBounds().width,this.getBounds().height);
		tmp.setLayout(gridLayout);
		//tmp.setBackground(GUIImpl.colorBackGround);
		for (int j = 0 ; j < 30 ; j++){
			for (int i = 0 ; i < 80 ; i ++){
				Position it = new Position(i, j);
				
				if (data.containsKey(it)){
					switch (data.get(it)) {
					case Box:
						tmp.add(new JBBox());
						break;
					case Obstacle:
						tmp.add(new JBObstacle());
						break;
					case RobotEmpty:
						JBRobotEmpty robotEmpty = new JBRobotEmpty(robots.get(it), this);
						if (selectedRobot!= null && selectedRobot.equals(robotEmpty) ){
							robotEmpty.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
							robotEmpty.setBackground(Color.black);
						}
						tmp.add(robotEmpty);
						break;
					case RobotFull:
						JBRobotFull robotFull = new JBRobotFull(robots.get(it), this);
						if (selectedRobot!= null && selectedRobot.equals(robotFull)){
							robotFull.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
							robotFull.setBackground(Color.black);
						}
						tmp.add(robotFull);
						break;
					case None:
						System.out.println("test");
						//zone mapHeight, mapWidth
						if ((j < mapHeight-5 && j> 5 ) && //vertical
							((i<10) || (i > mapWidth-10))){
							tmp.add(new JBZone());
						}else{
							tmp.add(new JBNone());
						}
						break;

					default:
						System.err.println("unknow object "+data.get(it).toString());
						break;
					}
				}else{
					//zone
					if ((j < mapHeight-5 && j> 5 ) && //vertical
						((i<10) || (i > mapWidth-10))){
						tmp.add(new JBZone());
					}else{
						tmp.add(new JBNone());
					}
				}
			}
		}
		remove(panel);
		panel.setVisible(true);
		add(tmp,0);
		panel = tmp;
		
	}
	
	public void selectRobots(JBRobotAbstract r){
		selectedRobot = r;
	}
	
}
