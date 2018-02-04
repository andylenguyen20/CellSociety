package cellsociety_team07;

import java.awt.Point;

public class GameOfLifeGrid extends Grid{
	public static final Point[] OFFSET_IGNORE = new Point[]{new Point(0,0)};
	public GameOfLifeGrid(int width, int height){
		super(width, height);	
		setNeighborFinder(new NeighborFinder(OFFSET_IGNORE));
	}
}
