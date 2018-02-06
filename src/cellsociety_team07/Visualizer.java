package cellsociety_team07;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane; 
import javafx.scene.control.*;

public class Visualizer extends Application {
	private static final int MY_SPEED = 10;
	private static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	protected Timeline animation;
	protected Simulation simulation;
	protected ComboBox<String> simulationMenu;
	protected ComboBox<String> commandsBox;
	protected double sceneWidth = 400;
	protected double sceneHeight = 400;
	protected Stage stg;
	protected GridPane gridPane;
	protected String currentSim;
	protected Scene myScene;
	protected ResourceBundle myResources_C;
	protected ResourceBundle myResources_S;
	protected Button stepForward;
	protected MenuCreator menuCreator;
	protected String selectedAction;
	protected CommandHandler commandHandler;
	protected Group root;
	protected CellsToVisualize cellDrawer;
	protected GridPaneAssembler paneAssembler;
	
	@Override
	public void start(Stage stage) {
		stg = stage;
		stg.setTitle("CA Simulation");
		myScene = setUpGame(500, 500, "xml/fire_simulation.xml" );
		stg.setScene(myScene);
		stg.show();
		commandHandler = new CommandHandler();
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MY_SPEED));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}


	protected Scene setUpGame(int height, int background, String sim) {
		root = new Group();
		Scene scene = new Scene(root, height, background);
		setSimulation(sim);
		setUpGridPane();
		root.getChildren().add(paneAssembler.getGridPane());
		drawFreshGrid();
		return scene;
	}
	
	private void drawFreshGrid() {
		cellDrawer = new CellsToVisualize();
		for (int i = 0; i < simulation.getCells().length; i++) {
			for (int j = 0; j < simulation.getCells()[i].length; j++) {
				Cell cell = simulation.getCells()[i][j];
				cellDrawer.visualizeCell(cell,simulation, sceneWidth, sceneHeight);
			    cellDrawer.getCell().setX(sceneWidth / simulation.getCells()[0].length * j + 45);
                cellDrawer.getCell().setY(sceneHeight / simulation.getCells().length * i + 55);
				root.getChildren().add(cell);
			}
		}
	}

	private void setUpGridPane() {
		gridPane = new GridPane();	
		menuCreator = new MenuCreator();
		paneAssembler = new GridPaneAssembler();
		paneAssembler.assembleGridPane(gridPane, menuCreator);
		menuCreator.stepButton().setOnAction((e) -> {
			handleStepForward(menuCreator.getResources(paneAssembler.getResourceC(), "StepForwardCommand"));			
		});
	}

	private void step(double elapsedTime) {
		update();
		menuCreator.commands().setOnAction((e) -> {
			commandHandler.handleCommand(e, animation, menuCreator);
		});
		menuCreator.simulations().setOnAction((e) -> {
			handleSimulation(e) ;
		});
	}
	
	protected void update() {
		Grid grid = simulation.getGrid();
		grid.prepareNextState();
		grid.update();
		for (Cell[] cells : grid.getCells()) {
			for (Cell cell : cells)
				root.getChildren().remove(cell);
			}
		drawFreshGrid();
	  }
	
	private void handleSimulation(Event e) {
		String selectedAction = menuCreator.simulations().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Game of Life")) 
			newSim("xml/gol_simulation.xml");
		if (selectedAction.equals("Segregation"))
			newSim("xml/segregation_simulation.xml");
		if (selectedAction.equals("Predator/Prey")) 
			newSim("xml/wator_simulation.xml");
		if (selectedAction.equals("Fire"))
			newSim("xml/fire_simulation.xml");
		}
	
	protected void newSim(String sim) {
		myScene = setUpGame(500, 500, sim);
		stg.setScene(myScene);
		stg.show();
		commandHandler.defaultRateAndPlay(1.0, animation);
	}
	
	protected void handleStepForward(String code) {
		switch (code) {
		case "Step Forward":
			update();
		    animation.stop();
			break;
		default:
			break;
		}
	}

	private void setSimulation(String s) {
		simulation = new Simulation(s);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
