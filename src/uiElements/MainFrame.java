package uiElements;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		JPanel infoPanel1 = new InfoPanel();
		JPanel plotPanel = new PlotPanel();
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,plotPanel,infoPanel1);
		
		JMenuBar menuBar = new MainMenu();
		
		frame.setJMenuBar(menuBar);
		frame.add(sp);
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sp.setResizeWeight(0.5);
	}

}
