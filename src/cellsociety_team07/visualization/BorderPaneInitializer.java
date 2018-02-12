package cellsociety_team07.visualization;

import javafx.scene.chart.LineChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

/**
 * This BorderPaneInitializer class creates the borderpane that contains all UI aspects when the simulation runs on screen
 * 
 * @author Dana Park
 */

public class BorderPaneInitializer {
	private static  final int PANE_XY_DIMENSIONS = 800;

	protected static BorderPane setUpBorderPane(HBox menuCreator, HBox propsChanger, HBox statesChanger, LineChart<Number, Number> lineChart) {
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(PANE_XY_DIMENSIONS, PANE_XY_DIMENSIONS); 
		borderPane.setTop(menuCreator);	    
		borderPane.setRight(propsChanger);
		borderPane.setLeft(statesChanger);
		borderPane.setBottom(lineChart);
		borderPane.setStyle("-fx-padding: 10;" +"-fx-border-style: solid inside;" + "-fx-border-width: 2;" + 
						"-fx-border-insets: 5;" +"-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return borderPane;
		
	}

}
