package cellsociety_team07;

public class Grid {
	private Cell[][] cells;
	public Grid(int width, int height){
		cells = new Cell[width][height];
	}
	
	/*
	 * sets cell neighbors
	 */
	public void setCellNeighbors(){
		
	}
	
	/*
	 * gets its cells
	 */
	public Cell[][] getCells(){
		return cells;
	}
	
	/*
	 * sets the rules for each individual cell.
	 */
	private void setCellRules(){
		
	}
	
	/*
	 * getRules from an XML document
	 */
	private void getRules(){
		
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
