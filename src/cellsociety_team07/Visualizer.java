package cellsociety_team07;

import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class Visualizer extends Application {
	private static final int MY_SPEED = 10;
	private static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	protected Timeline animation;
	protected Simulation simulation;
	private static final int SCENE_WIDTH = 500;
	private static final int SCENE_HEIGHT = 500;
	protected Stage stg;
	protected String currentSim;
	protected Scene myScene;
	protected String selectedAction;
	protected CommandHandler commandHandler;
	protected Group root;
	protected CellsToVisualize cellDrawer;
	protected SimulationHandler simHandler;
	private ResourceBundle myResources_C;
	private ResourceBundle myResources_S;
	private double [] props;

	
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private GraphCreator lineChart;
	
	@Override
	public void start(Stage stage) {
		stg = stage;
		stg.setTitle("CA Simulation");
		simulation = new Simulation("xml/wator_simulation.xml");
		getInitialProp();
		myScene = setUpGame(800, 800, "xml/wator_simulation.xml" );
		stg.setScene(myScene);

		stg.show();
		setAnimation(stg);
	}
	
	public void setAnimation(Stage s) {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MY_SPEED));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}


	protected Scene setUpGame(int height, int background, String sim) {
		root = new Group();
		commandHandler = new CommandHandler();
		simHandler = new SimulationHandler();
		myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
		myResources_S =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
		lineChart = new GraphCreator();
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(800, 800); 
	    borderPane.setTop(MenuCreator.addHBox(myResources_C, myResources_S));	    
		borderPane.setRight(TextFieldCreator.textFieldCreator());
		borderPane.setBottom(lineChart.getLineChart());
		borderPane.setStyle("-fx-padding: 10;" +"-fx-border-style: solid inside;" + "-fx-border-width: 2;" + 
							"-fx-border-insets: 5;" +"-fx-border-radius: 5;" + "-fx-border-color: blue;");

		Scene scene = new Scene(root, height, background);
		setSimulation(sim);
		drawFreshGrid();
		root.getChildren().add(borderPane);
		return scene;
	}
	
	private void drawFreshGrid() {
		
		CellsToVisualize.drawNewGrid(simulation, SCENE_WIDTH, SCENE_HEIGHT,root, props);
	}
	
	private void getInitialProp() {
		for (int i = 0; i < simulation.getCells().length; i++) {
			System.out.println("hi");
			for (int j = 0; j < simulation.getCells()[i].length; j++) {
				Cell cell = simulation.getCells()[i][j];
				props = new double [cell.getProps().length];
				System.out.println(props.length);
				props = cell.getProps();
			}
		}
	}

	private void step(double elapsedTime) {
	
		update();
		
	    TextFieldCreator.getSubmit().setOnAction((e) -> {
	    		props = new double[1];
	    		String str = TextFieldCreator.getTextValue().getText();
	    		String [] arrOfStr = str.split(":", 2);
	    		int i =	Integer.parseInt(arrOfStr[0]) ;
	    		double d = Double.parseDouble(arrOfStr[1]);
	    		props[i] =  d;
    		
	    });
		MenuCreator.stepButton().setOnAction((e) -> {
			handleStepForward(MenuCreator.getResources(myResources_C, "StepForwardCommand"));			
		});

		MenuCreator.commands().setOnAction((e) -> {
			commandHandler.handleCommand( e, animation);
		});
		MenuCreator.simulations().setOnAction((e) -> {
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
		String selectedAction = MenuCreator.simulations().getSelectionModel().getSelectedItem();
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
		myScene = setUpGame(800, 800, sim);
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
