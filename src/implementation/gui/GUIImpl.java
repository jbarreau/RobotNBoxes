package implementation.gui;


import interfaces.MapObject;

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

import objet.Position;
import MainSys.GUI;
 
public class GUIImpl extends GUI {

	private JFrame frmSmaalViewer;
	private JToolBar toolBar;
	private JButton JBPause;
	private JButton JBStop;
	private JButton JBPlay;
	private JButton JBChange;
	private JSlider JBSpeed;
	private JLabel lblCurrentSpeed;
	private JLabel labelPlus;
	private JLabel labelMoin;
	private JButton load;
	private JButton save;
	private JToolBar toolBar_1;
	private MapView mapView;
	
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

	public Map<Position, MapObject> update(){
		return null;//requires().environnement().update();
	}
	
	/**
	 * Create the application.
	 */
	public GUIImpl() {
		initialize();
		frmSmaalViewer.setVisible(true);
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
		gridBagLayout.rowHeights = new int[] {28, 23, 30, 1};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
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
		
				JBPause = new JButton("pause");
				JBPause.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						requires().gameManager().pause();
					}
				});
				JBPlay = new JButton("play");
				JBPlay.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						requires().gameManager().play();
					}
				});
				JBStop = new JButton("stop");
				JBStop.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						requires().gameManager().stop();
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
							requires().gameManager().speed(0.5);
						}else{
							lblCurrentSpeed.setText("current speed : "+JBSpeed.getValue()+",0");
							requires().gameManager().speed(JBSpeed.getValue());
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
		
		mapView = new MapView(0, 0, this);
		GridBagConstraints gbc_mapView = new GridBagConstraints();
		gbc_mapView.insets = new Insets(0, 0, 5, 0);
		gbc_mapView.fill = GridBagConstraints.BOTH;
		gbc_mapView.gridx = 0;
		gbc_mapView.gridy = 2;
		frmSmaalViewer.getContentPane().add(mapView, gbc_mapView);
		JBChange.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				requires().gameManager().changeCorridor();
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
						requires().gameManager().loadState(state);
					}catch(IOException i)
					{
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
						Serializable state = requires().gameManager().saveState();
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
