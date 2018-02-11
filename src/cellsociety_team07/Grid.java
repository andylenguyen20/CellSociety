package cellsociety_team07;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Grid{

	/************************** FIELDS *************************/
	private NeighborFinder neighFinder;
	private Map<String, List<Cell>> vertexMap; // maps x,y coordinates of a cell on screen to the Cell object itself
	private List<Cell> cells;
	private Dimension gridDimensions;
	
	/********************** CONSTRUCTOR ************************/
	
	/*
	 * @param
	 */
	public Grid(List<Cell> cells, Dimension gridDimensions){
		this.cells = cells;
		this.gridDimensions = gridDimensions;
		this.vertexMap = MapFactory.generateVertexMap(cells);
		//this.setCellNeighbors();
	}
	
	/********************** METHODS ****************************/
	
	/*
	 * Return ArrayList of all cells
	 */
//	public ArrayList<Cell> getCells() {
//		ArrayList<Cell> cellList = new ArrayList<Cell>();
//		for (Point p:grid.keySet()) {
//			cellList.add(grid.get(p));
//		}
//		return cellList;
//	}
	
	/*
	 * sets cell neighbors
	 */
	public void setCellNeighbors(){
		for(Cell cell : cells){
			cell.setNeighbors(neighFinder.findNeighbors(cell, vertexMap));
			
		}
	}
	
	public void setNeighborFinder(NeighborFinder neighFinder){
		this.neighFinder = neighFinder;
	}
	
	/*
	 * gets its cells
	 */
	public List<Cell> getCells(){
		return cells;
	}
	
	public void prepareNextState(){
		for(Cell cell : cells){
			cell.applyRules();
		}
	}
	
	public double numRows() {
		return gridDimensions.getHeight();
	}
	
	public double numCols() {
		return gridDimensions.getWidth();
	}
	
	/*
	 * A method that updates the Grid’s state to the next state.
	 */
	public void update(){
		for(Cell cell : cells){
			cell.update();
		}
	}

}

