package cellsociety_team07;

import java.util.ArrayList;

public abstract class Cell {
	private ArrayList<Cell> neighbors;
	private int currState;
	public Cell(){
		
	}
	
	/*
	 * A method that allows the cell to know what rules it abides by in the CA simulation and returns a next state given these rules.
	 */
	private void setRules(){
		
	}
	/*
	 * A method that updates the Cell’s state to the next state.
	 */
	public void update(){
		
	}
	private int applyRules(){
		return 0;
	}
}