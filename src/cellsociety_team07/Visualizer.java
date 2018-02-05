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
import javafx.scene.paint.Color;
import javafx.scene.control.*;

public class Visualizer extends Application {
	private static final int MY_SPEED = 10;
	private static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	protected static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	protected Timeline animation;
	protected Simulation simulation;
	protected ComboBox<String> simulationMenu;
	protected ComboBox<String> commandsBox;
	private double sceneWidth = 400;
	private double sceneHeight = 400;
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
		Group root = new Group();
		Scene scene = new Scene(root, height, background);
		
		setSimulation(sim);
		setUpGridPane();
		root.getChildren().add(gridPane);
		double cellWidth = sceneWidth / simulation.getCells()[0].length;
		double cellHeight = sceneHeight / simulation.getCells().length;
		for (int i = 0; i < simulation.getCells().length; i++) {
			for (int j = 0; j < simulation.getCells()[i].length; j++) {
				Cell cell = simulation.getCells()[i][j];
				cell.setWidth(cellWidth);
				cell.setHeight(cellHeight);
				cell.setFill(cell.getColors());
				cell.setStroke(Color.WHITE);
				cell.setX(cellWidth * j + 45);
				cell.setY(cellHeight * i + 55);
				root.getChildren().add(cell);
			}
		}

		return scene;
	}

	private void setUpGridPane() {
		gridPane = new GridPane();	
		menuCreator = new MenuCreator();
		myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
		myResources_S =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
		gridPane.add(menuCreator.makeButton(myResources_C), 1, 3);
		gridPane.add(new Label(menuCreator.getResources(myResources_S, "LabelCommand")), 0, 0);
		gridPane.add(menuCreator.getSimulationMenu(myResources_S), 1, 0);
		gridPane.add(new Label(menuCreator.getResources(myResources_C, "LabelCommand")), 2, 0);
		gridPane.add(menuCreator.getCommandsBox(myResources_C), 3, 0);
		menuCreator.stepButton().setOnAction((e) -> {
			handleStepForward(menuCreator.getResources(myResources_C, "StepForwardCommand"));			
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
				cell.setFill(cell.getColors());
		}
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
//	
	protected void newSim(String sim) {
		myScene = setUpGame(500, 500, sim);
		stg.setScene(myScene);
		stg.show();
	
		commandHandler.defaultRateAndPlay(1.0, animation);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
