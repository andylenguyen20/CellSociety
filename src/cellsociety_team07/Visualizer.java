package cellsociety_team07;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.shape.Polygon; 

public class Visualizer extends Application {
	private static final int MY_SPEED = 10;
	private static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	protected Timeline animation;
	protected Simulation simulation;
	protected double sceneWidth = 400;
	protected double sceneHeight = 400;
	protected Stage stg;
	protected GridPane gridPane;
	protected String currentSim;
	protected Scene myScene;
	protected MenuCreator menuCreator;
	protected String selectedAction;
	protected CommandHandler commandHandler;
	protected Group root;
	protected CellsToVisualize cellDrawer;
	protected GridPaneAssembler paneAssembler;
	protected SimulationHandler simHandler;
	protected List<Polygon> testDrawer = new ArrayList<Polygon>();
	
	@Override
	public void start(Stage stage) {
		stg = stage;
		stg.setTitle("CA Simulation");
		myScene = setUpGame(500, 500, "xml/gol_simulation.xml" );
		stg.setScene(myScene);
		stg.show();
		commandHandler = new CommandHandler();
		simHandler = new SimulationHandler();
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
		Scene scene = new Scene(root, height, background);
		setSimulation(sim);
		setUpGridPane();
		root.getChildren().add(paneAssembler.getGridPane());
		drawFreshGrid();
		return scene;
	}
	
	
	private void drawFreshGrid() {
		
		
		root.getChildren().clear();
		cellDrawer = new CellsToVisualize();
		//cellDrawer.drawNewGrid(simulation, sceneWidth, sceneHeight);
		int numRows = 20;
		int numCols = 30;
		cellDrawer.drawNewGrid(simulation, new Dimension(400, 400), new Dimension(numRows, numCols));
		//root.getChildren().addAll(cellDrawer.getCellsToVisualize());
		
		for(Cell cell : cellDrawer.getCellsToVisualize()){
			Polygon triangle = new Polygon();
			for(Point2D.Double vertex : cell.getVertices()){
				triangle.getPoints().add(vertex.getX() * (sceneWidth / numRows));
				triangle.getPoints().add(vertex.getY() * (sceneHeight / numCols));
			}
			triangle.setFill(Color.BLACK);
			triangle.setStroke(Color.WHITE);
			root.getChildren().add(triangle);
		}
		/*
		Polygon rect = new Polygon();
		rect.getPoints().add(0.0);
		rect.getPoints().add(0.0);
		rect.getPoints().add(100.0);
		rect.getPoints().add(0.0);
		rect.getPoints().add(100.0);
		rect.getPoints().add(100.0);
		rect.getPoints().add(0.0);
		rect.getPoints().add(100.0);
		root.getChildren().add(rect);
		*/
	}
	/*
	private void drawFreshGrid() {
		cellDrawer = new CellsToVisualize();
		cellDrawer.drawNewGrid(simulation, sceneWidth, sceneHeight);
		for (Cell c : cellDrawer.getCellsToVisualize())
				root.getChildren().add(c);
	}
	*/
	
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
			commandHandler.handleCommand( e, animation, menuCreator);
		});
		
//		menuCreator.simulations().setOnAction((e) -> {
//			simHandler.handleSimulation(menuCreator.simulations().getSelectionModel().getSelectedItem(), e, 
//									   menuCreator, myScene, stg, animation);
//		});
		
		menuCreator.simulations().setOnAction((e) -> {
			handleSimulation(e) ;
		});

	}
	
	protected void update() {
		
		Grid grid = simulation.getGrid();
		grid.prepareNextState();
		grid.update();
		for(Cell ec : cellDrawer.getCellsToVisualize()){
			root.getChildren().remove(ec);
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
