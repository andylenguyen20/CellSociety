package cellsociety_team07;

import java.awt.Dimension;
import java.util.List;

public class CellFactory {

	public static Cell generateBlankCell(String simType){
		switch(simType){
		case "FireCell":
			return new FireCell();
		case "GameOfLifeCell":
			return new GameOfLifeCell();
		case "SegregationCell":
			return new SegregationCell();
		case "WatorCell":
			return new FishCell();
		default:
			throw new BadSimulationException("Bad simulation type while getting default cell");		
		}
	}
	private static void initializeCellAttributes(Cell cell){
		/*
		int randomState = (int) (Math.random() * numStates);
		cell.setInitialAttributes(randomState, params);
		*/
	}
}

