package cellsociety_team07.simulation;

import javafx.scene.paint.*;

/**
 * The purpose of this class is to act as a specific subtype of Cell under the GameOfLife simulation
 * conditions, following the rules of a GameOfLife Cell accordingly.
 * @author Andy Nguyen
 *
 */
public class GameOfLifeCell extends Cell{
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	public static final Paint[] colors = {Color.BLACK, Color.AQUA};
	private static final int MIN_LIVING_NEIGHBORS = 2;
	private static final int MAX_LIVING_NEIGHBORS = 4;
	private static final int NEIGHBORS_FOR_RESPAWN = 3;
	
	/**
	 * instantiates a new GameOfLifeCell object
	 */
	public GameOfLifeCell() {
		super();
		super.setColors(colors);
	}
	
	/**
	 * applies the GameOfLife rules as a cell in the GameOfLifeGrid
	 */
	public void applyRules(){
		int alive = 0;
		for (Cell neigh:super.getNeighbors()) {
			alive += neigh.getCurrentState();
		}
		if(alive >= MIN_LIVING_NEIGHBORS && alive < MAX_LIVING_NEIGHBORS && this.getCurrentState() == ALIVE){
			super.setNextState(ALIVE);
		}else if(alive == NEIGHBORS_FOR_RESPAWN && this.getCurrentState() == DEAD){
			super.setNextState(ALIVE);
		}else{
			super.setNextState(DEAD);
		}
	}

}

