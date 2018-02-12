package cellsociety_team07.simulation;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

public class WatorGrid extends Grid implements CellFetcher{
	public WatorGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new UnboundedNeighborFinder(UnboundedNeighborFinder.ADJACENT_NEIGHBORS, gridDimensions));
	}
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
