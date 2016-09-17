package uiElements;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import actions.PlotPanelActionListener;
import functions.LoadAlist;
import functions.LoadPlotData;

public class MainFrame {

	private static JFrame frame;
	private static PlotPanel plotPanel;
	private static InfoPanel infoPanel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
		//LoadPlotData.loadData("resources/host");
		File f = new File("resources/dw2x.alist");
		LoadAlist.loadAlistFile(f);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		infoPanel = new InfoPanel();
		
		plotPanel = new PlotPanel();
		MouseListener ml = new PlotPanelActionListener(plotPanel);
		plotPanel.addMouseListener(ml);
		plotPanel.setPreferredSize(new Dimension(1500,1500));
		JScrollPane jsp = new JScrollPane(plotPanel);
		
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsp,infoPanel);
		JMenuBar menuBar = new MainMenu();
		
		frame.setJMenuBar(menuBar);
		frame.add(sp);
		frame.setBounds(100, 100, 1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sp.setResizeWeight(0.5);
	}
	
	public static void renewPlotPanel(){
		plotPanel.repaint();
	}
	
	public static void renewInfoPanel(){
		infoPanel.repaint();
	}
	
}
