package cellsociety_team07;

public interface CellMover {
	void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeMovedTo);
    void transferVerticesAndNeighbors(Cell source, Cell receiver);
}
