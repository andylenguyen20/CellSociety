package cellsociety_team07.config;

import java.awt.Dimension;

import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.FireCell;
import cellsociety_team07.simulation.GameOfLifeCell;
import cellsociety_team07.simulation.Grid;
import cellsociety_team07.simulation.SegregationCell;
import cellsociety_team07.simulation.WatorCell;

public class SimulationObjectManager {
	public static Cell getDefaultCell(String simulationType, double[] cellProps){
		switch(simulationType){
		case "Fire": 
			Cell fireCell = new FireCell();
			fireCell.setInitialAttributes(Cell.DEFAULT_STATE, cellProps);
			return fireCell;
		case "GameOfLife":
			Cell golCell = new GameOfLifeCell();
			golCell.setInitialAttributes(Cell.DEFAULT_STATE, cellProps);
			return golCell;
		case "Segregation":
			Cell segCell = new SegregationCell();
			segCell.setInitialAttributes(Cell.DEFAULT_STATE, cellProps);
			return segCell;
		case "Wator":
			Cell watorCell = new WatorCell();
			watorCell.setInitialAttributes(Cell.DEFAULT_STATE, cellProps);
			return watorCell;
	default:
		throw new BadSimulationException("Bad simulation type while getting default cell");		
	}
	}
	public static Cell getSpecificCell(String cellType, int state, double[] cellProps){
		switch(cellType){
			case "Fire": 
				Cell fireCell = new FireCell();
				fireCell.setInitialAttributes(state, cellProps);
				return fireCell;
			case "GameOfLife":
				Cell golCell = new GameOfLifeCell();
				golCell.setInitialAttributes(state, cellProps);
				return golCell;
			case "Segregation":
				Cell segCell = new SegregationCell();
				segCell.setInitialAttributes(state, cellProps);
				return segCell;
			case "Wator":
				Cell watorCell = new WatorCell();
				watorCell.setInitialAttributes(state, cellProps);
				return watorCell;
		default:
			throw new BadSimulationException("Bad simulation type while getting cell");		
		}
	}
	public static Grid getSpecificGrid(String shape, Dimension gridDim, String simType){
		return GridFactory.generateRandomizedGrid(shape, gridDim, simType);
	}
}


