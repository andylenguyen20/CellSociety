package cellsociety_team07;
import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.*;


public class Visualizer extends Application {
	
	

	  
	    @Override 
	    public void start(Stage stage) throws IOException {
	        stage.setTitle("CA Simulation");
	        Scene scene = new Scene(new Group(), 500, 500);
	        Group root = (Group)scene.getRoot();
	        GridPane grid = new GridPane();

	        final ComboBox<String> simulationmenu = new ComboBox<String>();
	        simulationmenu.getItems().addAll("Game of Life","Segregation","Predator/Prey","Fire");
	        
	        final ComboBox<String> commandsbox = new ComboBox<String>();
	        commandsbox.getItems().addAll("Pause", "Play","Skip forward");   

	        simulationmenu.setValue("Choose Simulation");
	        commandsbox.setValue("Choose Command");
	        

	       
	        grid.add(new Label("Simulation: "), 0, 0);
	        grid.add(simulationmenu, 1, 0);
	        grid.add(new Label("Command: "), 2, 0);
	        grid.add(commandsbox, 3, 0);
	
	       
	        root.getChildren().add(grid);
	        
	        stage.setScene(scene);
	        stage.show();
	    }  
	    
	    public static void main(String[] args) {
	        launch(args);
	    }
	}

