package cellsociety_team07.visualization;

import java.util.ArrayList;
/**
 * This DataPlotter class is responsible for adding points to the Population Line Chart that gets updated to show real-time population counts of the different
 * states of cells on screen
 * @author Dana Park
 */
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import javafx.scene.chart.XYChart;
import javafx.scene.paint.Paint;

public class DataPlotter {
	
	/**
	 * This plotPoints method is responsible for adding points to the Population Line Chart that gets updated to show real-time population counts of the different
	 * states of cells on screen
	 * @author Dana Park
	 */

	protected static void plotPoints( Map<Paint, Integer> populations, ConcurrentLinkedQueue<Number> dataQueue1,ConcurrentLinkedQueue<Number> dataQueue2, 
				     ConcurrentLinkedQueue<Number>dataQueue3, ExecutorService executor, int maxValue ) {
			if (populations == null) return;
			List<Paint> keys = new ArrayList<Paint>(populations.keySet());
			for (int i = 0; i < populations.size(); i++) {
				Object obj = keys.get(i);
				if (populations.size()==0)
					break;
				else if (i == 0 && populations.size() == 1 && populations.get(keys.get(0))!=null) {
					dataQueue1.add(maxValue);
					dataQueue2.add(0);
				}
				else if (i == 0) 
					dataQueue1.add(populations.get(obj));
				else if (i == 1) 
					dataQueue2.add(populations.get(obj));
				else if (i == 2) 
					dataQueue3.add(populations.get(obj));
				}
		}
	
	protected static void addNewPoint(XYChart.Series<Number, Number> series, int maxData) {
		if (series.getData().size() > maxData) 
    			series.getData().remove(0, series.getData().size() - maxData);
		}

}
			


