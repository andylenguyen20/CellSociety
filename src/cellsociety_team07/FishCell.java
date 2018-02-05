package cellsociety_team07;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class FishCell extends WatorCell{
	public static final int WATER = 0; // an empty cell
	public static final int FISH = 1;
	public static final Paint[] colors = {Color.CYAN}; // Array of Col
	private boolean toBeMoved;
	public static final int REPRODUCTION_CHRONON = 0;
	
	public FishCell(int initialState, double[] props) {
		 
		super(initialState, props);
		super.setColors(colors);
		toBeMoved = false;
		
	}
	
	public void applyRules(CellMover cm){
		this.applyRules();
		if(toBeMoved){
			Cell replacement = cm.getRandomEmptyCell(WATER);
			replacement.setNextState(super.getCurrentState());
			super.setNextState(WATER);
			//super.getProps()[0];
		}
	}
	
	@Override
	public void applyRules() {
		
		int numLiveNeighbors = 0;
		
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() != WATER){
				numLiveNeighbors++;
			}
		}
		if(numLiveNeighbors == 4)
			toBeMoved = false;
		else
			toBeMoved = true;
		
	}
	
	public boolean toBeMoved(){
		return toBeMoved;
	}

}
