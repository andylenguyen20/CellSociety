package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MenuCreator extends Visualizer {
	
	protected ResourceBundle myResources_C;
	protected ResourceBundle myResources_S;
	protected ComboBox<String> simulationMenu;
	protected ComboBox<String> commandsBox;
	protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	protected GridPane gridPane;

	
	
	
	
	public void createDropDown() {
		
	gridPane = new GridPane();
	

	myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
	myResources_S = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
	
	simulationMenu = new ComboBox<String>();
	simulationMenu.setValue(getResources(myResources_S, "InitialCommand"));

	simulationMenu.getItems().addAll(getResources(myResources_S, "GOLCommand"),
			getResources(myResources_S, "FireCommand"), getResources(myResources_S, "SegregationCommand"),
			getResources(myResources_S, "PredatorPreyCommand"));
	
	commandsBox = new ComboBox<String>();
	commandsBox.setValue(getResources(myResources_C, "InitialCommand"));

	commandsBox.getItems().addAll(getResources(myResources_C, "PlayCommand"),
			getResources(myResources_C, "StopCommand"), getResources(myResources_C, "SlowerCommand"),
			getResources(myResources_C, "FasterCommand"));
	
	addToGridPane();
	
	}
	
	private void addToGridPane() {
		Button stepForward = new Button(getResources(myResources_C, "StepForwardCommand"));
		
		gridPane.add(stepForward, 1, 3);
		gridPane.add(new Label(getResources(myResources_S, "LabelCommand")), 0, 0);
		gridPane.add(simulationMenu, 1, 0);
		gridPane.add(new Label(getResources(myResources_C, "LabelCommand")), 2, 0);
		gridPane.add(commandsBox, 3, 0);
		stepForward.setOnAction((e) -> {
			handleStepForward(getResources(myResources_C, "StepForwardCommand"));
		});

	}
	
	private String getResources(ResourceBundle rb, String s) {
		return rb.getString(s);
	}
	protected ResourceBundle getResourceS() {
		return myResources_S;
	}
	
	protected ResourceBundle getResourceC() {
		return myResources_C;
	}
	
	protected GridPane getGridPane() {
		return gridPane;
	}
	
	protected ComboBox<String> getCommandsBox() {
		return commandsBox;
	}
	
	protected ComboBox<String> getSimulationMenu() {
		return simulationMenu;
	}
	


}
