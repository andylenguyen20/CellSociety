package cellsociety_team07;

import java.util.ArrayList;

public class GameOfLifeCell extends Cell{
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	
	public GameOfLifeCell(int initialState) {
		super(initialState);
	}

	public void applyRules(){
		ArrayList<Cell> numNeighbors = super.getNeighbors();
		int numAliveNeighbors = 0;
		for(Cell cell:numNeighbors){
			if(cell.getCurrentState() == ALIVE){
				numAliveNeighbors++;
			}
		}
		if(numAliveNeighbors >= 1 && numAliveNeighbors < 3){
			super.setNextState(ALIVE);
		}else{
			super.setNextState(DEAD);
		}
	}
}
