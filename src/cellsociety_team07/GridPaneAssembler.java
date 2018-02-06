package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GridPaneAssembler extends Visualizer {
	
	public void assembleGridPane(GridPane g, MenuCreator m) {
		gridPane = g;
		myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
		myResources_S =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
		gridPane.add(m.makeButton(myResources_C), 1, 3);
		gridPane.add(new Label(m.getResources(myResources_S, "LabelCommand")), 0, 0);
		gridPane.add(m.getSimulationMenu(myResources_S), 1, 0);
		gridPane.add(new Label(m.getResources(myResources_C, "LabelCommand")), 2, 0);
		gridPane.add(m.getCommandsBox(myResources_C), 3, 0);
	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	protected ResourceBundle getResourceC() {
		return myResources_C;
	}
	
}
