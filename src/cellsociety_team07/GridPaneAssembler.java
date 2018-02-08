package cellsociety_team07;

import java.util.ResourceBundle;
import javafx.scene.layout.GridPane;

public class GridPaneAssembler{
	
	private GridPane gridPane;
	private ResourceBundle myResources_C;
	private ResourceBundle myResources_S;
	private MenuCreator menuCreator;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";

	public void assembleGridPane(GridPane g, MenuCreator m) {
		gridPane = g;
		menuCreator = m;
		myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
		myResources_S =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
		g.add(menuCreator.makeButton(myResources_C), 1, 3);
		g.add(menuCreator.makeLabel(myResources_S), 0, 0);
		g.add(menuCreator.getSimulationMenu(myResources_S), 1, 0);
		g.add(menuCreator.makeLabel(myResources_C), 2, 0);
		g.add(menuCreator.getCommandsBox(myResources_C), 3, 0);
	}
	
	public GridPane getGridPane() {
		return gridPane;
	}
	protected ResourceBundle getResourceC() {
		return myResources_C;
	}
	
}
