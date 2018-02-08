package cellsociety_team07;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Paint;

public abstract class Cell extends Rectangle {
	public static final int DEFAULT_STATE = 0;
	private ArrayList<Cell> neighbors;
	private int currState, nextState;
	private double[] props;
	private static Paint[] colors;

	
	//private Rule rule = new GameOfLifeRule();
	
	public Cell(int initialState, double[] props){
		this.currState = initialState;
		this.props = props;
		this.nextState = initialState;
	}
	
	/*
	 * A method that updates the Cell’s state to the next state.
	 */
	public abstract void update();

	public void setNeighbors(ArrayList<Cell> neigh){
		neighbors = neigh;
	}
	public ArrayList<Cell> getNeighbors(){
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

	public abstract void applyRules();
}


