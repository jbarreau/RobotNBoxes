package implementation.gui;

import interfaces.MapObject;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Map;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import objet.Position;


public class MapView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static int refreshDelay = 500;//milliseconds
	private static Color colorBackGround = Color.WHITE;
	private static Color colorBorder = Color.BLACK;
	private static Color colorBox = Color.YELLOW;
	private static Color colorRobot = Color.BLUE;
	private static Color colorRobotWithBox = Color.GREEN;
	private static Color colorObstacle = Color.GRAY;
	private static Color colorZoneFrom = Color.LIGHT_GRAY;
	private static Color colorZoneTo = colorZoneFrom;

	
	private static int mapWidth = 80 ; //79 mesured
	private static int mapHeight = 30; // 29.6 mesured
	private int screenWidth ;
	private int screenHeight ;
	private GUIImpl parent;
	
	private double ratio ;
	
	public Map<Position, MapObject> data = null;

	private Rectangle zoneFrom = new Rectangle(0, 5, 10, mapHeight-10);
	private Rectangle zoneTo = new Rectangle(mapWidth - 10, 5, 10, mapHeight-10);

	/**
	 * Create the panel.
	 */
	public MapView(int x, int y, GUIImpl Pparent) {
		parent = Pparent;
		init(x , y);
	}
	/**
	 * @wbp.parser.constructor
	 */
	public MapView(int x, int y) {
		init(x , y);
	}
	public void init(int x , int y){
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setLayout(null);
		
		Runnable refreshDatas = new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						//repaint();
						updateUI();
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

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		data = parent.update();
		paintMap(g);
	}
	
	public void refreshScreenSize(){
		screenHeight = getHeight();
		screenWidth = getWidth();
		
		double heightRatio = screenHeight / mapHeight;
		double widthRatio = screenWidth / mapWidth;
		
		if (heightRatio < widthRatio){
			ratio = heightRatio ;
		}else{
			ratio = widthRatio ;
		}
	}

	public void paintZones(Graphics g, Rectangle map){	
		g.setColor(colorZoneFrom);
		g.fillRect(map.x + (int)(zoneFrom.x*ratio), map.y + (int)(zoneFrom.y*ratio), 
				(int)(zoneFrom.width*ratio), (int)(zoneFrom.height*ratio));

		
		g.setColor(colorZoneTo);
		g.fillRect(map.x + (int)(zoneTo.x*ratio), map.y + (int)(zoneTo.y*ratio), 
				(int)(zoneTo.width*ratio), (int)(zoneTo.height*ratio));
	}

	public void paintDatas(Graphics g, Rectangle map){
		if (data == null){
			data = parent.update();
		}
		
		for (Position p : data.keySet()){
			
			if(data.get(p) == MapObject.None) {//NONE
				g.setColor(colorBackGround);
				if (inZoneFrom(p))
					g.setColor(colorZoneFrom);
				if (inZoneTo(p))
					g.setColor(colorZoneTo);
				
				g.fillRect(map.x + (int)(p.getX()*ratio), map.y + (int)(p.getY()*ratio), 
						(int)(1*ratio), (int)(1*ratio));
			}
			else if(data.get(p) == MapObject.Box) {//BOX
				g.setColor(colorBox);
				g.fillOval(map.x + (int)(p.getX()*ratio), map.y + (int)(p.getY()*ratio), 
						(int)(1*ratio), (int)(1*ratio));
			}
			else if(data.get(p) == MapObject.Obstacle) {//OBSTACLE
				g.setColor(colorObstacle);
				g.fillRect(map.x + (int)(p.getX()*ratio), map.y + (int)(p.getY()*ratio), 
						(int)(1*ratio), (int)(1*ratio));
			}
			else if(data.get(p) == MapObject.RobotEmpty) {//ROBOT EMPTY
				g.setColor(colorRobot);
				g.fill3DRect(map.x + (int)(p.getX()*ratio), map.y + (int)(p.getY()*ratio) , 
						(int)(1*ratio), (int)(1*ratio), true);
				
			}
			else if(data.get(p) == MapObject.RobotFull) {//ROBOT FULL
				g.setColor(colorRobotWithBox);
				g.fillRect(map.x + (int)(p.getX()*ratio), map.y + (int)(p.getY()*ratio), 
						(int)(1*ratio), (int)(1*ratio));
			}
			
		}
	}
	
	private boolean inZoneTo(Position p) {
		return  (p.getX() <mapWidth && p.getX() > mapWidth -10
				&&
			p.getY()> 5 && p.getY()<mapHeight - 5);
	}
	private boolean inZoneFrom(Position p) {
		return  (p.getX() <10 && p.getX() >= 0
				&&
			p.getY()> 5 && p.getY()<mapHeight - 5);
	}

	public void paintMap(Graphics g){	
		int x;	
		int y;	
		int width;	
		int height;
		
		refreshScreenSize();
		
		width = (int) (mapWidth * ratio) + 2;
		height = (int) (mapHeight * ratio) + 2 ; //for border
		x = (int)((screenWidth - width) /2);
		y = (int)((screenHeight - height) /2);
		
		g.setColor(colorBorder);
		g.draw3DRect(x, y, width, height, false);
		
		g.setColor(colorBackGround);
		g.fillRect(x+1, y+1, width-2, height-2);
		Rectangle map = new Rectangle(x+1, y+1, width-2, height-2);	

		paintZones(g, map);
		//paintDatas(g, map);
	}
}
