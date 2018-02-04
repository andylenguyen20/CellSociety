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
	private boolean toBeMoved;
	public static final Paint[] colors = {Color.AZURE, Color.RED, Color.BLUE}; // Array of Colors
	
	public SegregationCell(int initialState, double[] props) {
		super(initialState, props);
		super.setColors(colors);
		toBeMoved = false;

	}

	public void applyRules(CellMover cm){
		this.applyRules();
		if(toBeMoved){
			Cell replacement = cm.getRandomEmptyCell(EMPTY);
			replacement.setNextState(super.getCurrentState());
			super.setNextState(EMPTY);
		}
	}
	
	@Override
	public void applyRules() {
<<<<<<< HEAD
		if(this.getCurrentState() == EMPTY){
			toBeMoved = false;
			return;
		}
=======
>>>>>>> ece711b7c3cfd8fe60e7817f7a0f8277c209b441

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
		if(numLiveNeighbors == 0){
			toBeMoved = false;
		}
		double similarityRatio = (double) numSimilar / numLiveNeighbors;
		if(similarityRatio < super.getProps()[SIMILARITY_REQ]){
			toBeMoved = true;
		}else{
			toBeMoved = false;
		}
	}
	
	public boolean toBeMoved(){
		return toBeMoved;
	}
	public boolean isEmpty(){
		return this.getCurrentState() == EMPTY;

	}
}
