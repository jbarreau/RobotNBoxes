package implementation.gui;


import implementation.environement.Environement;
import implementation.gameManager.GameManager;
import implementation.robot.Robot;
import interfaces.MapObject;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToolBar;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import objet.Box;
import objet.Obstacle;
import objet.Position;


//to change UI
import implementation.gui.newUI.MapView;
//import implementation.gui.MapView;
public class GUIImpl implements GUI {
    private GameManager gameManager;

    private JFrame frmSmaalViewer;
    private JToolBar toolBar;
    private JButton JBPause;
	private JButton JBStop;
    private JButton JBPlay;
    private JButton JBAddRobot;
    private JButton JBChange;
    private JSlider JBSpeed;
    private JLabel lblCurrentSpeed;
	private JLabel labelPlus;
	private JLabel labelMoin;
	private JButton load;
	private JButton save;
	private JToolBar toolBar_1;
	private MapView mapView;
	//private MapView mapView;
	
	

	public static Color colorBackGround = Color.WHITE;
	public static Color colorBorder = Color.BLACK;
	public static Color colorBox = Color.YELLOW;
	public static Color colorRobot = Color.BLUE;
	public static Color colorRobotWithBox = Color.GREEN;
	public static Color colorObstacle = Color.GRAY;
	public static Color colorZoneFrom = Color.LIGHT_GRAY;
	public static Color colorZoneTo = colorZoneFrom;

    /**
     * Create the application.
     */
    public GUIImpl() {
        initialize();
        frmSmaalViewer.setVisible(true);
    }

    public GUIImpl(GameManager gm) {
        gameManager = gm;
        initialize();
        frmSmaalViewer.setVisible(true);
    }

    /**
     * Launch the application.
     */
    public void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUIImpl window = new GUIImpl();
                    window.frmSmaalViewer.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
	public Map<Position, Robot> getRobotsWithPosition() {
    	Map<Position, Robot> ret = new HashMap<Position, Robot>();
    	Map<Robot,Position> robots = Environement.getInstance().getRobotsGUI();

        for (Robot r : robots.keySet()) {
        	ret.put(robots.get(r), r);
        }
    	return ret;
	}

	@Override
    public Map<Position, MapObject> update() {
        Map<Position, MapObject> ret = new HashMap<Position, MapObject>();
		Map<Box,Position> boxes = getBoxes();
        Map<Robot, Position> robots = getRobots();
        Map<Obstacle, Position> obstacles = getObstacles();

        for (Robot r : robots.keySet()) {
            //if (r.)
			if (robots.get(r) == null){
				System.out.println("null pos for robot");
			}
			
			ret.put(robots.get(r), MapObject.RobotFull);
			if (r.getState() == false) {
				ret.put(robots.get(r), MapObject.RobotEmpty);
        	} else {
        		ret.put(robots.get(r), MapObject.RobotFull);
        	}
			/*if (r.getBox() == null) {
                ret.put(robots.get(r), MapObject.RobotEmpty);
            } else {
                ret.put(robots.get(r), MapObject.RobotFull);
            }*/
        }
        for (Box b : boxes.keySet()) {
            if (boxes.get(b) == null){
				System.out.println("null pos for box");
			}
			ret.put(boxes.get(b), MapObject.Box);
		}
		for (Obstacle o : obstacles.keySet()){
			if (obstacles.get(o) == null){
				System.out.println("null pos for obstacles");
			}
			ret.put(obstacles.get(o), MapObject.Obstacle);
		}
		
		return ret;
	}
	public Map<Robot,Position> getRobots(){
        List<implementation.robot.Robot> robots = Environement.getInstance().getRobots();
        Map<implementation.robot.Robot, Position> tmp = new HashMap<Robot, Position>();
        for (implementation.robot.Robot r : robots) {
            tmp.put(r, r.getPosition());
        }

        return tmp;
    }

    public Map<Box, Position> getBoxes() {
        return Environement.getInstance().getBoxes();
    }

    public Map<Obstacle, Position> getObstacles() {
        return Environement.getInstance().getObstacles();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmSmaalViewer = new JFrame();
		frmSmaalViewer.setTitle("SMA-AL Viewer");
		frmSmaalViewer.setBounds(100, 100, 550, 385);
		frmSmaalViewer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {55};
		gridBagLayout.rowHeights = new int[] {28, 23, 30};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0};
		frmSmaalViewer.getContentPane().setLayout(gridBagLayout);
		
		toolBar = new JToolBar();
		toolBar.setSize(new Dimension(550, 0));
		toolBar.setInheritsPopupMenu(true);
		toolBar.setFloatable(false);
		GridBagConstraints gbc_toolBar = new GridBagConstraints();
		gbc_toolBar.anchor = GridBagConstraints.WEST;
		gbc_toolBar.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar.gridx = 0;
		gbc_toolBar.gridy = 0;
		frmSmaalViewer.getContentPane().add(toolBar, gbc_toolBar);
        JBPlay = new JButton("play");
		JBPlay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameManager.play();
            }
        });
        JBStop = new JButton("stop");
		JBStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameManager.stop();
            }
        });
        JBAddRobot = new JButton("add robot");
        JBAddRobot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Environement.getInstance().createRobot();
            }
        });
        toolBar.add(JBAddRobot);
        
        JBPause = new JButton("pause");
        JBPause.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent arg0) {
                gameManager.pause();
            }
        });
        toolBar.add(JBPause);
        toolBar.add(JBPlay);
        toolBar.add(JBStop);

        JBSpeed = new JSlider();
		JBSpeed.setMinimumSize(new Dimension(50, 26));
		JBSpeed.setValue(1);
		JBSpeed.setMaximumSize(new Dimension(100, 20));
		JBSpeed.setMaximum(10);
		JBSpeed.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent arg0) {
				int value = JBSpeed.getValue();
				if (value == 0 ){
					lblCurrentSpeed.setText("current speed : 0,5");
                    gameManager.speed(0.5);
                } else {
                    lblCurrentSpeed.setText("current speed : " + JBSpeed.getValue() + ",0");
                    gameManager.speed(JBSpeed.getValue());
                }
            }
        });
		
		JSeparator separator = new JSeparator();
		toolBar.add(separator);
		
		labelMoin = new JLabel("-");
		toolBar.add(labelMoin);
		toolBar.add(JBSpeed);
		
		labelPlus = new JLabel("+");
		toolBar.add(labelPlus);
		
		lblCurrentSpeed = new JLabel("current speed : 1,0");
		toolBar.add(lblCurrentSpeed);
		toolBar.setInheritsPopupMenu(true);
		toolBar.setFloatable(false);
		
		toolBar_1 = new JToolBar();
		toolBar_1.setFloatable(false);
		GridBagConstraints gbc_toolBar_1 = new GridBagConstraints();
		gbc_toolBar_1.anchor = GridBagConstraints.WEST;
		gbc_toolBar_1.insets = new Insets(0, 0, 5, 0);
		gbc_toolBar_1.gridx = 0;
		gbc_toolBar_1.gridy = 1;
		frmSmaalViewer.getContentPane().add(toolBar_1, gbc_toolBar_1);
		
		save = new JButton("save");
		toolBar_1.add(save);
		
		load = new JButton("load");
		toolBar_1.add(load);
		JBChange = new JButton("change corridors");
		toolBar_1.add(JBChange);
		
		mapView = new MapView(this);
		GridBagConstraints gbc_mapView = new GridBagConstraints();
		gbc_mapView.insets = new Insets(0, 0, 5, 0);
		gbc_mapView.fill = GridBagConstraints.BOTH;
		gbc_mapView.gridx = 0;
		gbc_mapView.gridy = 2;
		frmSmaalViewer.getContentPane().add(mapView, gbc_mapView);
		JBChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
                gameManager.changeCorridor();
            }
        });
        load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				String path;
				fc.setMultiSelectionEnabled(false);
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					path = fc.getSelectedFile().getAbsolutePath();
					try {
						FileInputStream fileIn = new FileInputStream(path);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						Serializable state =  (Serializable) in.readObject();
						in.close();
						fileIn.close();
                        gameManager.loadState(state);
                    } catch (IOException i) {
                        i.printStackTrace();
					     return;
					}catch(ClassNotFoundException c)
					{
					   c.printStackTrace();
					   return;
					}
					
				}
			}
		});
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				String path;
				JFileChooser fc = new JFileChooser();
				fc.setMultiSelectionEnabled(false);
				int returnVal = fc.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION){
					path = fc.getSelectedFile().getAbsolutePath();

					ObjectOutputStream out;
					try {
                        Serializable state = gameManager.saveState();
                        FileOutputStream fileOut = new FileOutputStream(path);
                        out = new ObjectOutputStream(fileOut);
                        out.writeObject(state);
						out.close();
						fileOut.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
	}

}
