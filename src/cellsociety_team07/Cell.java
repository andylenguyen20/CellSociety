package cellsociety_team07;

import java.util.ArrayList;

import javafx.scene.Node;

public abstract class Cell{
	public static final int DEFAULT_STATE = 0;
	private ArrayList<Cell> neighbors;
	private int currState, nextState;
	
	//private Rule rule = new GameOfLifeRule();
	
	public Cell(int initialState){
		currState = initialState;
	}
	
	/*
	 * A method that allows the cell to know what rules it abides by in the CA simulation and returns a next state given these rules.
	 */
	public void setRules(){
		
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
	/*
	public void applyRules(){
		nextState = rule.getNextState(neighbors, currState);
	}*/
	public abstract void applyRules();
}
