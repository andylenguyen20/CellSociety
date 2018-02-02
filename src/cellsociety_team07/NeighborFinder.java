package cellsociety_team07;

import java.util.ArrayList;

public abstract class NeighborFinder {
	Cell[][] cells;
	public NeighborFinder(Cell[][] cells){
		this.cells = cells;
	}
	public abstract ArrayList<Cell> getNeighbors(int i, int j);
}
