package cellsociety_team07.visualization;


import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
/**
 * This GraphCreator class sets up the initial properties of the LineChart that shows Population Count as a simulation runs
 * @author Dana Park
 */

public class GraphCreator {
	
	private static final int MAX_DATA_POINTS = 20;
	private XYChart.Series<Number, Number> series1 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series2 = new XYChart.Series<>();
	private XYChart.Series<Number, Number> series3 = new XYChart.Series<>();
	private NumberAxis xAxis;
	private NumberAxis yAxis;
	
	protected LineChart<Number, Number> getLineChart() {
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

	@SuppressWarnings("unchecked")
	private LineChart<Number, Number>  setLineChart() {
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
	
	protected NumberAxis getXAxis() {
		return xAxis;
	}
	
	protected XYChart.Series<Number, Number> getSeries1(){
		return series1;
	}
	protected  XYChart.Series<Number, Number> getSeries2(){
		return series2;
	}
	protected  XYChart.Series<Number, Number> getSeries3(){
		return series3;
	}

}
