package uiElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bean.Connection;
import bean.PointBean;

public class PlotPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean showGrid = true;
	public static boolean showOLine = true;
	
	private static int _radiusOfDots = 4;
	private static int _height = 2000;
	private static int _width = 2000;
	private static ArrayList<PointBean> _list = new ArrayList<PointBean>();
	private static ArrayList<Connection> _connections = new ArrayList<Connection>();
	private static int _index = 0;
	
	/**
	 * Create the panel.
	 */
	public PlotPanel() {
		super();
		setBackground(Color.white);
		setBorder(BorderFactory.createBevelBorder(1));
	}
	
	public static void addConnection(Connection c){
		if (!_connections.contains(c)){
			_connections.add(c);
		}
		
	}
	
	private void drawConnections(Graphics g){
		g.setColor(Color.red);
		for (Connection c:_connections){
			g.drawLine(_list.get(c._pa)._x+_radiusOfDots/2, _list.get(c._pa)._y+_radiusOfDots/2, 
					_list.get(c._pb)._x+_radiusOfDots/2, _list.get(c._pb)._y+_radiusOfDots/2);
		}
	}
	
	private void drawGrid(Graphics g){
		Graphics2D g2d = (Graphics2D)g.create();
		
		int height = getHeight();
		int width = getWidth();
		g2d.setColor(Color.gray);
		for (int i=0; i <height; i += 5){
			g2d.drawLine(0, i, width, i);
		}
		for (int i=0; i <width; i += 5){
			g2d.drawLine(i, 0, i, height);
		}
		
		g2d.dispose();
	}
	
	private void drawBoxs(Graphics g){
		for (int i=0; i<_width; i+=100){
			for (int j=0; j<_height; j+=100){
				drawBox(g,i,j);
				
			}
		}
	}
	
	private void drawBox(Graphics g,int x,int y){
		drawPoint(g,x+50,y+30);
		drawPoint(g,x+50,y+40);
		drawPoint(g,x+50,y+60);
		drawPoint(g,x+50,y+70);
		drawPoint(g,x+30,y+50);
		drawPoint(g,x+40,y+50);
		drawPoint(g,x+60,y+50);
		drawPoint(g,x+70,y+50);
	}
	
	private void drawLines(Graphics g){
		for (int i=0; i<_list.size(); i++){
			int index = _list.get(i)._index;
			if ( index % 8 < 4){
				connectTwoPoints(g,index,(index/8)*8+4);
				connectTwoPoints(g,index,(index/8)*8+5);
				connectTwoPoints(g,index,(index/8)*8+6);
				connectTwoPoints(g,index,(index/8)*8+7);
			}
		}
	}
	
	private void connectTwoPoints(Graphics g,int a,int b){
		int xa=0,xb=0,ya=0,yb=0;
				xa = _list.get(a)._x+_radiusOfDots/2;
				ya = _list.get(a)._y+_radiusOfDots/2;
				xb = _list.get(b)._x+_radiusOfDots/2;
				yb = _list.get(b)._y+_radiusOfDots/2;

		
		if (xa != 0 && xb != 0 && ya !=0 && yb !=0){
			g.drawLine(xa, ya, xb, yb);
		}
	}
	
	private void drawPoint(Graphics g,int x, int y){
		g.fillOval(x, y, _radiusOfDots, _radiusOfDots);
		
		_list.add(new PointBean(_index,x,y));
		_index++;
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if (showGrid){
			drawGrid(g);
		}

		if (showOLine){
			drawLines(g);
		}
		
		drawBoxs(g);
		drawConnections(g);
	}
}