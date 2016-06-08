package uiElements;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoPanel extends JPanel {
	static JTextArea textAreaFN = new JTextArea("No File");
	/**
	 * 
	 */
	private static final long serialVersionUID = -1936736402101917359L;
	private static JTextField textPhysicalQ;
	private JTextField textEdgesShown;
	private static JTextField textGraphOrder;
	private static JTextField textLogicalQ;
	/**
	 * Create the panel.
	 */
	public InfoPanel() {
		super();
		initialize();
	}
	public static void addFileName(){
		String fileName = MainMenu.fileName;
		textAreaFN.setText(fileName);
	}
	public static void addGraphOrder(){
		String graphOrder = MainMenu.graphOrder;
		textGraphOrder.setText(graphOrder);
	}
	public static void addPhysicalQ(){
		String physicalQ = MainMenu.physicalQ;
		textPhysicalQ.setText(physicalQ);
	}

	public static void addLogicalQ(){
		String logicalQ = MainMenu.logicalQ;
		textLogicalQ.setText(logicalQ);
	}


	
	private void initialize(){
		JLabel infoLabel = new JLabel("Info");
		infoLabel.setBounds(60, 10, 50, 14);
		JTextArea textArea = new JTextArea("Some information goes here");
		textArea.setBounds(115, 5, 186, 24);
		setLayout(null);
		add(infoLabel);
		add(textArea);
		textAreaFN.setBounds(115, 265, 244, 24);
		//JLabel fileNameL = new JLabel("Current File: ");
		add(textAreaFN);
		
		JLabel lblCurrentFile = new JLabel("Current File:");
		lblCurrentFile.setBounds(27, 265, 73, 24);
		add(lblCurrentFile);
		
		JLabel lblNewLabel = new JLabel("Physical qubits used");
		lblNewLabel.setBounds(27, 55, 129, 14);
		add(lblNewLabel);
		
		JLabel lblEdgesShown = new JLabel("Edges Shown");
		lblEdgesShown.setBounds(27, 94, 129, 14);
		add(lblEdgesShown);
		
		JLabel lblGraphOrder = new JLabel("Graph Order");
		lblGraphOrder.setBounds(27, 130, 129, 14);
		add(lblGraphOrder);
		
		JLabel lblLogicalQubitsUsed = new JLabel("Logical qubits used");
		lblLogicalQubitsUsed.setBounds(27, 167, 129, 14);
		add(lblLogicalQubitsUsed);
		
		textPhysicalQ = new JTextField();
		textPhysicalQ.setBounds(215, 52, 86, 20);
		add(textPhysicalQ);
		textPhysicalQ.setColumns(10);
		
		textEdgesShown = new JTextField();
		textEdgesShown.setBounds(215, 91, 86, 20);
		add(textEdgesShown);
		textEdgesShown.setColumns(10);
		
		textGraphOrder = new JTextField();
		textGraphOrder.setBounds(215, 127, 86, 20);
		add(textGraphOrder);
		textGraphOrder.setColumns(10);
		
		textLogicalQ = new JTextField();
		textLogicalQ.setBounds(215, 164, 86, 20);
		add(textLogicalQ);
		textLogicalQ.setColumns(10);
		//set text fields to be uneditable
		textAreaFN.setEditable(false);
		textPhysicalQ.setEditable(false);
		textLogicalQ.setEditable(false);
		textEdgesShown.setEditable(false);
		textGraphOrder.setEditable(false);
	}
	public void update(String f){
		String fileName = MainMenu.fileName;
		JLabel fN = new JLabel(fileName);	
		add(fN);
	}
}
