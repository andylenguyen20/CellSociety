package cellsociety_team07;

import java.awt.geom.Point2D;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Visualizer extends Application {
	private static final int MY_SPEED = 10;
	private static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	private Timeline animation;
	private Simulation simulation;
	private static final int SCENE_WIDTH = 500;
	private static final int SCENE_HEIGHT = 500;
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 800;
	private Stage stg;
	private Scene myScene;
	private CommandHandler commandHandler;
	private Group root;
	private ResourceBundle myResources_C;
	private ResourceBundle myResources_S;
	private double [] props;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private GraphCreator lineChart;
	private int propsLength;
	private int cellState=1;
	private StateChangeTextField stateChanger;
	private PropsChangeTextField propsChanger;
	private BorderPaneFactory borderMaker = new BorderPaneFactory();
	private MenuCreator menuCreator;
	
	@Override
	public void start(Stage stage) {
		stg = stage;
		stg.setTitle("CA Simulation");
		simulation = new Simulation("xml/fire_simulation.xml");
		getInitialProp();
		myScene = setUpGame(SCREEN_WIDTH, SCREEN_HEIGHT, "xml/fire_simulation.xml" );
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
		myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
		myResources_S =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
		stateChanger = new StateChangeTextField();
		propsChanger = new PropsChangeTextField();
		menuCreator = new MenuCreator();
		lineChart = new GraphCreator();
		
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(800, 800); 
		borderPane.setTop(menuCreator.addHBox(myResources_C, myResources_S));	    
		borderPane.setRight(propsChanger.propsHBoxMaker("Index:Prop"));
		borderPane.setLeft(stateChanger.stateHBoxMaker("Enter State"));

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
		CellsToVisualize.drawNewGrid(simulation, SCENE_WIDTH, SCENE_HEIGHT,root, props, cellState);
	}
	
	private void getInitialProp() {
		
		List<Cell> cells = simulation.getCells();
		for(Cell cell : cells) {
			propsLength = cell.getProps().length;
			props = new double [propsLength]; 
			props = cell.getProps();
			//cellState = cell.getCurrentState();
			
				
			}
		}
	

	private void step(double elapsedTime) {
		update();
		
	    propsChanger.getSubmit().setOnAction((e) -> {
	   	    		handleParamChanges(e);
	     });
	    
	    stateChanger.getEnter().setOnAction((e) -> {
    			cellState = Integer.parseInt(stateChanger.getTextValue().getText());
		 });
		menuCreator.stepButton().setOnAction((e) -> {
			handleStepForward(menuCreator.getResources(myResources_C, "StepForwardCommand"));			
		});
		menuCreator.commands().setOnAction((e) -> {
			commandHandler.handleCommand( e, animation, menuCreator);
		});
		menuCreator.simulations().setOnAction((e) -> {
			handleSimulation(e) ;
		});
	}

	protected void update() {
		Grid grid = simulation.getGrid();
		grid.prepareNextState();
		grid.update();
		for(Cell cell : grid.getCells()) {
			root.getChildren().remove(cell);
		}
		drawFreshGrid();
	  }
	
	private void handleParamChanges(Event e) {
		props = new double[propsLength];
		String str = propsChanger.getTextValue().getText();
		String [] arrOfStr = str.split(":", 2);
		int i =	Integer.parseInt(arrOfStr[0]) ;
		double d = Double.parseDouble(arrOfStr[1]);
		props[i] =  d;
	}
	
	private void handleSimulation(Event e) {
		String selectedAction = menuCreator.simulations().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Game of Life")) {
			newSim("xml/gol_simulation.xml");

		}
		if (selectedAction.equals("Segregation"))
			newSim("xml/segregation_simulation.xml");
		if (selectedAction.equals("Predator/Prey")) 
			newSim("xml/wator_simulation.xml");
		if (selectedAction.equals("Fire")) {
			newSim("xml/fire_simulation.xml");
		}
		}
	
	protected void newSim(String sim) {
		myScene = setUpGame(SCREEN_WIDTH, SCREEN_HEIGHT, sim);
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
