package cellsociety_team07;

public class CellFactory {
	public static Cell generateBlankCell(String cellType){
		switch(cellType){
		case "FireCell": 
			return new FireCell();
		case "GameOfLifeCell":
			return new GameOfLifeCell();
		case "SegregationCell":
			return new SegregationCell();
		case "WaterCell":
			return new FishCell();
		case "FishCell":
			return new FishCell();
		case "SharkCell":
			return new SharkCell();
		default:
			throw new BadSimulationException("Bad simulation type while getting blank cell");		
		}
	}
}


