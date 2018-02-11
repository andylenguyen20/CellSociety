package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.layout.BorderPane;

public class BorderPaneFactory {
	private MenuCreator menuCreator = new MenuCreator();
	
	public  BorderPane bPaneMaker(ResourceBundle com, ResourceBundle sim , GraphCreator lineChart) {
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(800, 800); 
		borderPane.setTop(menuCreator.addHBox(com, sim));	    
		borderPane.setRight(TextFieldCreator.textFieldCreator("Enter->Index:Value"));
		//borderPane.setLeft(TextFieldCreator.textFieldCreator("Enter State"));

		borderPane.setBottom(lineChart.getLineChart());
		borderPane.setStyle("-fx-padding: 10;" +"-fx-border-style: solid inside;" + "-fx-border-width: 2;" + 
						"-fx-border-insets: 5;" +"-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return borderPane;
	}


}
