package cellsociety_team07;


import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

public class Simulation {
	
	private SimulationXMLParser simXMLParser;
	private Grid grid;
	private String myTitle;
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private int rows = 10;
	private int columns = 10;
	double gridWidth = sceneWidth / rows;
	double gridHeight = sceneHeight / columns;

	private String myType;
	
	public Simulation(String fileName){
		simXMLParser = new SimulationXMLParser(fileName);
		myType = simXMLParser.getType();
		Dimension gridDimensions = simXMLParser.getGridDimensions();
		grid = SimulationObjectManager.getSpecificGrid(myType, gridDimensions.width, gridDimensions.height);
		myTitle = simXMLParser.getTitle();
		setUpCells();
	}

	public String getTitle(){
		return myTitle;
	}

	private void setUpCells(){
		HashMap<Point, Integer> initialCellInfo = simXMLParser.getInitialCellInfo();
		double[] cellParams = simXMLParser.getSimulationParams();
		Cell[][] cells = grid.getCells();
		for(Point location : initialCellInfo.keySet()){
			int state = initialCellInfo.get(location);
			cells[location.x][location.y] = SimulationObjectManager.getSpecificCell(myType, state, cellParams);
		}
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				if(cells[i][j] == null){
					cells[i][j] = SimulationObjectManager.getSpecificCell(myType, Cell.DEFAULT_STATE, cellParams);
				}
			}
		}
		grid.setCellNeighbors();
	}
	
	public Grid getGrid(){
		return grid;
	}
	
	public Cell[][] getCells(){
		return grid.getCells();
	}
}
