package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;

public class UnboundedNeighborFinder extends NeighborFinder {

	public UnboundedNeighborFinder(Point[] neighborOffsets) {
		super(neighborOffsets);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Cell> getCellNeighborhood(Cell[][] cells, int row, int col) {

		ArrayList<Cell> neighborList = super.getCellNeighborhood(cells, row, col);

		// if you are in first column, then your neighbor is on the other side of the the grid but same row
		if (col == 0){
			neighborList.add(cells[row][cells[0].length - 1]);
		}

		// if you are in last column, neighbor is on the other side, but same row
		if (col == cells[0].length - 1){
			neighborList.add(cells[row][0]);
		}

		// if you are in first row, neighbor is on other side of grid in same column
		if (row == 0){
			neighborList.add(cells[cells.length - 1][col]);
		}

		// if you are in last row, neighbor is on other side of grid in same column
		if (row == cells.length - 1){
			neighborList.add(cells[0][col]);
		}

		return neighborList;

	}

}
