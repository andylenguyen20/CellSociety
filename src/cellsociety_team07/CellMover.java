package cellsociety_team07;

public interface CellMover {
	void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeOverwritten);
	Cell getRandomEmptyCell(int emptyState, Cell cell);
}
