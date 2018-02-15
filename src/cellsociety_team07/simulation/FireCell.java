package cellsociety_team07.simulation;


import javafx.scene.paint.*;
import javafx.scene.paint.Paint;

/**
 * The purpose of this class is to act as a specific subtype of Cell under the Fire simulation
 * conditions, following the rules of a Fire Cell accordingly.
 * @author Brendan Cheng
 *
 */
public class FireCell extends Cell{
	public static final int EMPTY = 1;
	public static final int TREE = 0;
	public static final int BURNING = 2;
	public static final int PROB_CATCH_INDEX = 0;
	public static final Paint[] colors = {Color.GREEN, Color.DARKGOLDENROD, Color.RED};
	
	/**
	 * initializes a new FireCell object that has a given set of colors
	 */
	public FireCell() {
		super();
		super.setColors(colors);
	}

	/**
	 * apply the FireCell rules:
	 * if you're an empty cell, your next state will be empty
	 * if you're a burning cell, your next state will be empty
	 * if you're neighbors with a burning cell, your next state could be alive or burning
	 */
	@Override
	public void applyRules() {
		if(super.getCurrentState() == EMPTY){
			return;
		}
		if (super.getCurrentState() == BURNING) {
			super.setNextState(EMPTY);
			return;
		}
		if (this.atRisk()) {
			double prob = Math.random();
			if (prob <= super.getParams()[PROB_CATCH_INDEX]){
				super.setNextState(BURNING);
			}
		}
	}

	/**
	 * 
	 * @return	a boolean indicating whether the tree is at risk of burning
	 */
	private boolean atRisk(){
		for (Cell tree:super.getNeighbors()) {
			if (tree.getCurrentState() == BURNING){
				return true;
			}
		}
		return false;
	}
}
