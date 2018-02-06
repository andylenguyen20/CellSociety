package cellsociety_team07;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public class PredatorPreyCell extends Cell {
	
	/* Constants that dictate indices in props[] array */
	public static final int FISH_REPRODUCTION_CHRONON = 0;
	public static final int SHARK_REPRODUCTION_CHRONON = 1;
	public static final int STARTING_ENERGY = 2;
	public static final int FISH_ENERGY = 3;
	/* Fields */
	private boolean toReproduce; // Determines if fish/shark should reproduce next turn
	private boolean toBeMoved; // Determines if fish/shark should move next turn
	private boolean toBeEaten; // if fish, determines if it will be eaten
	private double energy; // Life energy; sharks only
	private double reproductionCounter; // number of cycles fish/shark has gone without reproducing
	private double reproductionTime; // time limit at which point fish/shark can reproduce, dependent on props
	private PredatorPreyCell next; // if toBeMoved is true, next points to cell to be swapped with
	public static final int FISH = 0; // Fish state
	public static final int SHARK = 1; // Shark state
	public static final int WATER = 2; // Water state
	public static final Paint[] colors = {Color.PINK, Color.GREY, Color.BLUE}; // Colors corresponding to state


	
	public PredatorPreyCell(int initialState, double[] props) {
		super(initialState, props);
		super.setColors(colors);
		toBeMoved = false;
		toReproduce = false;
		energy = props[STARTING_ENERGY];
		reproductionCounter = 0;
		if (initialState == 0 || initialState == 1) {
			reproductionTime = props[initialState]; // only set reproductionTime if not water cell
		}
	}

	
	
	@Override
	public void update() {
		super.setCurrentState(super.getNextState());
	}

	@Override
	public void applyRules() {
		// Update Fields
		decEnergy();
		reproductionCounter++;
		if (energy <= 0)
			this.setNextState(WATER);
		if (reproductionCounter == reproductionTime)
			toReproduce = true;
		// Check movement/reproduction conditions
		for (Cell cell:getNeighbors()) {
			if (getCurrentState() == SHARK) {
				
			} else if (getCurrentState() == FISH) { // If fish, check for open water
				if (cell.getCurrentState() == WATER) {
					toBeMoved = true;
				}
			}
		}

	}
	
	public void decEnergy() {
		if (this.getCurrentState() == SHARK) {
			energy--;
		}
	}
	
	public boolean toBeMoved() {
		return toBeMoved;
	}
	
	public boolean toReproduce() {
		return (reproductionCounter >= reproductionTime) && toBeMoved;
	}
	
	/**
	 * Used to set toBeMoved variable
	 */
	public boolean sharkMove() {
		
	}

}
