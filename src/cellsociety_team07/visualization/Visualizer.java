package cellsociety_team07.visualization;

import cellsociety_team07.config.Simulation;
import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.Grid;
import java.util.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Paint;

/**
 * This Visualizer class is responsible for the constant visualization and maintenance of all aspects that are on screen, including
 * drop down menus, buttons,textfields, the grid with live action cells, and a live-action graph at the bottom of the screen.
 * This class runs the program and updates aspects of the program as necessary.
 * @author Dana Park
 */


public class Visualizer extends Application {
	
	private static final int MY_SPEED = 5;
	private static final int MILLISECOND_DELAY = 1000 / MY_SPEED;
	private static final int SCENE_WIDTH = 500;
	private static final int SCENE_HEIGHT = 500;
	private static final int SCREEN_WIDTH = 800;
	private static final int SCREEN_HEIGHT = 800;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private Timeline animation;
	private Simulation simulation;
	private Stage stg;
	private Scene myScene;
	private Group root;
	private double [] props;
	private ResourceBundle myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
	private ResourceBundle myResources_S =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
	private GraphCreator graphCreator;
	private int propsLength;
	private int cellState= 1;
	private TextFieldCreator stateChanger;
	private TextFieldCreator propsChanger;
	private MenuCreator menuCreator;
	private CellsToVisualize cellDrawer;
	private static final int MAX_DATA_POINTS = 20;
	private int xSeriesData = 0;
	private ExecutorService executor;
    private ConcurrentLinkedQueue<Number> dataQ1 = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Number> dataQ2 = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Number> dataQ3 = new ConcurrentLinkedQueue<>();
	
	
	/**
	 *  The start method allows the program to start and run by calling appropriate methods to set up and update the screen.
	 * @author Dana Park
	 */
	@Override
	public void start(Stage stage) {
		stg = stage;
		stg.setTitle("CA Simulation");
		simulation = new Simulation("xml/wator_simulation.xml");
		getInitialProp();
		myScene = setUpGame(SCREEN_WIDTH, SCREEN_HEIGHT, "xml/wator_simulation.xml" );
		stg.setScene(myScene);
		stg.show();
		setUpGraphExecutor();
		setAnimation(stg);
	}
	
	private void setUpGraphExecutor() {
		executor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });

        AddToQueue addToQueue = new AddToQueue();
        executor .execute(addToQueue);
	}
	
	private void setAnimation(Stage s) {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> step(MY_SPEED));
		animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
		
	}
	
	private void initializeHelpers() {
		root = new Group();
		stateChanger = new TextFieldCreator(myResources_C, "EnterStateChangeCommand", "EnterCommand");
		propsChanger = new TextFieldCreator(myResources_C, "EnterPropChangeCommand", "SubmitCommand");
		menuCreator = new MenuCreator();
		cellDrawer= new CellsToVisualize();
		graphCreator = new GraphCreator();
	}
	
	private void setUpBorderPane() {
		BorderPane borderPane = new BorderPane();
		borderPane.setPrefSize(800, 800); 
		borderPane.setTop(menuCreator.addHBox(myResources_C, myResources_S));	    
		borderPane.setRight(propsChanger.propsHBoxMaker(myResources_C, "EnterPropChangeCommand", "SubmitCommand"));
		borderPane.setLeft(stateChanger.propsHBoxMaker(myResources_C, "EnterStateChangeCommand", "EnterCommand"));
		borderPane.setBottom(graphCreator.getLineChart());
		borderPane.setStyle("-fx-padding: 10;" +"-fx-border-style: solid inside;" + "-fx-border-width: 2;" + 
						"-fx-border-insets: 5;" +"-fx-border-radius: 5;" + "-fx-border-color: blue;");
		root.getChildren().add(borderPane);
		
	}

	private Scene setUpGame(int height, int background, String sim) {
		initializeHelpers();
		Scene scene = new Scene(root, height, background);
		setSimulation(sim);
		drawFreshGrid();
		setUpBorderPane();
		return scene;
	}
	
	private class AddToQueue implements Runnable {
		public void run() {
			Map<Paint, Integer> populations = cellDrawer.getPopulations();
			try {
				DataPlotter.plotPoints(populations, dataQ1, dataQ2, dataQ3, executor);
				Thread.sleep(100);
				executor.execute(this);
		   }catch (InterruptedException ex) {
					System.out.println("Error! Interrupted Exception");
				}
			}
		}
	
	private void drawFreshGrid() {
		cellDrawer.drawNewGrid(simulation, SCENE_WIDTH, SCENE_HEIGHT,root, props, cellState);
	}
	
	private void getInitialProp() {
		List<Cell> cells = simulation.getCells();
		for(Cell cell : cells) {
			propsLength = cell.getProps().length;
			props = new double [propsLength]; 
			props = cell.getProps();
			}
		}
	
	private void step(double elapsedTime) {
		update();
		updateLineGraph();
	    propsChanger.getSubmit().setOnAction((e) -> {
	   	    		handleParamChanges(e); });
	    stateChanger.getSubmit().setOnAction((e) -> {
    			cellState = Integer.parseInt(stateChanger.getTextValue().getText()); });
		menuCreator.stepButton().setOnAction((e) -> {
			handleStepForward(menuCreator.getResources(myResources_C, "StepForwardCommand"));	});
		menuCreator.commands().setOnAction((e) -> {
			CommandHandler.handleCommand( e, animation, menuCreator); });
		menuCreator.simulations().setOnAction((e) -> {
			handleSimulation(e) ; });
	}

	private void update() {
		Grid grid = simulation.getGrid();
		grid.prepareNextState();
		grid.update();
		for(Cell cell : grid.getCells()) {
			root.getChildren().remove(cell);
		}
		drawFreshGrid();
	  }
	
	private void updateLineGraph() {
		for (int i = 0; i < 25; i++) { 
	            if (dataQ1.isEmpty()) break;
	            graphCreator.getSeries1().getData().add(new XYChart.Data<>(xSeriesData++, dataQ1.remove()));
	            if(dataQ2.isEmpty()) break;
	            graphCreator.getSeries2().getData().add(new XYChart.Data<>(xSeriesData++, dataQ2.remove()));
	            if(dataQ3.isEmpty()) break;
	            graphCreator.getSeries3().getData().add(new XYChart.Data<>(xSeriesData++, dataQ3.remove()));
	        }
			addNewPoint(graphCreator.getSeries1(), MAX_DATA_POINTS);
			addNewPoint(graphCreator.getSeries2(), MAX_DATA_POINTS);
			addNewPoint(graphCreator.getSeries3(), MAX_DATA_POINTS);
			graphCreator.getXAxis().setLowerBound(xSeriesData - MAX_DATA_POINTS);
	        graphCreator.getXAxis().setUpperBound(xSeriesData - 1);
	}
	
	private void addNewPoint(XYChart.Series<Number, Number> series, int maxData) {
		if (series.getData().size() > maxData) {
    			series.getData().remove(0, graphCreator.getSeries3().getData().size() - maxData);
		}
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
		if (selectedAction.equals("Game of Life")) 
			newSim("xml/gol_simulation.xml");
		if (selectedAction.equals("Segregation")) 
			newSim("xml/segregation_simulation.xml");
		if (selectedAction.equals("Predator/Prey")) 
			newSim("xml/wator_simulation.xml");
		if (selectedAction.equals("Fire")) 
			newSim("xml/fire_simulation.xml");
		}
	
	private void newSim(String sim) {
		dataQ1 = new ConcurrentLinkedQueue<>();
		dataQ2 = new ConcurrentLinkedQueue<>();
		dataQ3 = new ConcurrentLinkedQueue<>();
		myScene = setUpGame(SCREEN_WIDTH, SCREEN_HEIGHT, sim);
		stg.setScene(myScene);
		stg.show();
		CommandHandler.defaultRateAndPlay(1.0, animation);
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
	
	public static void main(String[] args) {
		launch(args);
	}
 }
