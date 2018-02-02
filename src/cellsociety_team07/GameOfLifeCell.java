package cellsociety_team07;

public class GameOfLifeCell extends Cell{
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	
	public GameOfLifeCell(int initialState) {
		super(initialState);
	}

	public void applyRules(){
		int numNeighbors = super.getNeighbors().size();
		if(numNeighbors >= 1 && numNeighbors <3){
			super.setNextState(ALIVE);
		}else{
			super.setNextState(DEAD);
		}
	}
}
