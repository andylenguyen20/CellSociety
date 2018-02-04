package cellsociety_team07;
import java.util.HashMap;
import java.awt.Point;
import java.util.ArrayList;

public abstract class Grid{

	/************************** FIELDS *************************/
	private NeighborFinder neighFinder;
	private HashMap<Point,Cell> grid; // maps x,y coordinates of a cell on screen to the Cell object itself
	private Cell[][] cells;
	
	/********************** CONSTRUCTOR ************************/
	
	/*
	 * @param
	 */
	public Grid() {
		setCellNeighbors();
	}
	
	public Grid(int width, int height){
		cells = new Cell[width][height];

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
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells.length; j++){
				cells[i][j].setNeighbors(neighFinder.getCellNeighborhood(cells, i, j));
				System.out.println("hi");
			}
		}
	}
	
	public void setNeighborFinder(NeighborFinder neighFinder){
		this.neighFinder = neighFinder;
	}
	
	/*
	 * gets its cells
	 */
	public Cell[][] getCells(){
		return cells;
	}
	
	public void prepareNextState(){
		System.out.println("wasdaf");
		for(Cell[] cellArray : cells){
			for(Cell cell : cellArray){
				cell.applyRules();
			}
		}
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
