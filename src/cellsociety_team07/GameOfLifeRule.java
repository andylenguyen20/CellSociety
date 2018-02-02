package cellsociety_team07;

import java.util.ArrayList;

public class GameOfLifeRule implements Rule{
	public static final int DEAD = 0;
	public static final int ALIVE = 1;
	@Override
	public int getNextState(ArrayList<Cell> neighbors, int currState) {
		if(neighbors.size() > 1 && neighbors.size() < 3){
			return ALIVE;
		}else{
			return DEAD;
		}
	}

}
