package cellsociety_team07;

import java.awt.Dimension;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

public class Simulation {
	
	private SimulationXMLParser simXMLParser;
	private Grid grid;
	private String myTitle;
	private String myType;
	
	
	public Simulation(String fileName) throws FileNotFoundException{
		try {
			simXMLParser = new SimulationXMLParser(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			throw new FileNotFoundException();
		}
		myTitle = simXMLParser.getTitle();
		myType = simXMLParser.getType();
		setUpGrid();
		setUpCells();
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
		Cell[][] cells = grid.getCells();
		for(InitialCellProperties initProps : initialCellPropList){
			int row = initProps.getLocation().x;
			int col = initProps.getLocation().y;
			int state = initProps.getState();
			String cellType = initProps.getCellType();
			cells[row][col] = SimulationObjectManager.getSpecificCell(cellType, state, simulationParams);
		}
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				if(cells[i][j] == null){
					cells[i][j] = SimulationObjectManager.getDefaultCell(myType, simulationParams);
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
