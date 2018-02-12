package cellsociety_team07.simulation;

import java.util.List;


import javafx.scene.shape.Polygon;
import java.awt.geom.Point2D.Double;
import javafx.scene.paint.Paint;

/**
 * The purpose of this class is to act as a cell in a grid and provide an abstraction for specific types of cells
 * in other simulations. Every cell has a current state and next state, as well as a list of simulation parameters that
 * it follows. In addition, every cell can be drawn to the grid and has a set of vertices that represent its
 * location in the grid's coordinate system. These vertices can then be scaled to draw to the screen.
 * @author Andy Nguyen
 *
 */
public abstract class Cell extends Polygon {
	public static final int DEFAULT_STATE = 0;
	private List<Cell> neighbors;
	private List<Double> vertices;
	private int currState, nextState;
	private double[] params;
	private static Paint[] colors;
	
	/**
	 * 
	 * @param initialState	the state for the cell when it is first initialized
	 * @param params		the simulation parameters that the cell will behave with
	 */
	public void setInitialAttributes(int initialState, double[] params) {
		this.currState = initialState;
		this.nextState = initialState;
		this.params = params;
	}
	
	/**
	 * 
	 * @param vertices	the list of vertices that this cell has in the grid's coordinate system
	 */
	public void setVertices(List<Double> vertices) {
		this.vertices = vertices;
	}
	
	/**
	 * 
	 * @return	the list of vertices that this cell has in the grid's coordinate system
	 */
	public List<Double> getVertices() {
		return vertices;
	}
	
	/**
	 * abstract function which gets called whenever the cell needs to update itself
	 */
	public void update(){
		currState = nextState;
	}

	/**
	 * abstract function which gets called whenever the cell needs to assign its next state
	 */
	public abstract void applyRules();
	
	/**
	 * 
	 * @param neigh	list of cells that are now assigned to the cell as its neighbors
	 */
	public void setNeighbors(List<Cell> neigh){
		neighbors = neigh;
	}
	
	/**
	 * 
	 * @return list of neighbors that this cell has
	 */
	public List<Cell> getNeighbors(){
		return neighbors;
	}
	
	/**
	 * 
	 * @return the current state of the cell
	 */
	public int getCurrentState(){
		return currState;
	}
	
	/**
	 * 
	 * @param state	the state that you want to set the cell's current state to
	 */
	public void setCurrentState(int state){
		currState = state;
	}
	
	/**
	 * 
	 * @return the next state of the cell
	 */
	public int getNextState(){
		return nextState;
	}
	
	/**
	 * 
	 * @param state the state that you want to set the cell's next state to
	 */
	public void setNextState(int state){
		nextState = state;
	}
	
	/**
	 * 
	 * @return the color of the cell that represents its current state
	 */
	public Paint getColor() {
		return colors[getCurrentState()];
	}
	
	/**
	 * 
	 * @return the list of colors that this cell could possibly be drawn with
	 */
	public Paint[] getColors(){
		return colors;
	}
	
	/**
	 * 
	 * @param arr	set the list of colors that this cell could possible be drawn with
	 */
	protected void setColors(Paint[] arr) {
		colors = arr;
	}

	/**
	 * 
	 * @return the list of simulation parameters that this cell behaves on
	 */
	public double[] getParams(){
		return params;
	}
	
	/**
	 * 
	 * @param p	sets the simulation parameters of this cell
	 */
	public void setParams(double[] p) {
		params = p;
	}
}