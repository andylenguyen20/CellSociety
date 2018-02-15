package cellsociety_team07.config;

import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.FireCell;
import cellsociety_team07.simulation.GameOfLifeCell;
import cellsociety_team07.simulation.SegregationCell;
import cellsociety_team07.simulation.WatorCell;

/**
 * The purpose of this class is to have a static way of generating a single cell that is uninitialized but
 * is of a specific cell type of interest
 * @author Andy Nguyen
 *
 */
public class CellFactory {
	public static Cell generateBlankCell(String cellType){
		switch(cellType){
		case "FireCell":
			return new FireCell();
		case "GameOfLifeCell":
			return new GameOfLifeCell();
		case "SegregationCell":
			return new SegregationCell();
		case "WatorCell":
			return new WatorCell();

		default:
			throw new BadSimulationException("Bad simulation type while getting blank cell");		
		}
	}
}



