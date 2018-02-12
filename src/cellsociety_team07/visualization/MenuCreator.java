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
	private ComboBox<String> commandsBox;
	private ComboBox<String> simulationMenu;
	private Button stepForward;
	private Button multipleSims;

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
	
	protected Button makeButton(Button button, ResourceBundle c, String string) {
		button = new Button(getResources(c, string));
		return button;
	}
	
	public HBox addHBox(ResourceBundle com, ResourceBundle sim) {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    hbox.getChildren().addAll(getSimulationMenu(sim),getCommandsBox(com),makeStepForward(com),makeButton(multipleSims, sim,"MultipleSimulationsCommand"));
	    return hbox;
	}

	protected ComboBox<String> commands (){
		return commandsBox;
	}
	
	protected ComboBox<String> simulations (){
		return simulationMenu;
	}
	
	protected Button makeStepForward(ResourceBundle c) {
		stepForward = new Button(getResources(c, "StepForwardCommand"));
		return stepForward;
	}

	protected Button stepButton (){
		return stepForward;
	}

}

