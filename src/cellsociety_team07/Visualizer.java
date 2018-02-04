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
import javafx.scene.shape.Rectangle;
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
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private int r = 0;
	private int c = 0;

	
	
	  
	    @Override 
	    public void start(Stage stage) {
	    	simulation = new Simulation("xml/gol_simulation.xml");
	        stage.setTitle("CA Simulation");
	        Scene scene = new Scene(new Group(), 500, 500);
	        Group root = (Group)scene.getRoot();
	        GridPane gridPane = new GridPane();

	        simulationMenu = new ComboBox<String>();
	        simulationMenu.getItems().addAll("Game of Life","Segregation","Predator/Prey","Fire");
	        
	        commandsBox = new ComboBox<String>();
	        commandsBox.getItems().addAll("Pause", "Play","Skip forward", "Slow Down", "Speed Up");   

	        simulationMenu.setValue("Choose Simulation");
	        commandsBox.setValue("Choose Command");
	        
	        
	       
	        gridPane.add(new Label("Simulation: "), 0, 0);
	        gridPane.add(simulationMenu, 1, 0);
	        gridPane.add(new Label("Command: "), 2, 0);
	        gridPane.add(commandsBox, 3, 0);
	
	        double cellWidth = sceneWidth / simulation.getCells()[0].length;
	        double cellHeight = sceneHeight / simulation.getCells().length;
	        
	        for (Cell[] cells : simulation.getCells()) {
	        	r++;
	        		for (Cell cell : cells) {
	        			c++;
	        			cell.setWidth(cellWidth);
	        			cell.setHeight(cellHeight);
	        			cell.setFill(cell.getColors());
	        			cell.setX(sceneWidth / cellWidth * c);
	    				cell.setY(sceneHeight / cellHeight * r);
	        			root.getChildren().add(cell);
	        		}
	        }
	       
	        root.getChildren().add(gridPane);

	        stage.setScene(scene);
	        stage.show();
	        
	        KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(SECOND_DELAY));
			animation = new Timeline();
			animation.setCycleCount(Timeline.INDEFINITE);
			animation.getKeyFrames().add(frame);
			animation.play();
	    }
	    
	    
	    private void step(double elapsedTime) {
	    	pause();
	    	play();
	    	speedUp();
	    	slowDown();
	    	update();
	    }
	    
	    private void update() {
	    		
	    }

	    private void setSpeed(double speed){
			mySpeed = speed;
		}
	    
	    private double getSpeed() {
	    		return mySpeed;
	    }
		
	    
	    private void pause() {
	    		if ( getCommand()=="pause")
	    			animation.stop();
	    		}
	    
	    private void play() {
	    	if ( getCommand()=="play")
	    		animation.play();
	    }
	    
	    private String getSimulation() {
	    		return simulationMenu.getValue().toString();
	    	}
	    
	    private String getCommand() {
	    		return commandsBox.getValue().toString();
	    }
	    
	    private void speedUp() {
	    		if ( getCommand() =="Speed Up")
	    			setSpeed(getSpeed()*1.2);
	    	}
	    
	    private void slowDown() {
	    		if ( getCommand() =="Slow Down")
	    			setSpeed(getSpeed()*0.8);
    		}
	    
	    
	    
	    public static void main(String[] args) {
	        launch(args);
	    }
	}

