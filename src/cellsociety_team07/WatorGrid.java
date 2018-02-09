package cellsociety_team07;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class WatorGrid extends Grid implements CellMover{
	public WatorGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new UnboundedNeighborFinder(UnboundedNeighborFinder.ADJACENT_NEIGHBORS, gridDimensions));
	}
	@Override
	public void update(){
		/*
		Cell[][] grid = super.getCells();
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				WatorCell currCell = (WatorCell) grid[row][col];
				currCell.update(this);
			}
		}
		super.setCellNeighbors();
		*/
	}
	@Override
	public void prepareNextState(){
		/*
		Cell[][] grid = super.getCells();
		
		for(int row = 0; row < grid.length; row++){
			for(int col = 0; col < grid[0].length; col++){
				WatorCell currCell = (WatorCell) grid[row][col];
				currCell.applyRules(this);
			}
		}
		*/
	}
	
	
	@Override
	public Cell getCellOfType(int desiredState, Cell cell) {
		ArrayList<Cell> potentialCells = new ArrayList<Cell>();
		for(Cell neighbor : cell.getNeighbors()){
			if(neighbor.getCurrentState() == desiredState && neighbor.getNextState() == desiredState){
				potentialCells.add(neighbor);
			}
		}
		return potentialCells.get((int) ( Math.random() *potentialCells.size()));
		
	}
	@Override
	public void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeOverwritten) {
		/*
		Cell[][] cells = super.getCells();
		Point toBeOverwrittenCoord;
		Point movingCellCoord=null;
		for(int row = 0; row < cells.length; row++){
			for(int col = 0; col < cells[0].length; col++){
				if(cells[row][col] == toBeOverwritten){
					toBeOverwrittenCoord = new Point(row, col);
					cells[toBeOverwrittenCoord.x][toBeOverwrittenCoord.y] = movingCell;
				}
				if(cells[row][col] == movingCell){
					movingCellCoord = new Point(row, col);
					
					

				}
			}
		}
		
		
		
		//cells[toBeOverwrittenCoord.x][toBeOverwrittenCoord.y] = movingCell;
		cells[movingCellCoord.x][movingCellCoord.y] = movingCellReplacement;
	*/
	}

}
