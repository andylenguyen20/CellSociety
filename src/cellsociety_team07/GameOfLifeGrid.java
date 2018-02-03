package cellsociety_team07;

import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLifeGrid extends Grid{

	public GameOfLifeGrid(int width, int height){
		super(width, height);
	}
	@Override
	public void setCellNeighbors() {
		Cell[][] cells = super.getCells();
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells.length; j++){
				cells[i][j].setNeighbors(getCellNeighborhood(cells, i, j));
			}
		}
	}
	
	private ArrayList<Cell> getCellNeighborhood(Cell[][] cells, int row, int col){
		if(row == 0 || col == 0 || row == cells.length || col == cells[0].length){
			return this.getEdgeCellNeighbors(cells, row, col);
		}
		ArrayList<Cell> neighbors = (ArrayList<Cell>) Arrays.asList(cells[row - 1][col], cells[row + 1][col],
				cells[row][col - 1], cells[row][col + 1], cells[row - 1][col - 1], cells[row + 1][col + 1],
				cells[row + 1][col - 1], cells[row - 1][col + 1]);
		return neighbors;
	}
	private ArrayList<Cell> getEdgeCellNeighbors(Cell[][] cells, int row, int col){
		if(getCornerCellNeighbors(cells, row, col) == null){
			return getSideCellNeighbors(cells, row, col);
		}else{
			return getCornerCellNeighbors(cells, row, col);
		}
	}
	private ArrayList<Cell> getSideCellNeighbors(Cell[][] cells, int row, int col){
		if(row == 0){
			return (ArrayList<Cell>) Arrays.asList(cells[0][col - 1], cells[0][col + 1], cells[1][col - 1],
					cells[1][col], cells[1][col+1]);
		}
		if(col == cells.length - 1){
			return (ArrayList<Cell>) Arrays.asList(cells[row][cells.length - 1], cells[row][cells.length + 1], cells[row - 1][cells.length - 1],
					cells[row - 1][cells.length], cells[row - 1][cells.length + 1]);
		}
		if(row == cells[0].length - 1){
			return (ArrayList<Cell>) Arrays.asList(cells[cells[0].length - 1][col - 1], cells[cells[0].length - 1][col + 1], cells[cells[0].length - 2][col - 1],
					cells[cells[0].length - 2][col], cells[cells[0].length - 2][col - 1]);
		}
		if(col == 0){
			return (ArrayList<Cell>) Arrays.asList(cells[row - 1][col], cells[row + 1][col], cells[row - 1][col + 1],
					cells[row][col + 1], cells[row + 1][col + 1]);
		}
		return null;
	}
	private ArrayList<Cell> getCornerCellNeighbors(Cell[][] cells, int row, int col){
		if(row == 0 && col == 0){
			return (ArrayList<Cell>) Arrays.asList(cells[0][1], cells[1][0], cells[1][1]);
		}
		if(row == 0 && col == cells.length){
			return (ArrayList<Cell>) Arrays.asList(cells[0][cells.length - 2], cells[1][cells.length - 1], cells[1][cells.length - 2]);
		}
		if(row == cells[0].length && col == cells.length){
			return (ArrayList<Cell>) Arrays.asList(cells[cells[0].length-1][cells.length - 2], cells[cells[0].length-2][cells.length - 1], cells[cells[0].length - 2][cells.length - 2]);
		}
		if(row == cells[0].length && col == 0){
			return (ArrayList<Cell>) Arrays.asList(cells[cells[0].length-2][0], cells[cells[0].length-1][1], cells[cells[0].length - 2][1]);
		}
		return null;
	}

	
}
