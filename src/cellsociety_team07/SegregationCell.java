package cellsociety_team07;

import javafx.scene.paint.*;

public class SegregationCell extends Cell{
	
	private static final int EMPTY = 0;
	private static final int RED = 1;
	private static final int BLUE = 2;
	private static final Paint[] colors = {Color.WHITE, Color.RED, Color.BLUE};
	private static double satisfaction; // satisfaction threshold

	public SegregationCell(int initialState) {
		super(initialState);
		super.setColors(colors);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyRules() {
		int same = 0; // number of cells of same type surrounding this cell
		int total = this.getNeighbors().size(); // total number of neighbors
		for (Cell neigh:this.getNeighbors()) {
			if (neigh.getCurrentState() == this.getCurrentState())
				same++;
		}
		
		double frac = same/total;
		
		if (frac < satisfaction) { // if not enough similar neighbors
			this.move(); // move to an empty cell
		}
	}
	
	public void move() { // move to empty cell
		
	}

}
