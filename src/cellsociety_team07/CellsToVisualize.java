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
	
	private static final int STARTING_X = 140;
	private static final int STARTING_Y = 115;

	protected static void drawNewGrid(Simulation sim, double width, double height, Group root, double[] props , int state){
		List<Cell> cells = sim.getCells();
		for(Cell cell : cells) {
			for(Point2D.Double vertex : cell.getVertices()) {
				cell.getPoints().add(vertex.getX() * (width / sim.getGrid().numRows()) +STARTING_X);
				cell.getPoints().add(vertex.getY() * (height / sim.getGrid().numCols())+STARTING_Y);
			}
			cell.setProps(props);
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
	
	 public void changeParam(Event e, Cell cell, double[] prob) {
         cell.setProps(prob);
	    } 
	

	

}
