package functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Connection;
import uiElements.PlotPanel;


public class LoadPlotData {
	private static List<Integer> _dots = new ArrayList<Integer>();
	public static void loadData(String fname){
		String everything = "";
		try(BufferedReader br = new BufferedReader(new FileReader(fname))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (!everything.equals("")){
			String[] temp = everything.split(",|\\(|\\)|\\s+");
			for (String i : temp){
				if (!i.equals("")){
					_dots.add(Integer.parseInt(i));
				}
			}
			
			for (int i=0;i<_dots.size();i+=2){
				Connection c = new Connection(_dots.get(i),_dots.get(i+1));
				PlotPanel.addConnection(c);
			}
			
			
		}
		
		
		
	}
}
