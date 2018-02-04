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
		super.prepareNextState();
		/*
		SegregationCell[][] segregationGrid = (SegregationCell[][]) super.getCells();
		for(int row = 0; row < segregationGrid.length; row++){
			for(int col = 0; col < segregationGrid.length; col++){
				SegregationCell currCell = segregationGrid[row][col];
				if(currCell.wantsToMove()){
					currCell.move(this);
				}
			}
		}*/
	}
	private void moveCell(SegregationCell[][] segregationGrid, Cell currentCell){
		for(int row = 0; row < segregationGrid.length; row++){
			for(int col = 0; col < segregationGrid[0].length; col++){
				if(segregationGrid[row][col].isEmpty()){
					segregationGrid[row][col].setNextState(currentCell.getCurrentState());
					currentCell.setNextState(0);
				}
			}
		}
	}
	public void moveCell(int currState){
		
	}
	@Override
	public ArrayList<Point> getEmptyLocations(int emptyState) {
		ArrayList<Point> emptyLocations = new ArrayList<Point>();
		Cell[][] grid = super.getCells();
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid.length; col++){
				Cell currCell = grid[row][col];
				if(currCell.getCurrentState() == emptyState){
					emptyLocations.add(new Point(row, col));
				}
			}
		}
		return emptyLocations;
	}
	
	@Override
	public void switchCellStates(Cell movingCell, Point moveLocation, int replacementState) {
		
	}

}
