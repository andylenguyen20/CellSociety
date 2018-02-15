package cellsociety_team07.visualization;

import java.awt.geom.Point2D;
import java.util.*;
import cellsociety_team07.config.Simulation;
import cellsociety_team07.simulation.Cell;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * This is my CellsToVisualize class which allows Visualizer to handle all commands relating to cell properties that are visualized on screen,
 * including cell states and properties. In this class, all cells are mapped to a certain point on screen and contain their params
 * 
 * @author Dana Park
 */

public class CellsToVisualize  {
	
	
	private static final int STARTING_X = 140;
	private static final int STARTING_Y = 115;
	protected Map<Paint, Integer> populations;
	
	protected void drawNewGrid(Simulation sim, double width, double height, Group root, double[] props , int state){
		populations =  new HashMap<Paint, Integer>();
		List<Cell> cells = sim.getCells();
		for(Cell cell : cells) {
			cell.getPoints().clear();
			for(Point2D.Double vertex : cell.getVertices()) {
				cell.getPoints().add(vertex.getX() * (width / sim.getGrid().numRows()) +STARTING_X);
				cell.getPoints().add(vertex.getY() * (height / sim.getGrid().numCols())+STARTING_Y);
			}
			initializeCells(cell, props, state);
			createPopulationMap(cell);
			root.getChildren().add(cell);
		}
	}
	
	
	private void initializeCells(Cell cell, double[]props, int state) {
		if(props!=null)cell.setParams(props);
		cell.setFill(cell.getColor());
		cell.setStroke(Color.WHITE);
		cell.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            	@Override
			public void handle(MouseEvent mouseEvent) {
				    		cell.setCurrentState(state);
				    		cell.setFill(cell.getColor());
				    } 
			});
		}
	
	private void createPopulationMap(Cell cell) {
		if (!populations.containsKey(cell.getColor())) {
			populations.put(cell.getColor(), 1);
		}else {
			populations.put(cell.getColor(), populations.get(cell.getColor())+1 );
			}
	}
	
	protected Map<Paint, Integer> getPopulations(){
		return populations;
	}
	
	public void changeParam(Event e, Cell cell, double[] prob) {
         cell.setParams(prob);
	   } 
	}