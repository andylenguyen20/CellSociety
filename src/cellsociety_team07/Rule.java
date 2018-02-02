package cellsociety_team07;

import java.util.ArrayList;

public interface Rule {
	int getNextState(ArrayList<Cell> neighbors, int currState);
}
