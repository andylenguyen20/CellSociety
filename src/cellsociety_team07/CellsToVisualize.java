package cellsociety_team07;

import java.awt.geom.Point2D;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;


public class CellsToVisualize  {
	private static List <Cell> cellsToVisualize;
	private static final int STARTING_X = 140;
	private static final int STARTING_Y = 115;
	private static int state = 1;

	
	/*
	 
	protected static void drawNewGrid(Simulation sim, double width, double height, Group root, double[] props ){
		for (int i = 0; i < sim.getCells().length; i++) {
			for (int j = 0; j < sim.getCells()[i].length; j++) {
				Cell cell = sim.getCells()[i][j];
				double cellWidth = width / sim.getCells()[0].length;
				double cellHeight = width / sim.getCells().length;
				cell.setWidth(cellWidth);
				cell.setHeight(cellHeight);
				cell.setFill(cell.getColor());
				cell.setStroke(Color.WHITE);
				cell.setX(width / sim.getCells()[0].length * j + STARTING_X);
	            cell.setY(height / sim.getCells().length * i + STARTING_Y);
	            if (props.length<=1) cell.setProps(props);
	            
	            root.getChildren().add(cell);
	            
	            cell.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	            	@Override
				public void handle(MouseEvent mouseEvent) {
					    		cell.setCurrentState(STATE);
					    		cell.setFill(cell.getColor());
					    } 
					});

				}
			}
		}
		*/
	
	protected static void drawNewGrid(Simulation sim, double width, double height, Group root, double[] props ){
		List<Cell> cells = sim.getCells();
		for(Cell cell : cells) {
			cell.getPoints().clear();
			for(Point2D.Double vertex : cell.getVertices()) {
				cell.getPoints().add(vertex.getX() * (width / sim.getGrid().numRows()) +STARTING_X);
				cell.getPoints().add(vertex.getY() * (height / sim.getGrid().numCols())+STARTING_Y);
			
			}
			//cell.setProps(props);
			cell.setFill(cell.getColor());
			cell.setStroke(Color.WHITE);
			root.getChildren().add(cell);
			
			cell.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	            	@Override
				public void handle(MouseEvent mouseEvent) {
					    		cell.setCurrentState(state);
					    		cell.setFill(cell.getColor());
					    } 
					});

		}
		
	}
	public List<Cell> getCellsToVisualize(){
		
		return cellsToVisualize;
	}
	
	 public void changeParam(Event e, Cell cell, double[] prob) {
         cell.setProps(prob);
	    } 
	

	

}
