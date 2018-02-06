package cellsociety_team07;
import javafx.scene.shape.Rectangle;

import javafx.scene.paint.*;

public class GameOfLifeCell extends Cell{
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	public static final Paint[] colors = {Color.BLACK, Color.AQUA};
	
	public GameOfLifeCell(int state, double[] props) {
		super(state, props);
		super.setColors(colors);
	}

	@Override
	public void update() {
		super.setCurrentState(super.getNextState());	
	}
	
	public void applyRules(){
		int alive = 0;
		for (Cell neigh:super.getNeighbors()) {
			alive += neigh.getCurrentState();
		}
		if(alive >= 2 && alive <4 && this.getCurrentState() == ALIVE){
			super.setNextState(ALIVE);
		}else if(alive == 3 && this.getCurrentState() == DEAD){
			super.setNextState(ALIVE);
		}else{
			super.setNextState(DEAD);
		}
	}

}
