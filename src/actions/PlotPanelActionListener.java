package actions;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


import uiElements.PlotPanel;

public class PlotPanelActionListener implements MouseListener{

	private PlotPanel _panel;
	public PlotPanelActionListener(PlotPanel panel){
		super();
		_panel = panel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println(e.getClickCount());
		if (e.getClickCount() == 2){
			if (PlotPanel.zoomin == true){
				PlotPanel.zoomin = false;
			}else{
				PlotPanel.zoomin = true;
			}
			
			_panel.repaint();
			PlotPanel.changed = true;
		}else if (e.getClickCount() == 1){
			Point p = e.getPoint();
			System.out.println(PlotPanel.getClickInfo(p));
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
