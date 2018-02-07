package cellsociety_team07;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MenuCreator {
	
	private ComboBox<String> commandsBox;
	private ComboBox<String> simulationMenu;
	private Button stepForward;

	protected String getResources(ResourceBundle rb, String s) {
		return rb.getString(s);
	}
	
	protected ComboBox<String> getCommandsBox(ResourceBundle c) {
		commandsBox = new ComboBox<String>();
		commandsBox.setValue(getResources(c, "InitialCommand"));
		commandsBox.getItems().addAll(getResources(c, "PlayCommand"),
				getResources(c, "StopCommand"), getResources(c, "SlowerCommand"),
				getResources(c, "FasterCommand"));
		return commandsBox;
	}
	
	protected ComboBox<String> getSimulationMenu(ResourceBundle s) {
		simulationMenu = new ComboBox<String>();
		simulationMenu.setValue(getResources(s, "InitialCommand"));
		simulationMenu.getItems().addAll(getResources(s, "GOLCommand"),
				getResources(s, "FireCommand"), getResources(s, "SegregationCommand"),
				getResources(s, "PredatorPreyCommand"));
		return simulationMenu;
	}
	
	protected Button makeButton(ResourceBundle c) {
		stepForward = new Button(getResources(c, "StepForwardCommand"));
		return stepForward;
	}
	
	protected ComboBox<String> commands (){
		return commandsBox;
	}
	
	protected ComboBox<String> simulations (){
		return simulationMenu;
	}
	
	protected Button stepButton (){
		return stepForward;
	}

}
