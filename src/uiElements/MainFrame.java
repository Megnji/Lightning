package uiElements;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import functions.LoadPlotData;

public class MainFrame {

	private JFrame frame;

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
		LoadPlotData.loadData("resources/input");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		JPanel infoPanel = new InfoPanel();
		
		JPanel plotPanel = new PlotPanel();
		plotPanel.setPreferredSize(new Dimension(2000,2000));
		JScrollPane jsp = new JScrollPane(plotPanel);
		
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jsp,infoPanel);
		JMenuBar menuBar = new MainMenu();
		
		frame.setJMenuBar(menuBar);
		frame.add(sp);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sp.setResizeWeight(0.5);
		
		
	}

}
