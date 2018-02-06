package cellsociety_team07;

import javafx.scene.paint.Color;

public class CellsToVisualize extends Visualizer {
	protected  Cell cell;
	
	public void visualizeCell(Cell c, Simulation sim, double width, double height) {
			cell = c;
			double cellWidth = width / sim.getCells()[0].length;
			double cellHeight = width / sim.getCells().length;
			c.setWidth(cellWidth);
			c.setHeight(cellHeight);
			c.setFill(c.getColor());
			c.setStroke(Color.WHITE);
		}
		
	public Cell getCell() {
		return cell;
	}
	

}
