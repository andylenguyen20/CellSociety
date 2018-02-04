package cellsociety_team07;


import javafx.scene.paint.*;
import javafx.scene.paint.Paint;


public class FireCell extends Cell{

	public static final int EMPTY = 0; // no tree/burnt tree
	public static final int TREE = 1; // living tree
	public static final int BURNING = 2; // tree on fire
	private double probCatch; // probability that tree will catch on fire
	public static final Paint[] colors = {Color.BLACK, Color.GREEN, Color.RED}; // Array of Colors
	
	
	public FireCell(int initialState) {
		super(initialState);
		super.setColors(colors);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyRules() {
		// if currState = burning, it will be empty (burnt) next frame
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
		if (risk) { // if there are burning neighbors, generate random # to dictate if tree catches fire
			double prob = Math.random();
			if (prob <= probCatch)
				super.setNextState(BURNING);
		}
		// if currState = empty, nothing happens
	}

}
