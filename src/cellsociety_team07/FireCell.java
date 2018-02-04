package cellsociety_team07;


import javafx.scene.paint.*;
import java.math.*;
import javafx.scene.paint.Paint;


public class FireCell extends Cell{

	public static final int EMPTY = 0; // no tree/burnt tree
	public static final int TREE = 1; // living tree
	public static final int BURNING = 2; // tree on fire
	private double probCatch; // probability that tree will catch on fire
	public static final Paint[] colors = {Color.BLACK, Color.GREEN, Color.RED};
	public FireCell(int initialState) {
		super(initialState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyRules() {
		if (super.getCurrentState() == BURNING) {
			super.setNextState(EMPTY);
			return;
		}
		// if currState = Tree, check neighbors
		boolean risk = false; // check if this tree is at risk of burning by checking neighbors
		for (Cell tree:super.getNeighbors()) {
			if (tree.getCurrentState() == BURNING)
				risk = true;
		}
		if (risk) {
			double prob = Math.random();
			if (prob <= probCatch)
				super.setNextState(BURNING);
		}
	}

	@Override
	public Paint getColors() {
		return colors[super.getCurrentState()];
	}

}
