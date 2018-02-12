package cellsociety_team07.visualization;

import javafx.scene.chart.LineChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class BorderPaneInitializer {

	protected static BorderPane setUpBorderPane(HBox menuCreator, HBox propsChanger, HBox statesChanger, LineChart<Number, Number> lineChart) {
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(800, 800); 
		borderPane.setTop(menuCreator);	    
		borderPane.setRight(propsChanger);
		borderPane.setLeft(statesChanger);
		borderPane.setBottom(lineChart);
		borderPane.setStyle("-fx-padding: 10;" +"-fx-border-style: solid inside;" + "-fx-border-width: 2;" + 
						"-fx-border-insets: 5;" +"-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return borderPane;
		
	}

}
