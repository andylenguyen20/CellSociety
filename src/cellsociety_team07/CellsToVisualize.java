package cellsociety_team07;

import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Polygon;


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
			
			if (!populations.containsKey(cell.getColor())) {
				populations.put(cell.getColor(), 1);
			}else {
				populations.put(cell.getColor(), populations.get(cell.getColor())+1 );
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
	
	protected Map<Paint, Integer> getPopulations(){
		return populations;
	}
	
	
	
	 public void changeParam(Event e, Cell cell, double[] prob) {
         cell.setProps(prob);
	    } 
	

	

}
