package cellsociety_team07;

import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SimulationHandler{

	
	
	protected void handleSimulation(Event e, MenuCreator m, Scene scene, Stage stage, Timeline a, Simulation simulate, double width, double height) {

		String selectedAction = m.simulations().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Game of Life")) 
			
			newSim("xml/gol_simulation.xml", scene, stage, a, simulate, width, height);
			
		if (selectedAction.equals("Segregation"))

			newSim("xml/segregation_simulation.xml", scene, stage, a,simulate, width, height);
			
		if (selectedAction.equals("Predator/Prey")) 

			newSim("xml/wator_simulation.xml", scene, stage, a,simulate, width, height);
			
		if (selectedAction.equals("Fire")) 

			newSim("xml/fire_simulation.xml",scene, stage, a,simulate, width, height);
		
		}
	
	
	protected void newSim(String sim, Scene scene, Stage stage, Timeline a, Simulation simulate, double width, double height) {
		stage = new Stage();
		Visualizer vis = new Visualizer();

		scene = vis.setUpGame(500, 500, sim );

		//stage.setScene(scene);
		System.out.println("hellobitches");

//		stage.show();
//		vis.setAnimation(stage);
	}


}
