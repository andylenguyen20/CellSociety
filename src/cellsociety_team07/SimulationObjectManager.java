package cellsociety_team07;

public class SimulationObjectManager {
	public static Cell getSpecificCell(String simulationType, int state, double[] cellProps){
		switch(simulationType){
			case "Fire": 
				return new FireCell(state, cellProps);
			case "GameOfLife":
				return new GameOfLifeCell(state, cellProps);
			case "Segregation":
				return new SegregationCell(state, cellProps);
			case "Wator":
				return new WatorCell(state, cellProps);
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
