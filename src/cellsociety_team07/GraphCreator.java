package cellsociety_team07;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.HBox;

public class GraphCreator {
	
	private static final int MAX_DATA_POINTS = 20;
	private int xSeriesData = 0;
	private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
	private ExecutorService executor;
    private ConcurrentLinkedQueue<Number> dataQ1 = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Number> dataQ2 = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Number> dataQ3 = new ConcurrentLinkedQueue<>();
	private NumberAxis xAxis;
	
	public LineChart getLineChart() {
		 xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS / 10);
	     xAxis.setForceZeroInRange(false);
	     xAxis.setAutoRanging(false);
	     xAxis.setTickLabelsVisible(false);
	     xAxis.setTickMarkVisible(false);
	     xAxis.setMinorTickVisible(false);
	     NumberAxis yAxis = new NumberAxis(0,25, 25);
	     yAxis.setLabel("Pop Count");

	        // Create a LineChart
	     final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis) {
	            // Override to remove symbols on each data point
	           @Override
	           protected void dataItemAdded(Series<Number, Number> series, int itemIndex, Data<Number, Number> item) {
	            }
	        };

	        lineChart.setAnimated(false);
	        lineChart.setTitle("Simulation Population Graph");
	        lineChart.setHorizontalGridLinesVisible(true);
	        lineChart.setPrefHeight(150);
	        lineChart.setMinHeight(150);
	        lineChart.setMaxHeight(150);
	        lineChart.setPrefWidth(760);
	        lineChart.setMinWidth(750);
	        lineChart.setMaxWidth(760);

	        lineChart.getData().addAll(series1, series2, series3);
	        return lineChart;
	
	}
	
	protected void updateLineGraph() {

		 for (int i = 0; i < 25; i++) { 
	            if (dataQ1.isEmpty()) break;
	            series1.getData().add(new XYChart.Data<>(xSeriesData++, dataQ1.remove()));
	            if(dataQ2.isEmpty()) break;
	            series2.getData().add(new XYChart.Data<>(xSeriesData++, dataQ2.remove()));
	            if(dataQ3.isEmpty()) break;
	            series3.getData().add(new XYChart.Data<>(xSeriesData++, dataQ3.remove()));
	        }

	        if (series1.getData().size() > MAX_DATA_POINTS) {
	        		series1.getData().remove(0, series1.getData().size() - MAX_DATA_POINTS);
	        }
	        if (series2.getData().size() > MAX_DATA_POINTS) {
	        		series2.getData().remove(0, series2.getData().size() - MAX_DATA_POINTS);
	        }
	        if (series3.getData().size() > MAX_DATA_POINTS) {
	        		series2.getData().remove(0, series3.getData().size() - MAX_DATA_POINTS);
	        }
	        xAxis.setLowerBound(xSeriesData - MAX_DATA_POINTS);
	        xAxis.setUpperBound(xSeriesData - 1);
		
	}
	
	
	public NumberAxis getXAxis() {
		return xAxis;
	}
	
	public ExecutorService getExecutor() {
		return executor;
	}
	
	public  XYChart.Series<Number, Number> getSeries1(){
		return series1;
	}
	public  XYChart.Series<Number, Number> getSeries2(){
		return series2;
	}
	public  XYChart.Series<Number, Number> getSeries3(){
		return series3;
	}

}

