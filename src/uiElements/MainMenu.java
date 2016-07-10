package uiElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainMenu extends JMenuBar implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1521768584529278585L;
	JMenuItem openItem;
	int returnVal;		
	JFileChooser fileChooser;
	File file;
	String currentLine;
	BufferedReader br;
	public static String fileName;
	public static String graphOrder = "";
	public static String physicalQ = "";
	public static String logicalQ = "";
	public static String embeddings = "";
	public static String [] embedding;
	public MainMenu(){
		super();
		initialize();
	}

	private void initialize(){
		
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("new");
		openItem = new JMenuItem("open..");
		openItem.addActionListener(this);
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
	    fileChooser = new JFileChooser();
		}
	public void actionPerformed(ActionEvent e){
		boolean emb = false;
		if(e.getSource().equals(openItem)){
			returnVal = fileChooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
				fileName = file.getName();
				InfoPanel.addFileName();
				InfoPanel.addPhysicalQ();
				InfoPanel.addLogicalQ();
				try{
					br =  new BufferedReader(new FileReader(file));
					while((currentLine = br.readLine())!=null){
						//System.out.println(currentLine);
						String[] words = currentLine.split(" ");
						if (words[0].contains("Graph")&&words[1].equals("order")){
							graphOrder = words[3].substring(0, 1);
							InfoPanel.addGraphOrder();
						}
						if (words[0].contains("Logical")&&words[1].equals("qubits")){
							logicalQ = words[3].substring(0, 1);
							InfoPanel.addLogicalQ();
						}
						if (words[0].contains("Physical")&&words[1].equals("qubits")){
							physicalQ = words[3].substring(0, 1);
							InfoPanel.addPhysicalQ();
						}
						if (currentLine.contains("embedding"))
							emb = true;
						if (currentLine.contains("Physical"))
							emb = false;
						if (emb)
							embeddings = embeddings + currentLine;
						
						
					}
			
					embedding = embeddings.split("]");
					embedding[0] = embedding[0].substring(17,embedding[0].length());
					for (int i=1;i<embedding.length-2;i++){
						embedding[i] = embedding[i].substring(3, embedding[i].length());
					}
					PlotPanel.updatePlotPanel("gg");
				}
				catch(Exception error){
					error.printStackTrace();
				}
			}
		}
	}
	public String getFileName(){
		return fileName;
	}


	}
	

