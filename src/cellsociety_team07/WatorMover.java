package cellsociety_team07;

import java.util.ArrayList;

public class WatorMover implements CellMover {

	@Override
	/**
	 * Swaps data in moving Cell and toBeOverwritten
	 */
	public void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeOverwritten) {
		toBeOverwritten = movingCell;
		movingCell = movingCellReplacement;
	}

	@Override
	/**
	 * Searches for random neighbor cell of specified state and returns it
	 * 
	 * @param emptyState: state of neighbor cell to search for
	 * @param cell: the parent cell. Look in this cell's ArrayList of neighboring cells
	 * @return random cell of corresponding state; null if no eligible neighbors found
	 */
	public Cell getCellOfType(int emptyState, Cell cell) {
		ArrayList<Cell> moveOptions = new ArrayList<Cell>();
		for (Cell neigh:cell.getNeighbors()) {
			if (neigh.getCurrentState() == emptyState) {
				moveOptions.add(neigh); // collect neighbors of correct type
			}
		}
		if (moveOptions.size() == 0) {
			return null;
		}
		return moveOptions.get((int) (moveOptions.size() * Math.random())); // return random eligible neighbor
	}

}
