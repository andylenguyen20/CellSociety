package cellsociety_team07;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class CellsToVisualize extends Visualizer {
	protected  Cell cell;
	private ArrayList <Cell> cellsToVisualize;
	
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
			    cell.setX(width / sim.getCells()[0].length * j + 45);
                cell.setY(height / sim.getCells().length * i + 55);
			    cellsToVisualize.add(cell);
			    
			}
		}
	}
	
	public ArrayList<Cell> getCellsToVisualize(){
		return cellsToVisualize;
	}
	

	public Cell getCell() {
		return cell;
	}
	

}
