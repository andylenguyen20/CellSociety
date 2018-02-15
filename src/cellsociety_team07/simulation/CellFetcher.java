package cellsociety_team07.simulation;


/**
 * The purpose of this interface is to allow Cells to fetch other cells of a certain type on a random
 * basis. This interface is implemented by the SegregationGrid and WatorGrid classes and allows
 * WatorCell and SegregationCell to delegate the responsibility of finding appropriate cells on the
 * map to their respective Grids, since they don't have access to the grid directly. Having this interface
 * also encapsulates the information from the Grid classes that extend this interface.
 * @author Andy Nguyen
 *
 */
public interface CellFetcher {
	/**
	 * 
	 * @param desiredState	the state of the cell you want to find
	 * @param cell			the cell that is requesting CellFetcher to fetch a cell for it
	 * @return				a random cell that the given cell wants to find within the grid
	 */
	Cell getCellOfType(int desiredState, Cell cell);
}