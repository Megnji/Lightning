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
		if(e.getSource().equals(openItem)){
			returnVal = fileChooser.showOpenDialog(null);
			if(returnVal == JFileChooser.APPROVE_OPTION){
				file = fileChooser.getSelectedFile();
				try{
					br =  new BufferedReader(new FileReader(file));
					while((currentLine = br.readLine())!=null){
						System.out.println(currentLine);
					}
				}
				catch(Exception error){
					error.printStackTrace();
				}
			}
		}
	}


	}
	

