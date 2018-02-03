package cellsociety_team07;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.awt.Point;
import java.util.HashMap;

public class Simulation {
	
	private SimulationXMLParser simXMLParser;
	private Grid grid;
	private double mySpeed;
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
		grid = new Grid(simXMLParser.getGridDimensions().width, simXMLParser.getGridDimensions().height);
		mySpeed = simXMLParser.getSpeed();
		myTitle = simXMLParser.getTitle();
		myType = simXMLParser.getType();
		setUpCells();
	}

	private void update(){
		System.out.println("updating");
	}
	
	public String getTitle(){
		return myTitle;
	}

	private void setUpCells(){
		HashMap<Point, Integer> initialCellInfo = simXMLParser.getInitialCellInfo();
		Cell[][] cells = grid.getCells();
		for(Point point : initialCellInfo.keySet()){
			int state = initialCellInfo.get(point);
			cells[point.x][point.y] = new GameOfLifeCell(state);
			
			cells[point.x][point.y] = SimulationObjectManager.getSpecificCell(myTitle, state);
		}
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				if(cells[i][j] == null){
					cells[i][j] = SimulationObjectManager.getSpecificCell(myTitle, Cell.DEFAULT_STATE);
				}
			}
		}
	}
	
	public Cell[][] getCells(){
		return grid.getCells();
	}
}
