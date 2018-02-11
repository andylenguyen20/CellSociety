package cellsociety_team07;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
public class FireGrid extends Grid{
	public FireGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new UnboundedNeighborFinder(NeighborFinder.ADJACENT_NEIGHBORS, gridDimensions));
	}

}
