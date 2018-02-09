package cellsociety_team07;

import java.awt.geom.Point2D.Double;
import java.util.List;

import javafx.scene.paint.Paint;

public abstract class Cell{
	public static final int DEFAULT_STATE = 0;
	private List<Double> vertices;
	private List<Cell> neighbors;
	private int currState, nextState;
	private double[] params;
	private static Paint[] colors;
	
	public Cell(){
	}
	
	/*
	 * A method that updates the Cell’s current state to the next state.
	 */
	public abstract void update();

	/*
	 * A method that applies the cell's rules to get its next state
	 */
	public abstract void applyRules();
	
	/*
	 * A method that sets the neighbors of the cell
	 */
	public void setNeighbors(List<Cell> neigh){
		neighbors = neigh;
	}
	
	/*
	 * gets the cell's neighbors
	 */
	public List<Cell> getNeighbors(){
		return neighbors;
	}
	
	/*
	 * gets current state of the cell
	 */
	public int getCurrentState(){
		return currState;
	}
	
	/*
	 * sets the current state of the cell
	 */
	public void setCurrentState(int state){
		currState = state;
	}
	
	/*
	 * gets the next state of the cell
	 */
	public int getNextState(){
		return nextState;
	}
	
	/*
	 * sets the next state of the cell
	 */
	public void setNextState(int state){
		nextState = state;
	}
	
	/*
	 * gets the color of the cell based off the current state of the cell
	 */
	public Paint getColor() {
		return colors[getCurrentState()];
	}
	
	/*
	 * initializes the colors of the cell, called by the specific subclass of the cell to initialize colors
	 */
	protected void setColors(Paint[] arr) {
		colors = arr;
	}
	
	public void setInitialAttributes(int initialState, double[] params){
		this.currState = initialState;
		this.nextState = initialState;
		this.params = params;
	}
	
	/*
	 * gets the properties of the cell, called by the specific subclass of the cell to get its properties
	 */
	protected double[] getParams(){
		return params;
	}
	
	/*
	 * sets the vertices of the cell
	 */
	public void setVertices(List<Double> vertices){
		this.vertices = vertices;
	}
	
	/*
	 * gets the vertices of the cell
	 */
	public List<Double> getVertices(){
		return vertices;
	}
}


