package cellsociety_team07.visualization;
import cellsociety_team07.config.BadSimulationException;
import cellsociety_team07.config.Simulation;
import cellsociety_team07.config.XMLWriterFactory;
import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.Grid;
import java.util.*;
import java.util.concurrent.*;
import javafx.scene.*;
import javafx.animation.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.Duration;
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
	private static final int SCENE_XY = 500;
	private static final int SCREEN_XY = 800;
	private static final double RATE = 1.0;
	private static final String DEFAULT_RESOURCE_PACKAGE = "resources/";
	private static final int MAXIMUM_POINTS = 20;
	private Timeline animation;
	private Simulation simulation;
	private Stage stg;
	private Scene myScene;
	private Group root;
	private double [] props;
	private int propsLength;
	private TextFieldCreator propsChanger;
	private ResourceBundle myResources_C = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "CommandsBar");
	private ResourceBundle myResources_S =ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "SimulationBar");
	private int cellState=0;
	private TextFieldCreator stateChanger;
	private String currentSimType;
	private String nextSimType;
	private MenuCreator menuCreator;
	private CellsToVisualize cellDrawer;
	private GraphCreator graphCreator;
	private int xData = 0;
	private ExecutorService chartExecutor;
    private ConcurrentLinkedQueue<Number> dataQueue1 = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Number> dataQueue2 = new ConcurrentLinkedQueue<>();
	private ConcurrentLinkedQueue<Number> dataQueue3 = new ConcurrentLinkedQueue<>();
	/**
	 *  The start method allows the program to start and run by calling appropriate methods to set up and update the screen.
	 * @author Dana Park
	 */
	@Override
	public void start(Stage stage) {
		stg = stage;
		stg.setTitle(myResources_S.getString("ScreenTitle"));
		simulation = new Simulation("xml/wator_simulation.xml");
		currentSimType = simulation.getType();
		getInitialProp();
		myScene = setUpGame(SCREEN_XY, SCREEN_XY, "xml/wator_simulation.xml" );
		stg.setScene(myScene);
		stg.show();
		setUpLineChartExecutor();
		setAnimation(stg);
	}
	private void setUpLineChartExecutor() {
		chartExecutor = Executors.newCachedThreadPool(new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r);
                thread.setDaemon(true);
                return thread;
            }
        });
        AddPointsToQueue addToQueue = new AddPointsToQueue();
        chartExecutor .execute(addToQueue);
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
	private BorderPane setUpBorderPane() {
		return BorderPaneInitializer.setUpBorderPane(menuCreator.addHBox(myResources_C, myResources_S), propsChanger.textHBoxMaker(myResources_C, "EnterPropChangeCommand", "SubmitCommand"), 
													stateChanger.textHBoxMaker(myResources_C, "EnterStateChangeCommand", "EnterCommand"), graphCreator.getLineChart(0, simulation.getCells().size() )); }
	private Scene setUpGame(int height, int background, String sim) {
		initializeHelpers();
		Scene scene = new Scene(root, height, background);
		simulation = new Simulation(sim);
		currentSimType = nextSimType;
		drawFreshGrid();
		root.getChildren().add(setUpBorderPane());
		return scene;
	}
	private class AddPointsToQueue implements Runnable {
		public void run() {
			Map<Paint, Integer> populations = cellDrawer.getPopulations();
			try {
				DataPlotter.plotPoints(populations, dataQueue1, dataQueue2, dataQueue3, chartExecutor, simulation.getCells().size());
				Thread.sleep(100);
				chartExecutor.execute(this);
		   }catch (Exception ex) {
				throw new BadSimulationException("Data plotter failed to plot points");
			}
		}
	}
	private void drawFreshGrid() {
		if(currentSimType != nextSimType) {
			cellState = 0;
			props = null;
		}
		cellDrawer.drawNewGrid(simulation, SCENE_XY, SCENE_XY,root, props, cellState);
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
	    handleUserInput();
	}
	public void handleUserInput() {
		propsChanger.getButton().setOnAction((e) -> {
			handleParamChanges(e); });
		stateChanger.getButton().setOnAction((e) -> {
			cellState = Integer.parseInt(stateChanger.getTextValue().getText()); });
		menuCreator.getRandomBox().getButton().setOnAction((e) -> {
			System.out.println("hello"); 
			handleRandomGeneration(e) ; });
		menuCreator.stepButton().setOnAction((e) -> {
			handleStepForward(menuCreator.getResources(myResources_C, "StepForwardCommand"));	});
		menuCreator.saveStateButton().setOnAction((e) -> {
			simulation.saveCurrentState();	});
		menuCreator.commands().setOnAction((e) -> {
			CommandHandler.handleCommand( e, animation, menuCreator); });
		menuCreator.simulations().setOnAction((e) -> {
			handleSimulation(e) ; });
		 }
	private void update() {
		Grid grid = simulation.getGrid();
		grid.prepareNextState();
		grid.update();
		for(Cell cell : grid.getCells()) 
			root.getChildren().remove(cell);
		drawFreshGrid();
	  }
	private void updateLineGraph() {
		for (int i = 0; i < MAXIMUM_POINTS; i++) { 
	            if (dataQueue1.isEmpty()) break;
	            graphCreator.getSeries1().getData().add(new XYChart.Data<>(xData++, dataQueue1.remove()));
	            if(dataQueue2.isEmpty()) break;
	            graphCreator.getSeries2().getData().add(new XYChart.Data<>(xData++, dataQueue2.remove()));
	            if(dataQueue3.isEmpty()) break;
	            graphCreator.getSeries3().getData().add(new XYChart.Data<>(xData++, dataQueue3.remove()));
	        }
			DataPlotter.addNewPoint(graphCreator.getSeries1(), MAXIMUM_POINTS);
			DataPlotter.addNewPoint(graphCreator.getSeries2(), MAXIMUM_POINTS);
			DataPlotter.addNewPoint(graphCreator.getSeries3(), MAXIMUM_POINTS);
			graphCreator.getXAxis().setLowerBound(xData - MAXIMUM_POINTS);
	        graphCreator.getXAxis().setUpperBound(xData - 1);
	}
	private void handleParamChanges(Event e) {
		props = new double[propsLength];
		String str = propsChanger.getTextValue().getText();
		String [] arrOfStr = str.split(":", 2);
		int i =	Integer.parseInt(arrOfStr[0]) ;
		double d = Double.parseDouble(arrOfStr[1]);
		props[i] =  d;
	}
	private void handleRandomGeneration(Event e) {
		String str = menuCreator.getRandomBox().getTextValue().getText();
		String [] arrOfStr = str.split(":", 4);
		if (arrOfStr.length!=4) return;
		int height =	Integer.parseInt(arrOfStr[0]) ;
		int width = Integer.parseInt(arrOfStr[1]);
		String simType = arrOfStr[2];
		String shape = arrOfStr[3];
		XMLWriterFactory.writeRandomSimData(height, width, simType, shape);
		newSim("xml/random.xml");
		nextSimType = simType;
	}
	private void handleSimulation(Event e) {
		String selectedAction = menuCreator.simulations().getSelectionModel().getSelectedItem();
		nextSimType = selectedAction;
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
		dataQueue1 = new ConcurrentLinkedQueue<>();
		dataQueue2 = new ConcurrentLinkedQueue<>();
		dataQueue3 = new ConcurrentLinkedQueue<>();
		myScene = setUpGame(SCREEN_XY, SCREEN_XY, sim);
		stg.setScene(myScene);
		stg.show();
		CommandHandler.defaultRateAndPlay(RATE, animation);
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
	public static void main(String[] args) {
		launch(args);
	}
}
