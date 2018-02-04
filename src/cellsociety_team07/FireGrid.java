package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;

public class FireGrid extends Grid{
	public static final Point[] ignore = new Point[]{new Point(0,0), new Point(-1,-1), new Point(-1,1), new Point(1,1), new Point(1,-1)};
	public FireGrid(int width, int height){
		super(width, height);
		setNeighborFinder(new NeighborFinder(ignore));
	}

}
