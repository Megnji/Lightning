package uiElements;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import bean.Connection;
import functions.LoadAlist;
import functions.LoadQUBO;
import functions.PlotInfoHandler;

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
	private static ArrayList<Integer> embedding2D = new ArrayList<Integer>();
	private static ArrayList<Connection> connections = new ArrayList<Connection>();
	public Connection t;
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
			PlotPanel.resetStatus();
			PlotPanel.resetZoomRate();
			returnVal = fileChooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
				InfoPanel.addFileName();
				InfoPanel.addPhysicalQ();
				InfoPanel.addLogicalQ();
				fileName = file.getName();
				if (fileName.contains(".out")){
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
						if (currentLine.contains("Q="))
							emb = true;
						if (currentLine.contains("Physical"))
							emb = false;
						if (emb)
							embeddings = embeddings + currentLine;
						
						
					}
			
					//PlotInfoHandler.updateConnection(embeddings);
				}
				catch(Exception error){
					error.printStackTrace();
				}
			}else if (fileName.contains(".alist")){
				LoadAlist.loadAlistFile(file);
			}else if (fileName.contains(".QUBO") || fileName.contains(".qubo")){
				LoadQUBO.loadQUBOFile(file);
			}
			InfoPanel.setFileName(fileName);
			
			MainFrame.renewInfoPanel();
			MainFrame.renewPlotPanel();
		}
		}
	
	}
	public String getFileName(){
		return fileName;
	}


	}
	

