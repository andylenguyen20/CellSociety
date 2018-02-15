package cellsociety_team07.simulation;

import java.awt.Dimension;
import java.util.List;

/**
 * The purpose of this class is to have a class that holds FireCells and has a unique neighbor type.
 * This class's functionality is very similar to the abstract Grid's functionality, except it defines
 * the NeighborFinder object for this grid, which is bounded and has adjacent neighbors (4 for rectangle and
 * 3 for triangle).
 * @author Andy Nguyen
 *
 */
public class FireGrid extends Grid{
	/**
	 * initializes a FireGrid  with a given list of cells and grid dimensions
	 * @param cells
	 * @param gridDimensions
	 */
	public FireGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new NeighborFinder(NeighborFinder.ADJACENT_NEIGHBORS));
	}
}


