package actions;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import uiElements.MainFrame;
import uiElements.PlotPanel;

public class PlotPanelActionListener implements MouseListener,MouseWheelListener{

	private PlotPanel _panel;
	public PlotPanelActionListener(PlotPanel panel){
		super();
		_panel = panel;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2){
			int currentZoomRate = PlotPanel.getZoomRate();
			if (currentZoomRate < 3){
				currentZoomRate++;
				PlotPanel.setZoomeRate(currentZoomRate);
				System.out.println("Current zoom rate: "+ currentZoomRate);
			}else{
				PlotPanel.resetZoomRate();
			}
			_panel.repaint();
			
		}else if (e.getClickCount() == 1){
			Point p = e.getPoint();
			System.out.println(PlotPanel.getClickInfo(p));
			_panel.repaint();
			MainFrame.renewInfoPanel();
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
	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// Need to add more
		
	}


}
