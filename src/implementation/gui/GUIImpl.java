package implementation.gui;


import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;

import MainSys.GUI;
 
public class GUIImpl extends GUI {

	private JFrame frame;
	private MapView mapView;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIImpl window = new GUIImpl();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIImpl() {
		initialize();
		//main(null);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{434, 0};
		gridBagLayout.rowHeights = new int[]{130, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		System.out.println("height : " +frame.getContentPane().getLayout());
		
		mapView = new MapView(400, 200);
		GridBagConstraints gbc_mapView = new GridBagConstraints();
		gbc_mapView.insets = new Insets(0, 0, 5, 0);
		gbc_mapView.fill = GridBagConstraints.BOTH;
		gbc_mapView.gridx = 0;
		gbc_mapView.gridy = 0;
		frame.getContentPane().add(mapView, gbc_mapView);
		
	}
}
