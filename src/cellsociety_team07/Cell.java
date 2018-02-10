package cellsociety_team07;

import java.util.List;

import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import java.awt.geom.Point2D.Double;
import javafx.scene.paint.Paint;

public abstract class Cell extends Polygon {
	public static final int DEFAULT_STATE = 0;
	private List<Cell> neighbors;
	private List<Double> vertices;
	private int currState, nextState;
	private double[] props;
	private static Paint[] colors;
	
	//private Rule rule = new GameOfLifeRule();
	
	public Cell() {
		
	}
	
	public void setInitialAttrivutes(int initialState, double[] params) {
		this.currState = initialState;
		this.nextState = initialState;
		this.props = params;
	}
	
	public void setVertices(List<Double> vertices) {
		this.vertices = vertices;
	}
	
	public List<Double> getVertices() {
		return vertices;
	}
	/*
	 * A method that updates the Cell’s state to the next state.
	 */
	public abstract void update();

	public void setNeighbors(List<Cell> neigh){
		neighbors = neigh;
	}
	public List<Cell> getNeighbors(){
		return neighbors;
	}
	public int getCurrentState(){
		return currState;
	}
	public void setCurrentState(int state){
		currState = state;
	}
	public int getNextState(){
		return nextState;
	}
	public void setNextState(int state){
		nextState = state;
	}
	
	public Paint getColor() {
		return colors[getCurrentState()];
	}
	 public Paint[]getColors(){
		 return colors;
	 }
	
	public void setColors(Paint[] arr) {
		colors = arr;
	}

	public double[] getProps(){
		return props;
	}
	
	public void setProps(double[] p) {
		props = p;
	}

	public abstract void applyRules();
}