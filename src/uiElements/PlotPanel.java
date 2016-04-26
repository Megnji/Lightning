package uiElements;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class PlotPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PlotPanel() {
		super();
		setBackground(Color.white);
		setBorder(BorderFactory.createBevelBorder(1));
	}
	
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
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
}
