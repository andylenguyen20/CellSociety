package cellsociety_team07.config;

import java.awt.Dimension;
import java.util.List;

import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.Grid;

/**
 * The purpose of this class is to house a given Simulation's configurations given a file that it can read from.
 * This simulation class houses the grid and also holds other attributes such as an XMLParser, title, and simulation type.
 * @author Andy Nguyen
 *
 */
public class Simulation {
	private SimulationXMLParser simXMLParser;
	private Grid grid;
	private String myTitle;
	private String myType;
	
	/**
	 * initializes a new instance of this Simulation given a simulation file to read from
	 * @param fileName
	 */
	public Simulation(String fileName){
		simXMLParser = new SimulationXMLParser(fileName);
		myTitle = simXMLParser.getTitle();
		myType = simXMLParser.getSimulationType();
		this.setUpInitializedGrid();
		setUpInitializedGrid();
	}

	/**
	 * 
	 * @return	the title of this simulation
	 */
	public String getTitle(){
		return myTitle;
	}
	/**
	 * 
	 * @return	the type of this simulation
	 */
	public String getType() {
		return myType;
	}

	/**
	 * sets up grid given an initialized dimension and initialized cells read from the XML parser
	 */
	private void setUpInitializedGrid(){
		Dimension gridDimensions = simXMLParser.getGridDimensions();
		List<Cell> initializedCells = simXMLParser.getInitialCells();
		grid = GridFactory.generateInitializedGrid(initializedCells, gridDimensions, myType);
		grid.setCellNeighbors();
	}

	/**
	 * saves the current state of this simulation into an XML file
	 */
	public void saveCurrentState() {
		XMLWriterFactory.saveSimData(grid, myType);
	}
	
	/**
	 * 
	 * @return	the grid that this simulation holds
	 */
	public Grid getGrid(){
		return grid;
	}
	
	/**
	 * 
	 * @return	the cells that this simulation's grid holds
	 */
	public List<Cell> getCells(){
		return grid.getCells();
	}
}

