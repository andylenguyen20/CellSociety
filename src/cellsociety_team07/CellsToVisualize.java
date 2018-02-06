package cellsociety_team07;

import javafx.scene.paint.Color;

public class CellsToVisualize extends Visualizer {
	protected double sceneWidth = 400;
	protected double sceneHeight = 400;
	protected  Cell cell;
	
	public void visualizeCell(Cell c, Simulation sim) {
			cell = c;
			double cellWidth = sceneWidth / sim.getCells()[0].length;
			double cellHeight = sceneHeight / sim.getCells().length;
			cell.setWidth(cellWidth);
			cell.setHeight(cellHeight);
			cell.setFill(cell.getColor());
			cell.setStroke(Color.WHITE);
		}
		
	public Cell getCell() {
		return cell;
	}
	

}
