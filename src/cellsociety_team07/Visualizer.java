package cellsociety_team07;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.*;


public class Visualizer extends Application{
	public static final int MY_SPEED= 10;
	public static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	private Timeline animation;
	private Simulation simulation;
	private ComboBox<String> simulationMenu;
    private ComboBox<String> commandsBox;
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private Stage stg;
	private GridPane gridPane;
	

	    @Override 
	    public void start(Stage stage) {
	    		stg = stage;
	    		simulation = new Simulation("xml/gol_simulation.xml");
	        stg.setTitle("CA Simulation");
	        
	        Scene scene = new Scene(new Group(), 500, 500);
	        Group root = (Group)scene.getRoot();
	        setUpGridPane();
	        root.getChildren().add(gridPane);
	        
	        double cellWidth = sceneWidth / simulation.getCells()[0].length;
	        double cellHeight = sceneHeight / simulation.getCells().length;

	        for(int i = 0; i < simulation.getCells().length; i++){
				for(int j = 0; j < simulation.getCells()[i].length; j++){
					Cell cell = simulation.getCells() [i][j];
	        			cell.setWidth(cellWidth);
	        			cell.setHeight(cellHeight);
	        			cell.setFill(cell.getColors());
	        			cell.setStroke(Color.WHITE);
	        			cell.setX(cellWidth * j + 45);
	    				cell.setY(cellHeight * i + 55);
	        			root.getChildren().add(cell);
	        		}
	        }

	        stg.setScene(scene);
	        stg.show();
	
	        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MY_SPEED));
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
	    }
	    
	    
	    private void setUpGridPane() {
	    	
	    	 		gridPane = new GridPane();

		        simulationMenu = new ComboBox<String>();
		        simulationMenu.getItems().addAll("Game of Life","Segregation","Predator/Prey","Fire");
		        simulationMenu.setValue("Choose Simulation");
		        
		        commandsBox = new ComboBox<String>();
		        commandsBox.getItems().addAll("Play", "Pause", "Slow Down", "Speed Up");  
		        commandsBox.setValue("Choose Command");
		        
		        Button stepForward = new Button ("Step Forward");
		        
		        gridPane.add(stepForward, 1, 3);
		        gridPane.add(new Label("Simulation: "), 0, 0);
		        gridPane.add(simulationMenu, 1, 0);
		        gridPane.add(new Label("Command: "), 2, 0);
		        gridPane.add(commandsBox, 3, 0);
		        
		        stepForward.setOnAction((e) -> {
		             handleStepForward("Step Forward");
		        });
		   }
	    
	
	        private void step(double elapsedTime) {
	    	
	    		update(simulation.getCells());
	   
	     		commandsBox.setOnAction((e) -> {
		             handleCommand(e);
		        });		    		
	    			
	    			simulationMenu.setOnAction((e) -> {
		             handleSimulation(e);
		    });
		    
	    }

		private void update(Cell[][] cell) {
		    	 for(int i = 0; i < simulation.getCells().length; i++){
						for(int j = 0; j < simulation.getCells()[i].length; j++){
							Cell c = simulation.getCells() [i][j];
							c.applyRules();
						}
		    	 }
		    	 for(int i = 0; i < simulation.getCells().length; i++){
					for(int j = 0; j < simulation.getCells()[i].length; j++){
						Cell c = simulation.getCells() [i][j];
						c.update();
						c.setFill(c.getColors());
					}
		    	 	}
		    }
	        
	    private void handleCommand(Event e) {
			String selectedAction = commandsBox.getSelectionModel().getSelectedItem();
    			
			if ( selectedAction.equals("Pause")) {
    				animation.stop();
			}
			if ( selectedAction.equals("Play")) {
				defaultRateAndPlay(1.0);
			}
			if ( selectedAction.equals("Speed Up")) {
				defaultRateAndPlay(1.0);
				animation.setRate(animation.getRate()*2);
			}
			if ( selectedAction.equals("Slow Down")) {
				defaultRateAndPlay(1.0);
				animation.setRate(animation.getRate()*0.5);
			}
		}
	    
	    private void handleSimulation(Event e) {
				String selectedAction = simulationMenu.getSelectionModel().getSelectedItem();
				if ( selectedAction.equals("Game of Life"))
	    				newSim("xml/gol_simulation.xml");
				if ( selectedAction.equals("Segregation"))
					newSim("xml/segregation_simulation.xml");
				if (selectedAction.equals("Predator/Prey"))
					newSim("xml/wator_simulation.xml");
				if ( selectedAction.equals("Fire"))
					newSim("xml/fire_simulation.xml");
					
			}
	    

	    private void newSim(String sim) {
	    		 simulation = new Simulation(sim);
	    		 Scene scene = new Scene(new Group(), 500, 500);
	    		 stg.setScene(scene);
	    		 stg.show();
	    	}
	   
	    
	    private void defaultRateAndPlay(double rate) {
	    		animation.setRate(rate);
	    		animation.play();
	    }
	    
	    
	    private void handleStepForward(String code){
	    	   switch(code){
				case "Step Forward": 
					update(simulation.getCells());
					animation.stop();
				break;
			default: break;
			}
		}
	    
	    public static void main(String[] args) {
	        launch(args);
	    }
			
	}
	

