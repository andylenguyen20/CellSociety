package cellsociety_team07;

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
			return new WatorCell();

		default:
			throw new BadSimulationException("Bad simulation type while getting blank cell");		
		}
	}
}



