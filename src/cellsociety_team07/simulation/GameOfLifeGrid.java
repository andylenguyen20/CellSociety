package cellsociety_team07.simulation;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

public class GameOfLifeGrid extends Grid{
	public GameOfLifeGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new UnboundedNeighborFinder(NeighborFinder.TOUCHING_NEIGHBORS, gridDimensions) );
	}
}