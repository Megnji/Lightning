package functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import bean.Connection;
import uiElements.PlotPanel;

public class LoadPlotData {
	
	public static String[] readFile(String fname) throws IOException{
		String[] result;
		int t;
		BufferedReader br = new BufferedReader(new FileReader(fname));
		try {
		    String line = br.readLine();
		    try {
		        t = Integer.parseInt(line);
		    } catch (NumberFormatException e) {
		        t = 0;
		    }
		    result = new String[2];
		    int count = 0;
		    while (line != null) {
		        result[count] = line;
		        line = br.readLine();
		        count++;   
		    }
		} finally {
		    br.close();
		}
		return result;
	}

	public static void loadFile(String fname) throws IOException{
		String[] st = readFile(fname);
		for (String s:st){
			int indexA = Character.getNumericValue(s.charAt(1));
			int indexB = Character.getNumericValue(s.charAt(3));
			PlotPanel.addConnection(new Connection(indexA,indexB));
		}
	}
}
