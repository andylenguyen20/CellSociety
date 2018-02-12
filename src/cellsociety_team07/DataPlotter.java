package cellsociety_team07;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Paint;

public class DataPlotter {
	
	private GraphCreator graphCreator = new GraphCreator();

	public static void plotPoints( Map<Paint, Integer> populations, ConcurrentLinkedQueue<Number> dataQ1,ConcurrentLinkedQueue<Number> dataQ2, 
				     ConcurrentLinkedQueue<Number>dataQ3, ExecutorService executor ) {
			if (populations == null) return;
			List keys = new ArrayList(populations.keySet());
			for (int i = 0; i < populations.size(); i++) {
				Object obj = keys.get(i);
				if (populations.size()==0)
					break;
				else if (i == 0 && populations.size() == 1 && populations.get(keys.get(0))!=null) {
					dataQ1.add(25);
					dataQ2.add(0);
				}
				else if (i == 0) 
					dataQ1.add(populations.get(obj));
				else if (i == 1) 
					dataQ2.add(populations.get(obj));
				else if (i == 2) 
					dataQ3.add(populations.get(obj));
				}
		}
}
			


