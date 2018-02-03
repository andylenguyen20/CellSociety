package cellsociety_team07;

public class GameOfLifeGrid extends Grid{
	private NeighborFinder neighFinder;
	public GameOfLifeGrid(int width, int height){
		super(width, height);
		neighFinder = new NeighborFinder();
	}
	@Override
	public void setCellNeighbors() {
		Cell[][] cells = super.getCells();
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells.length; j++){
				cells[i][j].setNeighbors(neighFinder.getCellNeighborhood(cells, i, j));
			}
		}
	}
}
