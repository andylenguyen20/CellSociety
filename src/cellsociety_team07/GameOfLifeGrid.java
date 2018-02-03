package cellsociety_team07;

public class GameOfLifeGrid extends Grid{
	public GameOfLifeGrid(int width, int height){
		super(width, height);	
		setNeighborFinder(new NeighborFinder());
	}
}
