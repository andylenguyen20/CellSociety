package cellsociety_team07.visualization;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

/**
 * This MenuCreator class acts like a factory that churns out all the necessary parts for the Top part of the screen including two drop down
 * menus and two buttons all located in one HBox. When these buttons/drop down menus are clicked, appropriate reactions take place.
 * @author Dana Park
 */

public class MenuCreator {
	private static final int INSET_TYPE_X = 15;
	private static final int INSET_TYPE_Y = 12;
	private static final int SPACING =10;
	private ComboBox<String> commandsBox;
	private ComboBox<String> simulationMenu;
	private Button stepForward;
	private Button saveState;
	private ButtonMaker stepButtonMaker;
	private ButtonMaker stateSaveButtonMaker;

	protected String getResources(ResourceBundle rb, String s) {
		return rb.getString(s);
	}
	
	private ComboBox<String> getCommandsBox(ResourceBundle c) {
		commandsBox = new ComboBox<String>();
		commandsBox.setValue(getResources(c, "InitialCommand"));
		commandsBox.getItems().addAll(getResources(c, "PlayCommand"),
				getResources(c, "StopCommand"), getResources(c, "SlowerCommand"),
				getResources(c, "FasterCommand"));
		return commandsBox;
	}
	
	private ComboBox<String> getSimulationMenu(ResourceBundle s) {
		simulationMenu = new ComboBox<String>();
		simulationMenu.setValue(getResources(s, "InitialCommand"));
		simulationMenu.getItems().addAll(getResources(s, "GOLCommand"),
				getResources(s, "FireCommand"), getResources(s, "SegregationCommand"),
				getResources(s, "PredatorPreyCommand"));
		return simulationMenu;
	}

	
	private Button makeStepForward(ResourceBundle resource, String command) {
		stepButtonMaker = new ButtonMaker (resource, command);
		stepForward = stepButtonMaker.makeButton(stepForward, resource, command);
		return stepForward;
	}
	
	
	private Button makeSaveState(ResourceBundle resource, String command) {
		stateSaveButtonMaker = new ButtonMaker (resource, command);
		saveState = stateSaveButtonMaker.makeButton(saveState, resource, command);
		return saveState;
	}
	
	protected HBox addHBox(ResourceBundle com, ResourceBundle sim) {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(INSET_TYPE_Y, INSET_TYPE_X, INSET_TYPE_Y, INSET_TYPE_X));
	    hbox.setSpacing(SPACING);
	    hbox.setStyle("-fx-background-color: #336699;");
	    hbox.getChildren().addAll(getSimulationMenu(sim),getCommandsBox(com),makeStepForward(com,"StepForwardCommand"),makeSaveState(com, "SaveStateCommand"));
	    return hbox;
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
	
	protected Button saveStateButton() {
		return saveState;
	}

}

