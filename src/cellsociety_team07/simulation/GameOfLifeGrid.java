package cellsociety_team07.simulation;

import java.awt.Dimension;
import java.util.List;

/**
 * The purpose of this class is to have a class that holds GameOfLifeCells and has a unique neighbor type.
 * This class's functionality is very similar to the abstract Grid's functionality, except it defines
 * the NeighborFinder object for this grid, which is bounded and has touching neighbors (8 for rectangle
 * and 12 for triangle).
 * @author Andy Nguyen
 *
 */
public class GameOfLifeGrid extends Grid{
	/**
	 * initializes a new GameOfLifeGrid object with a list of cells and grid dimensions
	 */
	public GameOfLifeGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new NeighborFinder(NeighborFinder.TOUCHING_NEIGHBORS));
	}
}

