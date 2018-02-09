package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class UnboundedNeighborFinder extends NeighborFinder {

	public UnboundedNeighborFinder(Point[] neighborOffsets) {
		super(neighborOffsets);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Cell> getCellNeighborhood(Cell[][] cells, int row, int col) {

		List<Cell> neighborList = super.getCellNeighborhood(cells, row, col);

		if (col == 0)
			neighborList.add(cells[row][cells[0].length - 1]);

		if (col == cells[0].length - 1)
			neighborList.add(cells[row][0]);

		if (row == 0)
			neighborList.add(cells[cells.length - 1][col]);

		if (row == cells.length - 1)
			neighborList.add(cells[0][col]);

		return neighborList;

	}

}
