package cellsociety_team07;

import java.awt.Dimension;

public class SimulationObjectManager {
	public static Cell getDefaultCell(String simulationType, double[] cellProps){
		switch(simulationType){
		case "Fire": 
			Cell fireCell = new FireCell();
			fireCell.setInitialAttrivutes(Cell.DEFAULT_STATE, cellProps);
			return fireCell;
		case "GameOfLife":
			Cell golCell = new GameOfLifeCell();
			golCell.setInitialAttrivutes(Cell.DEFAULT_STATE, cellProps);
			return golCell;
		case "Segregation":
			Cell segCell = new SegregationCell();
			segCell.setInitialAttrivutes(Cell.DEFAULT_STATE, cellProps);
			return segCell;
		case "Wator":
			Cell fishCell = new FishCell();
			fishCell.setInitialAttrivutes(Cell.DEFAULT_STATE, cellProps);
			return fishCell;
	default:
		throw new BadSimulationException("Bad simulation type while getting default cell");		
	}
	}
	public static Cell getSpecificCell(String cellType, int state, double[] cellProps){
		switch(cellType){
			case "Fire": 
				Cell fireCell = new FireCell();
				fireCell.setInitialAttrivutes(state, cellProps);
				return fireCell;
			case "GameOfLife":
				Cell golCell = new GameOfLifeCell();
				golCell.setInitialAttrivutes(state, cellProps);
				return golCell;
			case "Segregation":
				Cell segCell = new SegregationCell();
				segCell.setInitialAttrivutes(state, cellProps);
				return segCell;
			case "Fish":
				Cell fishCell = new FishCell();
				fishCell.setInitialAttrivutes(state, cellProps);
				return fishCell;
			case "Shark":
				Cell sharkCell = new SharkCell();
				sharkCell.setInitialAttrivutes(state, cellProps);
				return sharkCell;
		default:
			throw new BadSimulationException("Bad simulation type while getting cell");		
		}
	}
	public static Grid getSpecificGrid(String shape, Dimension gridDim, String simType){
		return GridFactory.generateRandomizedGrid(shape, gridDim, simType);
	}
}
