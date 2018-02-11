package cellsociety_team07;

import java.awt.Dimension;
import java.awt.geom.Point2D;
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
		myType = simXMLParser.getSimulationType();
		//this.setUpRandomizedGrid();
		this.setUpInitializedGrid();
		setUpInitializedGrid();
		//setUpCells();
	}

	public String getTitle(){
		return myTitle;
	}
	
	private void setUpRandomizedGrid(){
		Dimension gridDimensions = simXMLParser.getGridDimensions();
		String gridShape = simXMLParser.getGridShape();
		//grid = SimulationObjectManager.getSpecificGrid(gridShape, gridDimensions, myType);
		//grid = SimulationObjectManager.getSpecificGrid("Triangle", new Dimension(4,4), "GameOfLife");
	}

	private void setUpInitializedGrid(){
		Dimension gridDimensions = simXMLParser.getGridDimensions();
		List<Cell> initializedCells = simXMLParser.getInitialCells();
		grid = GridFactory.generateInitializedGrid(initializedCells, gridDimensions, myType);
		grid.setCellNeighbors();
	}



	
	public Grid getGrid(){
		return grid;
	}
	
	public List<Cell> getCells(){
		return grid.getCells();
	}
}

