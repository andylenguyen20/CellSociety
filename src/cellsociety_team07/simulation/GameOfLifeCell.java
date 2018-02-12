package cellsociety_team07.simulation;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.*;

public class GameOfLifeCell extends Cell{
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	public static final Paint[] colors = {Color.BLACK, Color.AQUA};
	private static final int MIN_LIVING_NEIGHBORS=2;
	private static final int MAX_LIVING_NEIGHBORS = 4;
	private static final int NEIGHBORS_FOR_RESPAWN=3;
	
	
	public GameOfLifeCell() {
		super();
		super.setColors(colors);
	}

	@Override
	public void update() {
		super.setCurrentState(super.getNextState());	
	}
	
	public void applyRules(){
		int alive = 0;
		for (Cell neigh:super.getNeighbors()) {
			alive += neigh.getCurrentState();
		}
		if(alive >= MIN_LIVING_NEIGHBORS && alive <MAX_LIVING_NEIGHBORS && this.getCurrentState() == ALIVE){
			super.setNextState(ALIVE);
		}else if(alive == NEIGHBORS_FOR_RESPAWN && this.getCurrentState() == DEAD){
			super.setNextState(ALIVE);
		}else{
			super.setNextState(DEAD);
		}
	}

}
