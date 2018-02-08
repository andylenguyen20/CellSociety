package cellsociety_team07;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class CellsToVisualize  {
	protected  Cell cell;
	private List <Cell> cellsToVisualize;
	private static final int STARTING_X = 135;
	private static final int STARTING_Y = 115;

	
	
	public void drawNewGrid(Simulation sim, double width, double height ) {
		cellsToVisualize = new ArrayList<Cell>();
		for (int i = 0; i < sim.getCells().length; i++) {
			for (int j = 0; j < sim.getCells()[i].length; j++) {
				cell = sim.getCells()[i][j];
				double cellWidth = width / sim.getCells()[0].length;
				double cellHeight = width / sim.getCells().length;
				cell.setWidth(cellWidth);
				cell.setHeight(cellHeight);
				cell.setFill(cell.getColor());
				cell.setStroke(Color.WHITE);
			    cell.setX(width / sim.getCells()[0].length * j + STARTING_X);
                cell.setY(height / sim.getCells().length * i + STARTING_Y);
			    cellsToVisualize.add(cell);
			    
			}
		}
	}
	
	public List<Cell> getCellsToVisualize(){
		return cellsToVisualize;
	}
	

	public Cell getCell() {
		return cell;
	}
	

}
