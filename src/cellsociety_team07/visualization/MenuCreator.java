//package cellsociety_team07;
//
//import java.util.ResourceBundle;
//
//import javafx.geometry.Insets;
//import javafx.scene.control.Button;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.Priority;
//
//public class MenuCreator {
//	
//	private static ComboBox<String> commandsBox;
//	private static ComboBox<String> simulationMenu;
//	private static Button stepForward;
//	private static Button multipleSims;
//	private static final int INSET_ONE=12;
//	private static final int INSET_TWO=15;
//	private static final int HBOX_SPACING=10;
//
//
//	protected static String getResources(ResourceBundle rb, String s) {
//		return rb.getString(s);
//	}
//	
//	protected static ComboBox<String> getCommandsBox(ResourceBundle c) {
//		commandsBox = new ComboBox<String>();
//		commandsBox.setValue(getResources(c, "InitialCommand"));
//		commandsBox.getItems().addAll(getResources(c, "PlayCommand"),
//				getResources(c, "StopCommand"), getResources(c, "SlowerCommand"),
//				getResources(c, "FasterCommand"));
//		return commandsBox;
//	}
//	
//	protected static ComboBox<String> getSimulationMenu(ResourceBundle s) {
//		simulationMenu = new ComboBox<String>();
//		simulationMenu.setValue(getResources(s, "InitialCommand"));
//		simulationMenu.getItems().addAll(getResources(s, "GOLCommand"),
//				getResources(s, "FireCommand"), getResources(s, "SegregationCommand"),
//				getResources(s, "PredatorPreyCommand"));
//		return simulationMenu;
//	}
//	
//	protected static Button makeButton(Button button, ResourceBundle c, String string) {
//		button = new Button(getResources(c, string));
//		return button;
//	}
//	
//	public static HBox addHBox(ResourceBundle com, ResourceBundle sim) {
//	    HBox hbox = new HBox();
//	    hbox.setPadding(new Insets(INSET_TWO, INSET_ONE, INSET_TWO, INSET_ONE));
//	    hbox.setSpacing(HBOX_SPACING);
//	    hbox.setStyle("-fx-background-color: #336699;");
//	    hbox.getChildren().addAll(getSimulationMenu(sim),getCommandsBox(com),makeStepForward(com),makeButton(multipleSims, sim,"MultipleSimulationsCommand"));
//	    return hbox;
//	}
//
//	protected static ComboBox<String> commands (){
//		return commandsBox;
//	}
//	
//	protected static ComboBox<String> simulations (){
//		return simulationMenu;
//	}
//	
//	protected static Button makeStepForward(ResourceBundle c) {
//		stepForward = new Button(getResources(c, "StepForwardCommand"));
//		return stepForward;
//	}
//
//	protected static Button stepButton (){
//		return stepForward;
//	}
//
//}

package cellsociety_team07.visualization;

import java.util.ResourceBundle;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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

