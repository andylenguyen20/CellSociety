package cellsociety_team07.simulation;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class SegregationCell extends Cell{
	public static final int EMPTY = 0;
	public static final int RACE1 = 1;
	public static final int RACE2 = 2;
	public static final int SIMILARITY_REQ = 0;
	private boolean toBeMoved;
	public static final Paint[] colors = {Color.AZURE, Color.RED, Color.BLUE};
	
	/**
	 * instantiates a new SegregationCell
	 */
	public SegregationCell() {
		super();
		super.setColors(colors);
		toBeMoved = false;
	}

	/**
	 * applies the rules of a segregation cell. If the cell decides to move to a new location,
	 * it asks the CellFetcher to give it an empty cell that it can move to
	 * @param cm
	 */
	public void applyRules(CellFetcher cm){
		this.applyRules();
		if(toBeMoved){
			Cell replacement = cm.getCellOfType(EMPTY, this);
			replacement.setNextState(super.getCurrentState());
			super.setNextState(EMPTY);
		}
	}
	
	/**
	 * applies the rules of a segregation cell according to the Segregation simulation online rules
	 */
	@Override
	public void applyRules() {
		if(this.getCurrentState() == EMPTY){
			toBeMoved = false;
			return;
		}
		int numLiveNeighbors = this.getNumLiveNeighbors();
		if(this.getNumLiveNeighbors() == 0){
			toBeMoved = false;
			return;
		}
		int numSimilarNeighbors = this.getNumSimilarNeighbors();
		double similarityRatio = (double) numSimilarNeighbors / numLiveNeighbors;
		if(similarityRatio < super.getParams()[SIMILARITY_REQ]){
			toBeMoved = true;
		}else{
			toBeMoved = false;
		}
	}

	private int getNumSimilarNeighbors(){
		int numSimilar = 0;
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() == this.getCurrentState()){
				numSimilar++;
			}
		}
		return numSimilar;
	}
	
	private int getNumLiveNeighbors(){
		int numLiveNeighbors = 0;
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() != EMPTY){
				numLiveNeighbors++;
			}
		}
		return numLiveNeighbors;
	}
}
