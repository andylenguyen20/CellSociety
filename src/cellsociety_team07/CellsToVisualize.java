package cellsociety_team07;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.scene.paint.Color;

public class CellsToVisualize extends Visualizer {
	protected  ExperimentalCell cell;
	private List<Cell> cellsToVisualize;
	private GridFactory gridCreator;
	
//	public void drawNewGrid(Simulation sim, double width, double height ) {
//		cellsToVisualize = new ArrayList<Cell>();
//		for (int i = 0; i < sim.getCells().length; i++) {
//			for (int j = 0; j < sim.getCells()[i].length; j++) {
//				cell = sim.getCells()[i][j];
//				double cellWidth = width / sim.getCells()[0].length;
//				double cellHeight = width / sim.getCells().length;
//				cell.setWidth(cellWidth);
//				cell.setHeight(cellHeight);
//				cell.setFill(cell.getColor());
//				cell.setStroke(Color.WHITE);
//			    cell.setX(width / sim.getCells()[0].length * j + 45);
//                cell.setY(height / sim.getCells().length * i + 55);
//			    cellsToVisualize.add(cell);
//			    
//			}
//		}
//	}

	public void drawNewGrid(Simulation sim, Dimension screenDim, Dimension gridDim){
		Grid grid = GridFactory.generateGrid("Triangle", gridDim, "GameOfLife");
		cellsToVisualize = grid.getCells();
		Map<String, List<Cell>> map = MapFactory.generateVertexMap(cellsToVisualize);
		
		/*
		List<ExperimentalCell> myCells = GridCreator.getTriangleGrid(screenDim, gridDim);
		for (int i = 0; i < myCells.size(); i++) {
				ExperimentalCell exCell = myCells.get(i);
				exCell.setStroke(Color.WHITE);
			    cellsToVisualize.add(exCell);
			}
		}
		*/
	}
	
	public List<Cell> getCellsToVisualize(){
		return cellsToVisualize;
	}
	/*
	public List<Cell> getCellsToVisualize(){
		return cellsToVisualize;
	}
	*/
	
/*
	public Cell getCell() {
		return cell;
	}
	*/
	

}
