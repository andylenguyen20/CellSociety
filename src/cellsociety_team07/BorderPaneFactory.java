package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.layout.BorderPane;

public class BorderPaneFactory {
	private StateChangeTextField stateChangeText= new StateChangeTextField();
	private PropsChangeTextField propsChangeText= new PropsChangeTextField();
	private MenuCreator mCreator = new MenuCreator();
	myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");


	
	public BorderPane bPaneMaker(ResourceBundle com, ResourceBundle sim , GraphCreator lineChart) {
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(800, 800); 
		borderPane.setTop(menuCreator.addHBox(myResources_C, myResources_S));	    
		borderPane.setRight(propsChanger.propsHBoxMaker(myResources_C, "EnterPropChangeCommand", "SubmitCommand"));
		borderPane.setLeft(stateChanger.stateHBoxMaker(myResources_C, "EnterStateChangeCommand", "EnterCommand"));
		borderPane.setBottom(graphCreator.getLineChart());
		borderPane.setStyle("-fx-padding: 10;" +"-fx-border-style: solid inside;" + "-fx-border-width: 2;" + 
						"-fx-border-insets: 5;" +"-fx-border-radius: 5;" + "-fx-border-color: blue;");
		return borderPane;
	}


}

