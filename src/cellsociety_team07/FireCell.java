package cellsociety_team07;


import javafx.scene.paint.*;
import javafx.scene.paint.Paint;


public class FireCell extends Cell{

	public static final int EMPTY = 1; // no tree/burnt tree
	public static final int TREE = 0; // living tree
	public static final int BURNING = 2; // tree on fire
	public static final int PROB_CATCH = 0;
	//private double probCatch; // probability that tree will catch on fire
	public static final Paint[] colors = {Color.GREEN, Color.DARKGOLDENROD, Color.RED}; // Array of Colors
	
	
	public FireCell(int initialState, double[] props) {
		super(initialState, props);
		super.setColors(colors);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void update() {
		super.setCurrentState(super.getNextState());	
	}

	@Override
	public void applyRules() {
		// if currState = burning, it will be empty (burnt) next frame
		if(super.getCurrentState() == EMPTY){
			return;
		}
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
			if (prob <= super.getProps()[PROB_CATCH])
				super.setNextState(BURNING);
		}
		// if currState = empty, nothing happens
	}

}
