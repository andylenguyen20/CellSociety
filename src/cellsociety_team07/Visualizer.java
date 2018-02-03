package cellsociety_team07;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.ComboBox;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;


public class Visualizer extends Application {
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Timeline animation;
	private Simulation simulation;
	private ComboBox<String> simulationMenu;
    private ComboBox<String> commandsBox;
	private double mySpeed;
	private Grid myGrid;

	
	
	  
	    @Override 
	    public void start(Stage stage) {
	    	    simulation = new Simulation("xml/gol_simulation.xml");
	        stage.setTitle("CA Simulation");
	        Scene scene = new Scene(new Group(), 500, 500);
	        Group root = (Group)scene.getRoot();
	        GridPane grid = new GridPane();

	        simulationMenu = new ComboBox<String>();
	        simulationMenu.getItems().addAll("Game of Life","Segregation","Predator/Prey","Fire");
	        
	        commandsBox = new ComboBox<String>();
	        commandsBox.getItems().addAll("Pause", "Play","Skip forward", "Slow Down", "Speed Up");   

	        simulationMenu.setValue("Choose Simulation");
	        commandsBox.setValue("Choose Command");
	        
	        
	       
	        grid.add(new Label("Simulation: "), 0, 0);
	        grid.add(simulationMenu, 1, 0);
	        grid.add(new Label("Command: "), 2, 0);
	        grid.add(commandsBox, 3, 0);
	
	       
	        root.getChildren().add(grid);

	        stage.setScene(scene);
	        stage.show();
	        
	        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
	    }
	    
	    
	    private void step(double elapsedTime) {
	    	//todo
	    
	    }

	    private void setSpeed(double speed){
			mySpeed = speed;
		}
	    
	    private double getSpeed() {
	    		return mySpeed;
	    }
		
	    
	    private void pause() {
	    		if ( commandsBox.getValue().toString()=="pause")
	    			animation.stop();
	    		}
	    
	    private void play() {
	    	if ( commandsBox.getValue().toString()=="play")
	    		animation.play();
	    }
	    
	    private void getSimulation() {
	    		simulationMenu.getValue().toString();
	    	}
	    
	    private void getCommand() {
	    		commandsBox.getValue().toString();
	    }
	    
	    private void speedUp() {
	    		if ( commandsBox.getValue().toString()=="Speed Up")
	    			setSpeed(getSpeed()*1.2);
	    	
	    }
	    private void slowDown() {
	    		if ( commandsBox.getValue().toString()=="Slow Down")
	    			setSpeed(getSpeed()*0.8);
    	
	    }
	    
	    
	    
	    public static void main(String[] args) {
	        launch(args);
	    }
	}

