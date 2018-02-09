package cellsociety_team07;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class SegregationGrid extends Grid implements CellMover{
	public SegregationGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		super.setNeighborFinder(new NeighborFinder(NeighborFinder.TOUCHING_NEIGHBORS));
	}
	@Override
	public void prepareNextState(){
		//ArrayList<Cell> grid = super.getCells();
		/*
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid.length; col++){
				SegregationCell currCell = (SegregationCell) grid[row][col];
				currCell.applyRules(this);
			}
		}
		*/
	}
	
	@Override
	public Cell getCellOfType(int emptyState, Cell cell) {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		/*
		Cell[][] grid = super.getCells();
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				Cell currCell = grid[row][col];
				if(currCell.getCurrentState() == emptyState && currCell.getNextState() == emptyState){
					emptyCells.add(currCell);
				}
			}
		}
		*/
		return emptyCells.get((int) (Math.random() * emptyCells.size()));
	}
	@Override
	public void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeOverwritten) {
		// TODO Auto-generated method stub
		
	}
}
