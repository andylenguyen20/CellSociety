package cellsociety_team07;

import java.awt.Point;
public class WatorGrid extends Grid{
	public static final Point[] NEIGHBOR_OFFSETS = new Point[]{new Point(-1,0), new Point(1,0), new Point(0,-1), new Point(0,1)};
	
	public WatorGrid(int width, int height){
		super(width, height);
		setNeighborFinder(new UnboundedNeighborFinder(NEIGHBOR_OFFSETS));
	}
	@Override
	public void update(){
		Cell[][] grid = super.getCells();
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				WatorCell currCell = (WatorCell) grid[row][col];
				WatorCell nextCell = currCell.getNextCellState();
				nextCell.update();
				grid[row][col] = nextCell;
			}
		}
		super.setCellNeighbors();
	}
	
	@Override
	public void prepareNextState(){
		Cell[][] grid = super.getCells();
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				if(grid[row][col] instanceof SharkCell){
					grid[row][col].applyRules();
				}
			}
		}
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				if(grid[row][col] instanceof FishCell){
					grid[row][col].applyRules();
				}
			}
		}
	}
}
