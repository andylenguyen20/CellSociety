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
		System.out.print("howdy ");
		System.out.println(movingCell instanceof FishCell);
		Cell[][] cells = super.getCells();
		Point toBeOverwrittenCoord = null;
		Point movingCellCoord = null;
		for(int row = 0; row < cells.length; row++){
			for(int col = 0; col < cells[0].length; col++){
				if(cells[row][col] == toBeOverwritten){
					toBeOverwrittenCoord = new Point(row, col);
				}
				if(cells[row][col] == movingCell){
					System.out.println("movingCellCoord " + row + "," + col);
					movingCellCoord = new Point(row, col);
				}
			}
		}
		cells[toBeOverwrittenCoord.x][toBeOverwrittenCoord.y] = movingCell;
		cells[movingCellCoord.x][movingCellCoord.y] = movingCellReplacement;
		System.out.println("toBeOverwrittenCoord " + toBeOverwrittenCoord.x + "," + toBeOverwrittenCoord.y);
		System.out.println(cells[toBeOverwrittenCoord.x][toBeOverwrittenCoord.y].getCurrentState());
		System.out.println(cells[9][5].getCurrentState());
		System.out.println(movingCell.getCurrentState());
		System.out.println("finishedmoving");
		System.out.println("moveSpot " + cells[9][5].getCurrentState());
		System.out.println("originalSpot " + cells[10][5].getCurrentState());
		/*
		for(int row = 0; row < cells.length; row++){
			for(int col = 0; col < cells[0].length; col++){
				if(cells[row][col] == toBeOverwritten){
					System.out.println("row overwritten : " + row);
					System.out.println("col overwritten : " + col);
					cells[row][col] = movingCell;
					System.out.println("attemptedMoving " + cells[row][col].getCurrentState());
					//System.out.println(cells[row][col] == null);
					debug = true;
				}
				if(cells[row][col] == movingCell){
					if(debug){
						System.out.println("finishedmoving1 " + cells[9][5].getCurrentState());
					}
					System.out.println("row movingoverwritten : " + row);
					System.out.println("col movingoverwritten : " + col);
					cells[row][col] = movingCellReplacement;
					//System.out.println(cells[row][col] == null);
				}
			}
		}
		*/
		//System.out.println("finishedmoving" + cells[9][5].getCurrentState());
	}

}
