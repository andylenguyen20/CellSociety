package cellsociety_team07;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Simulation {
	
	private SimulationXMLParser simXMLParser;
	private Grid grid;
	private String myTitle;
	private String myType;
	
	
	public Simulation(String fileName){
		simXMLParser = new SimulationXMLParser(fileName);
		myTitle = simXMLParser.getTitle();
		myType = simXMLParser.getType();
		//setUpGrid();
		//setUpCells();
	}

	public String getTitle(){
		return myTitle;
	}
	
	private void setUpGrid(){
		Dimension gridDimensions = simXMLParser.getGridDimensions();
		grid = SimulationObjectManager.getSpecificGrid(myType, gridDimensions.width, gridDimensions.height);
	}

	private void setUpCells(){
		List<InitialCellProperties> initialCellPropList = simXMLParser.getInitialCellInfo();
		double[] simulationParams = simXMLParser.getSimulationParams();
		List<Cell> cells = grid.getCells();
		for(InitialCellProperties initProps : initialCellPropList){
			int row = initProps.getLocation().x;
			int col = initProps.getLocation().y;
			int state = initProps.getState();
			String cellType = initProps.getCellType();
			//cells[row][col] = SimulationObjectManager.getSpecificCell(cellType, state, simulationParams);
		}
		/*for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				if(cells[i][j] == null){
					cells[i][j] = SimulationObjectManager.getDefaultCell(myType, simulationParams);
				}
			}
		}
		*/
		grid.setCellNeighbors();
	}

	
	public Grid getGrid(){
		return grid;
	}
	
	public List<Cell> getCells(){
		Grid grid = GridFactory.generateGrid("Rectangle", new Dimension(20,20), "GameOfLife");
		return grid.getCells();
	}
}
