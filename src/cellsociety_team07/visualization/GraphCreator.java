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
	private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	
	public LineChart getLineChart() {
		setXAxis();
	    setYAxis();
	    return setLineChart();
	}
	
	private void setXAxis() {
		 xAxis = new NumberAxis(0, MAX_DATA_POINTS, MAX_DATA_POINTS / 10);
	     xAxis.setForceZeroInRange(false);
	     xAxis.setAutoRanging(false);
	     xAxis.setTickLabelsVisible(false);
	     xAxis.setTickMarkVisible(false);
	     xAxis.setMinorTickVisible(false);
	}
	
	private void setYAxis() {
		 yAxis = new NumberAxis(0,25, 25);
	     yAxis.setLabel("Pop Count");
	}

	private LineChart setLineChart() {
		  final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis) {
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
	
	public NumberAxis getXAxis() {
		return xAxis;
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

