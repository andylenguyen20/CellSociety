package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;

public class WatorGrid extends Grid implements CellMover{
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
				currCell.update(this);
			}
		}
		super.setCellNeighbors();
	}
	@Override
	public void prepareNextState(){
		Cell[][] grid = super.getCells();
		System.out.println("5,4 current state : " + grid[5][4].getCurrentState());
		
		System.out.println("4,4 current state : " + grid[4][4].getCurrentState());
		
		System.out.println("5,3 current state : " + grid[5][3].getCurrentState());
		
		System.out.println("5,4 next state : " + grid[5][4].getNextState());
		
		System.out.println("4,4 next state : " + grid[4][4].getNextState());
		
		System.out.println("5,3 next state : " + grid[5][3].getNextState());
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				WatorCell currCell = (WatorCell) grid[row][col];
				currCell.applyRules(this);
			}
		}
	}
	
	
	@Override
	public Cell getCellOfType(int desiredState, Cell cell) {
		ArrayList<Cell> potentialCells = new ArrayList<Cell>();
		for(Cell neighbor : cell.getNeighbors()){
			if(neighbor.getCurrentState() == desiredState && neighbor.getNextState() == desiredState){
				potentialCells.add(neighbor);
			}
		}
		return potentialCells.get(0);
		//return potentialCells.get((int) (Math.random() * potentialCells.size()));
	}
	@Override
	public void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeOverwritten) {
<<<<<<< HEAD
		
		System.out.println(movingCell instanceof FishCell);
=======
>>>>>>> 9373fe6960e735b4fe4a95105ba1153aca84fc23
		Cell[][] cells = super.getCells();
		Point toBeOverwrittenCoord = null;
		Point movingCellCoord = null;
		for(int row = 0; row < cells.length; row++){
			for(int col = 0; col < cells[0].length; col++){
				if(cells[row][col] == toBeOverwritten){
					toBeOverwrittenCoord = new Point(row, col);
				}
				if(cells[row][col] == movingCell){
					movingCellCoord = new Point(row, col);
				}
			}
		}
		cells[toBeOverwrittenCoord.x][toBeOverwrittenCoord.y] = movingCell;
		cells[movingCellCoord.x][movingCellCoord.y] = movingCellReplacement;
<<<<<<< HEAD
	
=======
>>>>>>> 9373fe6960e735b4fe4a95105ba1153aca84fc23
	}

}
