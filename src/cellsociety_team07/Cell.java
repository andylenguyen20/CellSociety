package cellsociety_team07;

import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.Paint;

public abstract class Cell extends Rectangle {
	public static final int DEFAULT_STATE = 0;
	private ArrayList<Cell> neighbors;
	private int currState, nextState;
	private static Paint[] colors;
	
	//private Rule rule = new GameOfLifeRule();
	
	public Cell(int initialState){
		currState = initialState;
	}
	
	/*
	 * A method that updates the Cell’s state to the next state.
	 */
	public void update(){
		currState = nextState;
	}

	public void setNeighbors(ArrayList<Cell> neigh){
		neighbors = neigh;
	}
	public ArrayList<Cell> getNeighbors(){
		return neighbors;
	}
	public int getCurrentState(){
		return currState;
	}
	public void setNextState(int state){
		nextState = state;
	}
	
	public Paint getColors() {
		return colors[getCurrentState()];
	}
	
	public void setColors(Paint[] arr) {
		colors = arr;
	}

	public abstract void applyRules();
}


