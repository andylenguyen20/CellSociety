package cellsociety_team07;

public class SimulationObjectManager {
	public static Cell getDefaultCell(String simulationType, double[] cellProps){
		switch(simulationType){
		case "Fire": 
			return new FireCell(Cell.DEFAULT_STATE, cellProps);
		case "GameOfLife":
			return new GameOfLifeCell(Cell.DEFAULT_STATE, cellProps);
		case "Segregation":
			return new SegregationCell(Cell.DEFAULT_STATE, cellProps);
		case "Wator":
			return new FishCell(Cell.DEFAULT_STATE, cellProps);
	default:
		throw new BadSimulationException("Bad simulation type while getting default cell");		
	}
	}
	public static Cell getSpecificCell(String cellType, int state, double[] cellProps){
		switch(cellType){
			case "Fire": 
				return new FireCell(state, cellProps);
			case "GameOfLife":
				return new GameOfLifeCell(state, cellProps);
			case "Segregation":
				return new SegregationCell(state, cellProps);
			case "Fish":
				return new FishCell(state, cellProps);
			case "Shark":
				return new SharkCell(state, cellProps);
		default:
			throw new BadSimulationException("Bad simulation type while getting cell");		
		}
	}
	public static Grid getSpecificGrid(String simulationType, int width, int height){
		switch(simulationType){
			case "Fire": 
				return new FireGrid(width, height);
			case "GameOfLife":
				return new GameOfLifeGrid(width, height);
			case "Segregation":
				return new SegregationGrid(width, height);
			case "Wator":
				return new WatorGrid(width, height);
		default:
			throw new BadSimulationException("Bad simulation type while getting grid");		
		}
	}
}
