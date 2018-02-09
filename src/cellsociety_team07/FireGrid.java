package cellsociety_team07;

import java.awt.Point;
public class FireGrid extends Grid{
	public static final Point[] NEIGHBOR_OFFSETS = new Point[]{new Point(-1,0), new Point(1,0), new Point(0,-1), new Point(0,1)};
	public FireGrid(int width, int height){
		super(width, height);
		setNeighborFinder(new NeighborFinder(0));
	}

}
