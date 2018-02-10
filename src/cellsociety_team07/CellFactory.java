package cellsociety_team07;

import java.awt.Dimension;
import java.util.List;

public class CellFactory {
	private static Cell generateRandomizedCell(String simType){
		switch(simType){
		case "Fire": 
			return new FireCell();
		case "GameOfLife":
			return new GameOfLifeCell();
		case "Segregation":
			return new SegregationCell();
		case "Wator":
			return new FishCell();
		default:
			throw new BadSimulationException("Bad simulation type while getting default cell");		
		}
	}
	
	public static Cell generateBlankCell(String simType){
		switch(simType){
		case "Fire": 
			return new FireCell();
		case "GameOfLife":
			return new GameOfLifeCell();
		case "Segregation":
			return new SegregationCell();
		case "Wator":
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


