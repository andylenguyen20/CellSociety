package cellsociety_team07;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;

public class GraphCreator {
	
	public HBox getLineChart() {
		NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time Passed");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Population Count");

        LineChart lineChart = new LineChart(xAxis, yAxis);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("CA Simulation");

        dataSeries1.getData().add(new XYChart.Data( 1, 1));
        dataSeries1.getData().add(new XYChart.Data( 2, 1));
        dataSeries1.getData().add(new XYChart.Data(3, 1));

        lineChart.getData().add(dataSeries1);
        lineChart.setPrefHeight(150);
        lineChart.setMinHeight(150);
        lineChart.setMaxHeight(150);

        lineChart.setPrefWidth(760);
        lineChart.setMinWidth(750);
        lineChart.setMaxWidth(760);

        HBox hbox = new HBox(lineChart);

        return hbox;

	}
	
	public void addData(LineChart chart, int x, int y) {
		chart.getData().add(new XYChart.Data(x,y));
		
	}

}
