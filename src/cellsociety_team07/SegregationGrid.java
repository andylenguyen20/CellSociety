package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;

public class SegregationGrid extends Grid implements CellMover{
	public static final Point[] IGNORE = new Point[]{new Point(0,0)};
	public SegregationGrid(int width, int height){
		super(width, height);
		super.setNeighborFinder(new NeighborFinder(IGNORE));
	}
	@Override
	public void prepareNextState(){
		Cell[][] grid = super.getCells();
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid.length; col++){
				SegregationCell currCell = (SegregationCell) grid[row][col];
				currCell.applyRules(this);
			}
		}
	}
	
	@Override
	public Cell getRandomEmptyCell(int emptyState) {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		Cell[][] grid = super.getCells();
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				Cell currCell = grid[row][col];
				if(currCell.getCurrentState() == emptyState && currCell.getNextState() == emptyState){
					emptyCells.add(currCell);
				}
			}
		}
		return emptyCells.get((int) (Math.random() * emptyCells.size()));
	}
}
