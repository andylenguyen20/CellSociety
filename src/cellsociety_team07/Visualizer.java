package cellsociety_team07;

import java.util.ResourceBundle;

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

public class Visualizer extends Application {
	public static final int MY_SPEED = 10;
	public static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	public static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Timeline animation;
	private Simulation simulation;
	private ComboBox<String> simulationMenu;
	private ComboBox<String> commandsBox;
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private Stage stg;
	private GridPane gridPane;
	private String currentSim = "xml/gol_simulation.xml";
	private Scene myScene;
	private ResourceBundle myResources_C;
	private ResourceBundle myResources_S;

	@Override
	public void start(Stage stage) {
		stg = stage;

		stg.setTitle("CA Simulation");

		myScene = setUpGame(500, 500);
		stg.setScene(myScene);
		stg.show();

		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MY_SPEED));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private Scene setUpGame(int height, int background) {
		Group root = new Group();
		Scene scene = new Scene(root, height, background);
		setSimulation(currentSim);
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

		myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
		myResources_S = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");

		simulationMenu = new ComboBox<String>();
		simulationMenu.setValue(getResources(myResources_S, "InitialCommand"));

		simulationMenu.getItems().addAll(getResources(myResources_S, "GOLCommand"),
				getResources(myResources_S, "FireCommand"), getResources(myResources_S, "SegregationCommand"),
				getResources(myResources_S, "PredatorPreyCommand"));

		commandsBox = new ComboBox<String>();
		commandsBox.setValue(getResources(myResources_C, "InitialCommand"));

		commandsBox.getItems().addAll(getResources(myResources_C, "PlayCommand"),
				getResources(myResources_C, "StopCommand"), getResources(myResources_C, "SlowerCommand"),
				getResources(myResources_C, "FasterCommand"));
		addToGridPane();

	}

	private void addToGridPane() {
		Button stepForward = new Button(getResources(myResources_C, "StepForwardCommand"));
		gridPane.add(stepForward, 1, 3);
		gridPane.add(new Label(getResources(myResources_S, "LabelCommand")), 0, 0);
		gridPane.add(simulationMenu, 1, 0);
		gridPane.add(new Label(getResources(myResources_C, "LabelCommand")), 2, 0);
		gridPane.add(commandsBox, 3, 0);
		stepForward.setOnAction((e) -> {
			handleStepForward(getResources(myResources_C, "StepForwardCommand"));
		});

	}

	private String getResources(ResourceBundle rb, String s) {
		return rb.getString(s);
	}

	private void step(double elapsedTime) {

		update();

		commandsBox.setOnAction((e) -> {
			handleCommand(e);
		});

		simulationMenu.setOnAction((e) -> {
			handleSimulation(e);
		});

	}
	
	private void update() {
		Grid grid = simulation.getGrid();
		grid.prepareNextState();
		grid.update();
		for (Cell[] cells : grid.getCells()) {
			for (Cell cell : cells)
				cell.setFill(cell.getColors());
		}
	}


	private void handleCommand(Event e) {
		String selectedAction = commandsBox.getSelectionModel().getSelectedItem();

		if (selectedAction.equals("Pause")) {
			animation.stop();
		}
		if (selectedAction.equals("Play")) {
			defaultRateAndPlay(1.0);
		}
		if (selectedAction.equals("Speed Up")) {
			defaultRateAndPlay(1.0);
			animation.setRate(animation.getRate() * 2);
		}
		if (selectedAction.equals("Slow Down")) {
			defaultRateAndPlay(1.0);
			animation.setRate(animation.getRate() * 0.5);
		}
	}

	private void handleSimulation(Event e) {
		String selectedAction = simulationMenu.getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Game of Life"))
			newSim("xml/gol_simulation.xml");

		if (selectedAction.equals("Segregation"))
			newSim("xml/segregation_simulation.xml");

		if (selectedAction.equals("Predator/Prey"))
			newSim("xml/wator_simulation.xml");

		if (selectedAction.equals("Fire"))
			newSim("xml/fire_simulation.xml");
	}
	
	private void handleStepForward(String code) {
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

	private void defaultRateAndPlay(double rate) {
		animation.setRate(rate);
		animation.play();
	}

	private void newSim(String sim) {
		currentSim = sim;
		Scene scene = setUpGame(500, 500);
		stg.setScene(scene);
		stg.show();
		defaultRateAndPlay(1.0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
