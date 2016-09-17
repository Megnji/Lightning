package uiElements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bean.Connection;
import bean.Connection.ConnectionType;
import bean.PointBean;

public class PlotPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static boolean showGrid = false;
	public static boolean showOLine = false;
	
	
	public static boolean changed = false;
	
	private static boolean _draw = false;
	private static int _radiusOfDots = 4;
//	private static int _height = 800;
//	private static int _width = 800;
	private static ArrayList<PointBean> _list = new ArrayList<PointBean>();
	private static ArrayList<Integer> _listOfIndex = new ArrayList<Integer>();
	private static int _maxIndex= 0;
	private static ArrayList<Connection> _connections = new ArrayList<Connection>();
	private static int _index = 0;
	private static int _zoomRate = 1;
	
	/**
	 * Create the panel.
	 */
	public PlotPanel() {
		super();
		setBackground(Color.white);
		setBorder(BorderFactory.createBevelBorder(1));
	}
	
	/*
	 * Add connection into plot panel
	 */
	public static void addConnection(Connection c){
		for (Connection cp:_connections){
			if ((c._pa == cp._pa && c._pb == cp._pb) || (c._pb == cp._pa && c._pa == cp._pb)){
				cp._type = c._type;
				cp._weight = c._weight;
				cp._indexGroup = c._indexGroup;
				return;
			}
		}
		_connections.add(c);
	}
	
	//determine whether the connection is already existed.
	public static boolean connectionExist(Connection c){
		for (Connection cp:_connections){
			if ((c._pa == cp._pa && c._pb == cp._pb) || (c._pb == cp._pa && c._pa == cp._pb)){
				return true;
			}
		}
		return false;
	}
	
	public static void showGroup(Connection c){
		int num = 0;
		for (Connection c2: _connections){
			if (c2._indexGroup == c._indexGroup && c2._indexGroup != -1){
				c2.isClicked = true;
				num ++;
			}else{
				c2.isClicked = false;
			}
		}
		
		InfoPanel.setGroupNum(num);
	}
	

	public static void loadConnections(ArrayList<Connection> list){

		for (Connection c: _connections){
			c._type = ConnectionType.host;
		}
		for (Connection c: list){
			addConnection(c);
		}
		
	}
	
	public static String getClickInfo(Point p){
		
		if (_zoomRate != 1){
			for (PointBean pb : _list){
				pb._x = pb._x*_zoomRate;
				pb._y = pb._y*_zoomRate;
			}
		}
		String result = "";
		double distance; 
		for (PointBean pb : _list){
				distance = Math.sqrt(Math.pow((pb._x +4 - p.x),2) + Math.pow((pb._y +4-p.y),2));
				if (distance < 5){
					InfoPanel.setClickInfo(pb._index);
					result = "Point clicked: "+pb._index+" "+pb._x+ " "+pb._y +" "+p.x +" "+p.y + " with distance: "+ distance;
					return result;
				}

		}
		
		for (Connection c: _connections){
			PointBean pa = _list.get(c._pa);
			PointBean pb = _list.get(c._pb);
			
//			if (pa._x == pb._x && pa._y<pb._y){ //Vertical line && pa at the top
//				if (Math.abs(pa._x - p.x) < 12 && p.y > pa._y && p.y < pb._y){
//					showGroup(c);
//					return "Line clicked:" +pa._index + " to " + pb._index;
//				}
//			}else if (pa._x == pb._x && pa._y>pb._y){ //Vertical line && pb at the top
//				if (Math.abs(pa._x - p.x) < 12 && p.y <pa._y && p.y > pb._y){
//					showGroup(c);
//					return "Line clicked:" +pa._index + " to " + pb._index;
//				}
//			}else if (pa._y == pb._y && pa._x < pb._x){ //Horizontal line && pa at the left
//				if (Math.abs(pa._y - p.y) < 12 && p.x >pa._x && p.x < pb._x){
//					showGroup(c);
//					return "Line clicked:" +pa._index + " to " + pb._index;
//				}
//			}else if (pa._y == pb._y && pa._x > pb._x){ //Horizontal line && pb at the left
//				if (Math.abs(pa._y - p.y) < 12 && p.x < pa._x && p.x > pb._x){
//					showGroup(c);
//					return "Line clicked:" +pa._index + " to " + pb._index;
//				}
//			}else {
				// The distance between the dot and the line of two points
			    
			    double x0,y0,x1,x2,y1,y2;
			    x0 = (double)p.x;
			    y0 = (double)p.y;
			    x1 = (double)pa._x +4.0;
			    y1 = (double)pa._y +4.0;
			    x2 = (double)pb._x +4.0;
			    y2 = (double)pb._y +4.0;
			    distance = Math.abs((y2-y1)*x0 - (x2-x1) * y0 + x2*y1 - y2*x1) / 
						Math.sqrt(Math.pow((y2 - y1),2) + Math.pow((x2-x1),2)); 
			    if (x1 == x2 && (y0< Math.min(y1, y2)||y0> Math.max(y1, y2) )){
			    	distance = 100;
			    }else if (y1 == y2 && (x0 < Math.min(x1, x2)|| x0 > Math.max(x1, x2))){
			    	distance = 100;
			    }else if (x1 != x2 && y1 != y2 && (x0 < Math.min(x1, x2) || x0> Math.max(x1, x2)|| 
			    		 y0 < Math.min(y1,y2) || y0> Math.max(y1, y2))){
			    	distance = 100;
			    }
				
				if (distance <= 5){
					showGroup(c);
					if (c._type == ConnectionType.host && !_draw){
						
					}else{
						InfoPanel.setClickInfo(pa._index, pb._index,c._type,c._weight);
					}
					return "Line clicked:" +pa._index + " to " + pb._index +" distance" + distance;
				}
//			}
		}
		Connection temp = new Connection(0,0); //A temporary connection that reset the click state
		showGroup(temp);
		InfoPanel.resetClick();
		return "Nothing clicked";
	}
	
	public static void setHostDraw(boolean draw){
		_draw = draw;
	}
	
	private void drawConnections(Graphics g){
		for (Connection c:_connections){
			if (c.isClicked){
				g.setColor(Color.RED);
			}else if(c._type == ConnectionType.embedding){
				g.setColor(Color.BLUE);
			}else if (c._type == ConnectionType.Q){
				g.setColor(Color.GREEN);
			}else {
				g.setColor(Color.lightGray);
			}
			
			if (!_draw && c._type == ConnectionType.host){
				//do nothing
			}else{
				g.drawLine(_list.get(c._pa)._x+_radiusOfDots/2, _list.get(c._pa)._y+_radiusOfDots/2, 
						_list.get(c._pb)._x+_radiusOfDots/2, _list.get(c._pb)._y+_radiusOfDots/2);
			}
			
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
		int size = (int)Math.ceil(Math.sqrt(_maxIndex / 8));
		for (int i=0; i<size * 100; i+=100){
			for (int j=0; j<size * 100; j+=100){
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
		int actualRadius = _radiusOfDots;
		
		if (_listOfIndex.contains(_index)){
			g.fillOval(x, y, actualRadius, actualRadius);
			PointBean point = new PointBean(_index,x,y);
			
			point = new PointBean(_index,x,y);
			_list.add(_index, point);
		}else{
			PointBean point = new PointBean(_index,0,0);
			_list.add(_index, point);
		}
		
		_index++;
	}
	
	public static void resetStatus(){
		for (Connection c:_connections){
			c._type = ConnectionType.host;
			c._weight = 0;
		}
	}
	
	public static void setDotList(ArrayList<Integer> l, int maxIndex){
		_listOfIndex = l;
		_maxIndex = maxIndex;
	}
	
	public static void setZoomeRate(int rate){
		_zoomRate = rate;
	}
	
	public static int getZoomRate(){
		return _zoomRate;
	}
	
	public static void resetZoomRate(){
		_zoomRate = 1;
	}
	public void renewInfoPanel(){
		ArrayList<Integer> list= new ArrayList<Integer>();
		int num = 0;
		for (Connection c: _connections){
			if (c._type == ConnectionType.embedding || c._type == ConnectionType.Q){
				if (!list.contains(c._pa)){
					list.add(c._pa);
				}
				num ++;
				if (!list.contains(c._pb)){
					list.add(c._pb);
				}
			}

		}
		
		InfoPanel.setEdges(num);
		InfoPanel.setPhysicQubitNum(list.size());
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
		


		_index = 0;
		_list.clear();
		
		if (_zoomRate != 1){
			setPreferredSize(new Dimension(1500*_zoomRate,1500*_zoomRate));
			Graphics2D g2 = (Graphics2D) g;
			g2.scale((double)_zoomRate, (double)_zoomRate);
			
		}else{
			setPreferredSize(new Dimension(1500,1500));
		}
		drawBoxs(g);
		
		drawConnections(g);
		renewInfoPanel();
	}
}
