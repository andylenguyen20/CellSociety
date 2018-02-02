package cellsociety_team07;

import java.util.ArrayList;
import java.util.Arrays;

public class GameOfLifeGrid extends Grid{

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
			return this.getEdgeCellNeighbors();
		}
		ArrayList<Cell> neighbors = (ArrayList<Cell>) Arrays.asList(cells[row - 1][col], cells[row + 1][col],
				cells[row][col - 1], cells[row][col + 1], cells[row - 1][col - 1], cells[row + 1][col + 1],
				cells[row + 1][col - 1], cells[row - 1][col + 1]);
		return neighbors;
	}
	private ArrayList<Cell> getEdgeCellNeighbors(){
		
	}

	
}
