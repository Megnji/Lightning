package uiElements;

import java.awt.Color;
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
	
	public static boolean zoomin = false;
	public static boolean changed = false;
	private static int _radiusOfDots = 4;
	private static int _height = 800;
	private static int _width = 800;
	private static ArrayList<PointBean> _list = new ArrayList<PointBean>();
	private static ArrayList<Integer> _listOfIndex = new ArrayList<Integer>();
	private static int _maxIndex= 0;
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
		for (Connection cp:_connections){
			if ((c._pa == cp._pa && c._pb == cp._pb) || (c._pb == cp._pa && c._pa == cp._pb)){
				cp._type = c._type;
				cp._weight = c._weight;
				return;
			}
		}
		_connections.add(c);
	}
	
	public static boolean connectionExist(Connection c){
		for (Connection cp:_connections){
			if ((c._pa == cp._pa && c._pb == cp._pb) || (c._pb == cp._pa && c._pa == cp._pb)){
				return true;
			}
		}
		return false;
	}
	
	public static String getClickInfo(Point p){
		String result = "";
		for (PointBean pb : _list){
				if (Math.abs(pb._x - p.x) < 12 && Math.abs(pb._y- p.y) < 12){
					result = "Point clicked: "+pb._index+" "+pb._x+ " "+pb._y;
					result = result+ "\n" + _list.size();
					return result;
				}

		}
		
		for (Connection c: _connections){
			PointBean pa = _list.get(c._pa);
			PointBean pb = _list.get(c._pb);
			
			if (pa._x == pb._x && pa._y<pb._y){ //Vertical line && pa at the top
				if (Math.abs(pa._x - p.x) < 12 && p.y > pa._y && p.y < pb._y){
					return "Line clicked:" +pa._index + " to " + pb._index;
				}
			}else if (pa._x == pb._x && pa._y>pb._y){ //Vertical line && pb at the top
				if (Math.abs(pa._x - p.x) < 12 && p.y <pa._y && p.y > pb._y){
					return "Line clicked:" +pa._index + " to " + pb._index;
				}
			}else if (pa._y == pb._y && pa._x < pb._x){ //Horizontal line && pa at the left
				if (Math.abs(pa._y - p.y) < 12 && p.x >pa._x && p.x < pb._x){
					return "Line clicked:" +pa._index + " to " + pb._index;
				}
			}else if (pa._y == pb._y && pa._x > pb._x){ //Horizontal line && pb at the left
				if (Math.abs(pa._y - p.y) < 12 && p.x < pa._x && p.x > pb._x){
					return "Line clicked:" +pa._index + " to " + pb._index;
				}
			}
		}
		return "Hahahahahah";
	}
	
	private void drawConnections(Graphics g){
		for (Connection c:_connections){
			if (c._type == ConnectionType.host){
				g.setColor(Color.gray);
			}else if(c._type == ConnectionType.embedding){
				g.setColor(Color.red);
			}else {
				g.setColor(Color.blue);
			}
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
		if (_listOfIndex.contains(_index)){
			g.fillOval(x, y, _radiusOfDots, _radiusOfDots);
			PointBean point = new PointBean(_index,x,y);
			_list.add(_index, point);
		}else{
			PointBean point = new PointBean(_index,0,0);
			_list.add(_index, point);
		}
		
		_index++;
	}
	
	public void scale(){
		
		for (PointBean p : _list){
			if (zoomin){
				p._x = p._x *2;
				p._y = p._y *2;
			}else{
				p._x = p._x /2;
				p._y = p._y /2;
			}

		}
		changed = false;
	}
	
	public static void setDotList(ArrayList<Integer> l, int maxIndex){
		_listOfIndex = l;
		_maxIndex = maxIndex;
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
		
		if (zoomin){
			Graphics2D g2 = (Graphics2D) g;
			int scale = 2;
			g2.scale(scale, scale);
			
		}

		_index = 0;
		_list.clear();
		drawBoxs(g);
		
		drawConnections(g);
		if (changed){
			scale();
		}
	}
}
