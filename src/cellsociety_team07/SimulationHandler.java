package cellsociety_team07;

import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulationHandler{

	protected void handleSimulation(String selectedAction, Event e, MenuCreator m, Scene scene, Stage stage, Timeline a) {
		System.out.println ("before string");
		selectedAction = m.simulations().getSelectionModel().getSelectedItem();
		System.out.println ("after string");
		if (selectedAction.equals("Game of Life")) 
			newSim("xml/gol_simulation.xml", scene, stage, a);
		if (selectedAction.equals("Segregation"))
			newSim("xml/segregation_simulation.xml", scene, stage, a);
		if (selectedAction.equals("Predator/Prey")) 
			newSim("xml/wator_simulation.xml", scene, stage, a);
		if (selectedAction.equals("Fire"))
			newSim("xml/fire_simulation.xml",scene, stage, a);
		}
	
	
	protected void newSim(String sim, Scene scene, Stage stage, Timeline a) {
		Visualizer vis = new Visualizer();
		scene = vis.setUpGame(500, 500, sim);
		stage.setScene(scene);
		stage.show();
		vis.setAnimation(stage);
	}


}
