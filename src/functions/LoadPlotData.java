package functions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bean.Connection;
import bean.PointBean;
import uiElements.PlotPanel;


public class LoadPlotData {
	private static List<Integer> _dots = new ArrayList<Integer>();
	private static ArrayList<Integer> _dotList = new ArrayList<Integer>();
	private static ArrayList<PointBean> _list = new ArrayList<PointBean>();
	private static int _maxIndex = 0;
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
					int index= Integer.parseInt(i);
					_dots.add(index);
					PointBean p = new PointBean(index);
					if (!_list.contains(p)){
						_list.add(p);
					}
					if (!_dotList.contains(index)){
						_dotList.add(index);
					}
					if (index > _maxIndex){
						_maxIndex= index;
					}
				}
			}
			
			for (int i: _dotList){
				System.out.print(i+ " ");
			}
			
			PlotPanel.setDotList(_dotList,_maxIndex);
			
			for (int i=0;i<_dots.size();i+=2){
				Connection c = new Connection(_dots.get(i),_dots.get(i+1));
				PlotPanel.addConnection(c);
			}						
		}		
		
		String str = "('Q=', {(9, 26): -20, (11, 11): 80, "
				+ "(12, 12): 30, (7, 25): -40, (18, 19): 40, (0, 10): -20, (1, 11): -40, (19, 19): 80, (8, 24): "
				+ "-20, (6, 7): 40, (5, 5): -25, (4, 19): -40, (5, 18): -20, (6, 25): -40, (1, 1): -25, (20, 20): 30, "
				+ "(4, 5): 40, (14, 14): 30, (4, 16): -20, (24, 24): 30, (6, 23): -40, (5, 21): -40, (7, 22): -20, (2, 17): "
				+ "-40, (0, 1): 40, (27, 27): 80, (1, 12): -20, (28, 28): 30, (9, 9): -25, (15, 15): 80, (3, 18): -20, (4, 21): "
				+ "-40, (7, 8): 40, (16, 16): 30, (22, 23): 40, (13, 13): 80, (1, 15): -40, (8, 9): 40, (2, 12): -20, (8, 28): "
				+ "-20, (3, 17): -40, (12, 13): 40, (7, 24): -20, (0, 11): -40, (2, 2): -25, (1, 10): -20, (8, 25): -40, (25, 25):"
				+ " 80, (0, 8): 20, (3, 5): 20, (4, 6): 20, (10, 10): 30, (5, 7): 20, (4, 17): -40, (24, 25): 40, (6, 20): -20, "
				+ "(5, 20): -20, (0, 2): 20, (3, 15): -40, (1, 3): 20, (28, 29): 40, (9, 29): -40, (5, 23): -40, (0, 28): -20, "
				+ "(16, 17): 40, (1, 14): -20, (2, 13): -40, (9, 11): -40, (8, 29): -40, (3, 16): -20, (26, 26): 30, (7, 27): "
				+ "-40, (0, 12): -20, (21, 21): 80, (2, 3): 40, (1, 9): 20, (2, 14): -20, (8, 26): -20, (29, 29): 80, (9, 27): "
				+ "-40, (6, 8): 20, (18, 18): 30, (0, 9): 40, (3, 4): 40, (2, 4): 20, (10, 11): 40, (6, 6): -25, (5, 6): 40, "
				+ "(4, 18): -20, (7, 7): -25, (6, 21): -40, (5, 19): -40, (6, 24): -20, (3, 14): -20, (1, 2): 40, (3, 3): -25, "
				+ "(4, 4): -25, (9, 28): -20, (6, 22): -20, (5, 22): -20, (0, 29): -40, (7, 23): -40, (2, 16): -20, (0, 0): -25, "
				+ "(1, 13): -40, (9, 10): -20, (3, 19): -40, (4, 20): -20, (7, 9): 20, (26, 27): 40, (7, 26): -20, (22, 22): 30, "
				+ "(0, 13): -40, (23, 23): 80, (17, 17): 80, (20, 21): 40, (8, 8): -25, (2, 15): -40, (14, 15): 40, (8, 27): -40})"
				+ "('embedding=', [[280, 344, 349, 357, 408, 472], [288, 291, 292, 300, 352], [287, 295, 296, 303, 311], [233, 297, "
				+ "302, 310, 361], [240, 304, 308, 316, 368], [242, 306, 370, 434], [248, 312, 376, 437, 440, 445, 504], [432, 484, "
				+ "492, 496, 498, 500], [478, 486, 494, 502, 505, 510], [355, 419, 423, 483, 485, 487], [290, 354, 418], [348, 356], "
				+ "[285, 289, 293], [282, 284], [234, 298], [224, 230, 232, 238], [301, 309], [305], [365, 371, 373], [364, 372], "
				+ "[241, 246, 254], [244, 252], [435, 436], [439, 447], [503, 511], [506, 508], [490, 495], [491, 493], [473, 479], "
				+ "[474, 477]])";
		
		PlotInfoHandler.updateConnection(str);
	}
}
