package cellsociety_team07;
import java.util.HashMap;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Grid{

	/************************** FIELDS *************************/
	
	HashMap<Point,Cell> grid; // maps x,y coordinates of a cell on screen to the Cell object itself
	
	
	/********************** CONSTRUCTOR ************************/
	
	/*
	 * @param
	 */
	public Grid() {
		
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
	
	private Cell[][] cells;
	public Grid(int width, int height){
		cells = new Cell[width][height];
	}
	
	/*
	 * sets cell neighbors
	 */
	public abstract void setCellNeighbors();
	
	/*
	 * gets its cells
	 */
	public Cell[][] getCells(){
		return cells;
	}
	
	
	/*
	 * A method that updates the Grid’s state to the next state.
	 */
	public void update(){
		for(Cell[] cellArray : cells){
			for(Cell cell : cellArray){
				cell.update();
			}
		}
	}

}
