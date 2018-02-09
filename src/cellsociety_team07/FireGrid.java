package cellsociety_team07;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
public class FireGrid extends Grid{
	public static final Point[] NEIGHBOR_OFFSETS = new Point[]{new Point(-1,0), new Point(1,0), new Point(0,-1), new Point(0,1)};
	public FireGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new NeighborFinder(NeighborFinder.ADJACENT_NEIGHBORS));
	}

}
