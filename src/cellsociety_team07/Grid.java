package cellsociety_team07;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.Dimension;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Grid{

	/************************** FIELDS *************************/
	private NeighborFinder neighFinder; // maps x,y coordinates of a cell on screen to the Cell object itself
	private Map<String, List<Cell>> vertexMap;
	private List<Cell> cells;
	private Dimension dimensions;
	/********************** CONSTRUCTOR ************************/
	
	/*
	 * @param
	 */
	public Grid(List<Cell> cells, Dimension gridDimensions){
		this.cells = cells;
		this.dimensions = gridDimensions;
		this.vertexMap = MapFactory.generateVertexMap(cells);
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
		/*
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells.length; j++){
				//cells[i][j].setNeighbors(neighFinder.findNeighbors(cells, i, j));
			}
		}
		*/
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
		/*
		for(Cell[] cellArray : cells){
			for(Cell cell : cellArray){
				cell.applyRules();
			}
		}
		*/
	}
	
	/*
	 * A method that updates the Grid’s state to the next state.
	 */
	public void update(){
		/*
		for(Cell[] cellArray : cells){
			for(Cell cell : cellArray){
				cell.update();
			}
		}*/
	}

}
