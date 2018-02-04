package cellsociety_team07;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.*;


public class Visualizer extends Application{
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Timeline animation;
	private Simulation simulation;
	private ComboBox<String> simulationMenu;
    private ComboBox<String> commandsBox;
	private double mySpeed=30;
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private Stage stg;

	    @Override 
	    public void start(Stage stage) {
	    		stg = stage;
	    		simulation = new Simulation("xml/gol_simulation.xml");
	        stg.setTitle("CA Simulation");
	        
	        Scene scene = new Scene(new Group(), 500, 500);
	        scene.setOnKeyPressed(e -> {
				handleKeyInput(e.getCode());
			});
	        Group root = (Group)scene.getRoot();
	        GridPane gridPane = new GridPane();

	        simulationMenu = new ComboBox<String>();
	        simulationMenu.getItems().addAll("Game of Life","Segregation","Predator/Prey","Fire");
	        simulationMenu.setValue("Choose Simulation");
	        
	        commandsBox = new ComboBox<String>();
	        commandsBox.getItems().addAll("Play", "Pause","Skip forward", "Slow Down", "Speed Up");  
	        commandsBox.setValue("Choose Command");
	        
	        gridPane.add(new Label("Simulation: "), 0, 0);
	        gridPane.add(simulationMenu, 1, 0);
	        gridPane.add(new Label("Command: "), 2, 0);
	        gridPane.add(commandsBox, 3, 0);
	        
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
	       
	        root.getChildren().add(gridPane);

	        stg.setScene(scene);
	        //scene.setOnKeyPressed(e -> handleKeyInput(e.getCode()));
	        stg.show();
	        
	        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
	    }
	    
	    
	    private void step(double elapsedTime) {
	    	/*
	    	update(simulation.getCells());
    		commandsBox.setOnAction((e) -> {
	             handleCommand(e);
	        });
=======
	    		update(simulation.getCells());
>>>>>>> 8f565ef9ccaf0559a330a6e25c078984e1e00351
	    		
	     		commandsBox.setOnAction((e) -> {
		             handleCommand(e);
		        });		    		
	    			
	    			simulationMenu.setOnAction((e) -> {
		             handleSimulation(e);
		    });
		    */
	    }
	    
	    
	    private void handleCommand(Event e) {
			String selectedAction = commandsBox.getSelectionModel().getSelectedItem();
			if ( selectedAction.equals("Pause"))
    				animation.stop();
			if ( selectedAction.equals("Play"))
				animation.play();
			if ( selectedAction.equals("Speed Up"))
				setSpeed(getSpeed()*1.2);
			if ( selectedAction.equals("Slow Down"))
				setSpeed(getSpeed()*0.8);
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
	    private void newSim(String sim) {
	    		 simulation = new Simulation(sim);
	    		 Scene scene = new Scene(new Group(), 500, 500);
	    		 stg.setScene(scene);
	    		 stg.show();
	    	}
	    

	    private void setSpeed(double speed){
			mySpeed = speed;
		}
	    
	    private double getSpeed() {
	    		return mySpeed;
	    }
	    
	    public static void main(String[] args) {
	        launch(args);
	    }
	    
	    private void handleKeyInput(KeyCode code){
			switch(code){
				case T: 
					update(simulation.getCells());
					System.out.println("dog");
				break;
			default: break;
			}
		}
			
	}
	

