package cellsociety_team07.simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

/**
 * The purpose of this class is to have a class that holds WatorCells and has a unique neighbor type.
 * This class has adjacent neighbors in an unbounded grid. This class also extends the meaning of Grid in that
 * it overrides the prepareNextState method to allow WatorCells to apply their rules given the passing of the
 * CellFetcher interface. This class implements the CellFetcher interface, which fetches a random neighbor for any
 * given cell of requested cell type.
 * @author Andy Nguyen
 *
 */
public class WatorGrid extends Grid implements CellFetcher{
	/**
	 * Instantiates a new WaterGrid object that has a list of cells and dimensions.
	 * @param cells
	 * @param gridDimensions
	 */
	public WatorGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new UnboundedNeighborFinder(UnboundedNeighborFinder.ADJACENT_NEIGHBORS, gridDimensions));
	}
	
	/**
	 * Prepares the next state of the cell. It does this by applying the rules for the sharks first and then
	 * applying the rules for fish and water cells.
	 */
	@Override
	public void prepareNextState(){
		List<Cell> grid = super.getCells();
		for(Cell cell : grid){
			WatorCell watorCell = (WatorCell) cell;
			if(watorCell.isShark()){
				(watorCell).applyRules(this);
			}
		}
		for(Cell cell : grid){
			WatorCell watorCell = (WatorCell) cell;
			if(!watorCell.isShark()){
				(watorCell).applyRules(this);
			}
		}
	}
	
	/**
	 * @see CellFetcher for more information on this. This method fetches a random neighbor of a specific cell type
	 */
	@Override
	public Cell getCellOfType(int desiredState, Cell cell) {
		List<Cell> potentialCells = new ArrayList<Cell>();
		for(Cell neighbor : cell.getNeighbors()){
			if(neighbor.getCurrentState() == desiredState && neighbor.getNextState() == desiredState){
				potentialCells.add(neighbor);
			}
		}
		return potentialCells.get((int) ( Math.random() * potentialCells.size()));
	}
}

