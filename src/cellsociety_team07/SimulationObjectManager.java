package cellsociety_team07;

public class SimulationObjectManager {
	public static Cell getSpecificCell(String simulationType, int initialState){
		switch(simulationType){
			case "Fire": 
				return new FireCell(initialState);
			case "GameOfLife":
				return new GameOfLifeCell(initialState);
			case "Segregation":
				return new SegregationCell(initialState);
			case "Wator":
				return new WatorCell(initialState);
		default:
			throw new BadSimulationException("Bad simulation type");		
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
			throw new BadSimulationException("Bad simulation type");		
		}
	}
}
