package cellsociety_team07;


import java.awt.Point;
import java.util.ArrayList;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SegregationCell extends Cell{
	public static final int EMPTY = 0; // an empty cell
	public static final int RACE1 = 1; // first race inside of the cell
	public static final int RACE2 = 2; // second race inside of the cell
	public static final int SIMILARITY_REQ = 0;
	private boolean isSatisfied;
	public static final Paint[] colors = {Color.AZURE, Color.RED, Color.BLUE}; // Array of Colors
	
	public SegregationCell(int initialState, double[] props) {
		super(initialState, props);
		super.setColors(colors);
		isSatisfied = true;

	}

	@Override
	public void applyRules() {
//<<<<<<< HEAD
//		int same = 0; // number of cells of same type surrounding this cell
//		int total = this.getNeighbors().size(); // total number of neighbors
//		for (Cell neigh:this.getNeighbors()) {
//			if (neigh.getCurrentState() == this.getCurrentState())
//				same++;
//		}
//=======
		int numLiveNeighbors = 0;
		int numSimilar = 0;
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() != EMPTY){
				numLiveNeighbors++;
			}
			if (cell.getCurrentState() == super.getCurrentState()){
				numSimilar++;
			}
		}
	}
	public void move(CellMover cm){
		ArrayList<Point> emptyLocations = cm.getEmptyLocations(EMPTY);
		Point myChoice = emptyLocations.get((int) Math.random() * emptyLocations.size());

	}

	
	public boolean isSatisifed(){
		return isSatisfied;
	}
	public boolean isEmpty(){
		return this.getCurrentState() == EMPTY;

	}
}
