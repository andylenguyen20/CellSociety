package cellsociety_team07;
import javafx.scene.paint.*;

public class GameOfLifeCell extends Cell{
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	public final Paint[] colors = {Color.BLACK, Color.GREEN};
	
	public GameOfLifeCell(int initialState) {
		super(initialState);
	}

	public void applyRules(){
		int alive = 0;
		for (Cell neigh:super.getNeighbors()) {
			alive += neigh.getCurrentState();
		}
		if(alive >= 1 && alive <3){
			super.setNextState(ALIVE);
		}else{
			super.setNextState(DEAD);
		}
	}
}
