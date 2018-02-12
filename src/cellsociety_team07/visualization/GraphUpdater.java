package cellsociety_team07.visualization;

import javafx.scene.chart.XYChart;

public class GraphUpdater {
	
	private void updateLineGraph() {
		for (int i = 0; i < 25; i++) { 
	            if (dataQ1.isEmpty()) break;
	            graphCreator.getSeries1().getData().add(new XYChart.Data<>(xSeriesData++, dataQ1.remove()));
	            if(dataQ2.isEmpty()) break;
	            graphCreator.getSeries2().getData().add(new XYChart.Data<>(xSeriesData++, dataQ2.remove()));
	            if(dataQ3.isEmpty()) break;
	            graphCreator.getSeries3().getData().add(new XYChart.Data<>(xSeriesData++, dataQ3.remove()));
	        }
			addNewPoint(graphCreator.getSeries1(), MAX_DATA_POINTS);
			addNewPoint(graphCreator.getSeries2(), MAX_DATA_POINTS);
			addNewPoint(graphCreator.getSeries3(), MAX_DATA_POINTS);
			graphCreator.getXAxis().setLowerBound(xSeriesData - MAX_DATA_POINTS);
	         graphCreator.getXAxis().setUpperBound(xSeriesData - 1);
	}
	
	private void addNewPoint(XYChart.Series<Number, Number> series, int maxData) {
		if (series.getData().size() > maxData) {
    			series.getData().remove(0, graphCreator.getSeries3().getData().size() - maxData);
		}
	}

}
