package uiElements;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class InfoPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1936736402101917359L;

	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		super();
		initialize();
	}
	
	private void initialize(){
		JLabel infoLabel = new JLabel("Info");
		JTextArea textArea = new JTextArea("Some information goes here");
		add(infoLabel,BorderLayout.NORTH);
		add(textArea,BorderLayout.SOUTH);
		
		
	}

}
