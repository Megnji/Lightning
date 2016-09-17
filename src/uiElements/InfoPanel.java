package uiElements;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import bean.Connection.ConnectionType;

public class InfoPanel extends JPanel implements ItemListener{
	static JTextArea textAreaFN = new JTextArea("Default File");
	/**
	 * 
	 */
	private static final long serialVersionUID = -1936736402101917359L;
	private static JTextField textPhysicalQ;
	private static JTextField textEdgesShown;
	private static JTextField textGraphOrder;
	private static JTextField textLogicalQ;
	
	private static JLabel itemClicked;
	private static JLabel label1,label2,label3,label4,label5,errorMsg;
	
	private static JCheckBox box1, box2;
	
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
		JLabel infoLabel = new JLabel("Information");
		infoLabel.setBounds(60, 10, 120, 14);
		setLayout(null);
		add(infoLabel);
		
		textAreaFN.setBounds(115, 265, 244, 24);
		//JLabel fileNameL = new JLabel("Current File: ");
		add(textAreaFN);
		
		JLabel lblCurrentFile = new JLabel("Current File:");
		lblCurrentFile.setBounds(27, 265, 200, 24);
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
		
		textPhysicalQ = new JTextField("0");
		textPhysicalQ.setBounds(215, 52, 86, 20);
		add(textPhysicalQ);
		textPhysicalQ.setColumns(10);
		
		textEdgesShown = new JTextField("0");
		textEdgesShown.setBounds(215, 91, 86, 20);
		add(textEdgesShown);
		textEdgesShown.setColumns(10);
		
		textGraphOrder = new JTextField("0");
		textGraphOrder.setBounds(215, 127, 86, 20);
		add(textGraphOrder);
		textGraphOrder.setColumns(10);
		
		textLogicalQ = new JTextField("0");
		textLogicalQ.setBounds(215, 164, 86, 20);
		add(textLogicalQ);
		textLogicalQ.setColumns(10);
		//set text fields to be uneditable
		textAreaFN.setEditable(false);
		textPhysicalQ.setEditable(false);
		textLogicalQ.setEditable(false);
		textEdgesShown.setEditable(false);
		textGraphOrder.setEditable(false);
		
		itemClicked = new JLabel("No item clicked");
		itemClicked.setBounds(60, 300, 150, 24);
		add(itemClicked);
		
		label1 = new JLabel("");
		label1.setBounds(27,330, 150, 24);
		add(label1);
		
		label2 = new JLabel("");
		label2.setBounds(200, 330, 150, 24);
		add(label2);
		
		label3 = new JLabel("");
		label3.setBounds(27, 360, 150,24);
		add(label3);
		
		label4 = new JLabel("");
		label4.setBounds(200, 360, 150, 24);
		add(label4);
		
		label5 = new JLabel("");
		label5.setBounds(27,390,150,24);
		add(label5);
		
		errorMsg = new JLabel("");
		errorMsg.setBounds(27, 480, 300,24);
		add(errorMsg);
		
		box1 = new JCheckBox("Show Faulty qubits");
		box1.setBounds(27, 420, 200, 24);
		//add(box1);
		
		box2 = new JCheckBox("Show host graph");
		box2.setBounds(27, 450, 200, 24);
		box2.addItemListener(this);
		add(box2);
		
	}
	
	public static void resetClick(){
		itemClicked.setText("No item clicked");
		label1.setText("");
		label2.setText("");
		label3.setText("");
		label4.setText("");
		label5.setText("");
	}
	public static void setClickInfo(int i){
		itemClicked.setText("Vertice clicked");
		label1.setText("Vertice number: "+ i);
		label2.setText("");
		label3.setText("");
		label4.setText("");
		label5.setText("");
	}
	
	public static void setClickInfo(int i, int j, ConnectionType type, double weight){
		itemClicked.setText("edge clicked");
		label1.setText("Starting vertice: "+ i);
		label2.setText("Ending vertice: "+ j);
		String t = "Type: ";
		if (type == ConnectionType.embedding){
			t = t+ "Embedding";
			weight = 1.0;
		}else if (type == ConnectionType.host){
			t = t+ "Hosting";
			label4.setText("");
		}else {
			t = t+ "Embedding";
			weight = 1.0;
			label4.setText("");
		}
	    label3.setText(t);
	    
	    label5.setText("Weight: "+ weight);
	}
	public static void setGroupNum(int i){
		if (i > 0){
			label4.setText("Group edges number: "+ i);
		}
	}
	public static void setPhysicQubitNum(int i){
		textPhysicalQ.setText("" + i);;
	}
	
	public static void setEdges(int i){
		textEdgesShown.setText(""+ i);
	}
	
	public static void setGraphOrder(int i){
		textGraphOrder.setText("" + i);
	}
	
	public static void setLogicQubit(int i){
		textLogicalQ.setText("" + i);
	}
	
	public static void setErrorMsg(String error){
		errorMsg.setText(error);
	}
	
	public static void setFileName(String fname){
		textAreaFN.setText(fname);
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == box1){
			
		}else if (e.getStateChange() == ItemEvent.DESELECTED && e.getSource() == box1){
			
		}else if (e.getStateChange() == ItemEvent.SELECTED && e.getSource() == InfoPanel.box2){
			PlotPanel.setHostDraw(true);
			MainFrame.renewPlotPanel();
		}else if (e.getStateChange() == ItemEvent.DESELECTED && e.getSource() == InfoPanel.box2){
			PlotPanel.setHostDraw(false);
			MainFrame.renewPlotPanel();
		}
		
	}
}
