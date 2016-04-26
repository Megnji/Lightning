package uiElements;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1521768584529278585L;

	public MainMenu(){
		super();
		initialize();
	}
	
	private void initialize(){
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("new");
		JMenuItem openItem = new JMenuItem("open..");
		JMenuItem saveItem = new JMenuItem("save");
		JMenuItem saveAsItem = new JMenuItem("save as..");
		
		fileMenu.add(newItem);
		fileMenu.addSeparator();
		
		fileMenu.add(openItem);
		fileMenu.add(saveItem);
		fileMenu.add(saveAsItem);
		
		JMenu helpMenu = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About");
		
		helpMenu.add(aboutItem);
		
		add(fileMenu);
		add(helpMenu);
	}
	
	
}
