package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;

public interface CellMover {
	ArrayList<Point> getEmptyLocations(int emptyStateß);
	void switchCellStates(Cell movingCell, Point moveLocation, int replacementState);
}
