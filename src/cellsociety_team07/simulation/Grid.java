package cellsociety_team07.simulation;

import java.util.List;
import java.util.Map;
import java.awt.Dimension;

/**
 * The purpose of this class is to provide an abstraction for specific cell grids that extend this
 * class. All Grids have a neighborfinder, a vertexmap, a list of cells, and a set of dimensions. Additionally,
 * all Grids are able to prepare a next state and update
 * @author macbookair
 *
 */
public abstract class Grid{
	private NeighborFinder neighFinder;
	private Map<String, List<Cell>> vertexMap;
	private List<Cell> cells;
	private Dimension gridDimensions;
	
	/**
	 * instantiates a new Grid object given a list of cells and dimensions
	 * @param cells
	 * @param gridDimensions
	 */
	public Grid(List<Cell> cells, Dimension gridDimensions){
		this.cells = cells;
		this.gridDimensions = gridDimensions;
		this.vertexMap = MapFactory.generateVertexMap(cells);
	}
	
	/**
	 * sets the neighbors for all of the cells in the grid using the NeighborFinder object specific
	 * to this Grid
	 */
	public void setCellNeighbors(){
		for(Cell cell : cells){
			cell.setNeighbors(neighFinder.findNeighbors(cell, vertexMap));
		}
	}
	
	/**
	 * sets the NeighborFinder object for this grid, which determines how the neighbors are
	 * going to be assigned to the cells
	 * @param neighFinder	the NeighborFinder object to assign to this Grid
	 */
	public void setNeighborFinder(NeighborFinder neighFinder){
		this.neighFinder = neighFinder;
	}
	
	/**
	 * 
	 * @return	the cells that are in this grid
	 */
	public List<Cell> getCells(){
		return cells;
	}
	
	/**
	 * prepares all of the cells' next states in the grid
	 */
	public void prepareNextState(){
		for(Cell cell : cells){
			cell.applyRules();
		}
	}
	
	/**
	 * 
	 * @return 	the number of rows given its dimensions
	 */
	public double numRows() {
		return gridDimensions.getHeight();
	}
	
	/**
	 * 
	 * @return	the number of columns given its dimensions
	 */
	public double numCols() {
		return gridDimensions.getWidth();
	}
	
	/**
	 * updates all of the cells in the grid
	 */
	public void update(){
		for(Cell cell : cells){
			cell.update();
		}
	}
}

