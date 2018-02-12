package cellsociety_team07.simulation;

import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to have a class that holds SegregationCells and has a unique neighbor type.
 * This class has touching neighbors in a bounded grid. This class also extends the meaning of Grid in that
 * it overrides the prepareNextState method to allow SegregationCells to apply their rules given the passing of the
 * CellFetcher interface. This class implements the CellFetcher interface, which fetches a random empty cell for a
 * given cell to migrate to
 * @author Andy Nguyen
 *
 */
public class SegregationGrid extends Grid implements CellFetcher{
	/**
	 * Instantiates a new SegregationGrid
	 * @param cells
	 * @param gridDimensions
	 */
	public SegregationGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		super.setNeighborFinder(new NeighborFinder(NeighborFinder.TOUCHING_NEIGHBORS));
	}
	
	/**
	 * Prepares the next state of the cell
	 */
	@Override
	public void prepareNextState(){
		List<Cell> grid = super.getCells();
		for(Cell cell : grid) {
			SegregationCell currCell = (SegregationCell) cell;
			currCell.applyRules(this);
		}
	}
	
	/**
	 * @see CellFetcher for more information on this. This method fetches a random empty cell in the grid
	 */
	@Override
	public Cell getCellOfType(int emptyState, Cell cell) {
		ArrayList<Cell> emptyCells = new ArrayList<Cell>();
		List<Cell> grid = super.getCells();
		for(Cell currCell : grid) {
			if(currCell == cell){
				continue;
			}
			if(currCell.getCurrentState() == emptyState && currCell.getNextState() == emptyState){
				emptyCells.add(currCell);
			}
		}
		return emptyCells.get((int) (Math.random() * emptyCells.size()));
	}
}