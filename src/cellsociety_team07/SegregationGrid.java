package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;

public class SegregationGrid extends Grid implements CellMover{
	public static final Point[] NEIGHBOR_OFFSETS = new Point[]{new Point(-1,-1), new Point(-1,0),new Point(-1,1), new Point(0,-1), 
																new Point(0,1), new Point(1,-1), new Point(1,0), new Point(1,1)};
	public SegregationGrid(int width, int height){
		super(width, height);
		super.setNeighborFinder(new NeighborFinder(NEIGHBOR_OFFSETS));
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
	public Cell getRandomEmptyCell(int emptyState, Cell cell) {
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
	@Override
	public void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeOverwritten) {
		// TODO Auto-generated method stub
		
	}
}
