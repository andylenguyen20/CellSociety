package cellsociety_team07;

import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class WatorGrid extends Grid implements CellFetcher, CellMover{
	List<Cell> cellsToAdd;
	List<Cell> cellsToRemove;
	public WatorGrid(List<Cell> cells, Dimension gridDimensions){
		super(cells, gridDimensions);
		setNeighborFinder(new UnboundedNeighborFinder(UnboundedNeighborFinder.ADJACENT_NEIGHBORS, gridDimensions));
	}
	@Override
	public void update(){
		cellsToAdd = new ArrayList<>();
		cellsToRemove = new ArrayList<>();
		List<Cell> grid = super.getCells();
		for(Cell cell : grid){
			WatorCell watorCell = (WatorCell) cell;
			watorCell.update(this);
		}
		System.out.println(cellsToAdd.size());
		grid.addAll(cellsToAdd);
		grid.removeAll(cellsToRemove);
		System.out.println(cellsToRemove.size());
	}
	@Override
	public void prepareNextState(){
		List<Cell> grid = super.getCells();
		for(Cell cell : grid){
			WatorCell watorCell = (WatorCell) cell;
			if(watorCell instanceof SharkCell){
				((SharkCell) watorCell).applyRules(this);
			}
		}
		for(Cell cell : grid){
			WatorCell watorCell = (WatorCell) cell;
			if(watorCell instanceof FishCell){
				((FishCell) watorCell).applyRules(this);
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
	@Override
	public void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeMovedTo) {
		movingCell.getNeighbors().remove(toBeMovedTo);
		toBeMovedTo.getNeighbors().remove(movingCell);
		List<Cell> savedMovingCellNeighbors = movingCell.getNeighbors();
		for(Cell neighbor : movingCell.getNeighbors()){
			neighbor.getNeighbors().remove(movingCell);
		}
		for(Cell neighbor: toBeMovedTo.getNeighbors()){
			neighbor.getNeighbors().remove(toBeMovedTo);
			neighbor.getNeighbors().add(movingCell);
		}
		for(Cell neighbor: savedMovingCellNeighbors){
			neighbor.getNeighbors().add(movingCellReplacement);
		}
		movingCellReplacement.setNeighbors(savedMovingCellNeighbors);
		movingCellReplacement.getNeighbors().add(toBeMovedTo);
		toBeMovedTo.getNeighbors().add(movingCellReplacement);
		
		List<Point2D.Double> movingCellVertices = movingCell.getVertices();
		List<Point2D.Double> toBeMovedToVertices = toBeMovedTo.getVertices();
		movingCell.setVertices(toBeMovedToVertices);
		movingCellReplacement.setVertices(movingCellVertices);
		/*
		//TODO: make it cleaner
		this.transferVerticesAndNeighbors(movingCell, movingCellReplacement);
		cellsToAdd.add(movingCellReplacement);
		this.transferVerticesAndNeighbors(toBeMovedTo, movingCell);
		cellsToRemove.add(toBeMovedTo);
		*/
	}
	@Override
	public void transferVerticesAndNeighbors(Cell source, Cell receiver){
		List<Point2D.Double> verticesToTransfer = source.getVertices();
		receiver.setVertices(verticesToTransfer);
		List<Cell> neighborsToTransfer = source.getNeighbors();
		receiver.setNeighbors(neighborsToTransfer);
		for(Cell neighbor : receiver.getNeighbors()){
			neighbor.getNeighbors().add(receiver);
			neighbor.getNeighbors().remove(source);
		}
	}
}
