package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class GridPaneAssembler extends Visualizer {
	
	public void assembleGridPane(GridPane g, MenuCreator m, ResourceBundle com, ResourceBundle sim){
	g = new GridPane();
	m = new MenuCreator();
	
	com = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
	sim =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
	g.add(m.makeButton(com), 1, 3);
	g.add(new Label(m.getResources(sim, "LabelCommand")), 0, 0);
	g.add(m.getSimulationMenu(sim), 1, 0);
	g.add(new Label(m.getResources(com, "LabelCommand")), 2, 0);
	g.add(m.getCommandsBox(com), 3, 0);
	}

}
