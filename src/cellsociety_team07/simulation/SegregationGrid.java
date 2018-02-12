package cellsociety_team07.simulation;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class SegregationGrid extends Grid implements CellFetcher{
	public SegregationGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		super.setNeighborFinder(new NeighborFinder(NeighborFinder.TOUCHING_NEIGHBORS));
	}
	@Override
	public void prepareNextState(){
		List<Cell> grid = super.getCells();
		for(Cell cell : grid) {
			SegregationCell currCell = (SegregationCell) cell;
			currCell.applyRules(this);
		}
	}
	
	@Override
	public Cell getCellOfType(int emptyState, Cell cell) {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		List<Cell> grid = super.getCells();
		for(Cell currCell : grid) {
			if(currCell.getCurrentState() == emptyState && currCell.getNextState() == emptyState){
				emptyCells.add(currCell);
			}
		}
		return emptyCells.get((int) (Math.random() * emptyCells.size()));
	}
	
}
