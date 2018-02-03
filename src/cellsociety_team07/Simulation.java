package cellsociety_team07;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.awt.Dimension;
import java.awt.Point;
import java.util.HashMap;

public class Simulation {
	
	private SimulationXMLParser simXMLParser;
	private Grid grid;
	private double mySpeed;
	private String myTitle;
	private String myType;
	
	public Simulation(String fileName){
		simXMLParser = new SimulationXMLParser(fileName);
		myType = simXMLParser.getType();
		Dimension gridDimensions = simXMLParser.getGridDimensions();
		grid = SimulationObjectManager.getSpecificGrid(myType, gridDimensions.width, gridDimensions.height);
		mySpeed = simXMLParser.getSpeed();
		myTitle = simXMLParser.getTitle();
		setUpCells();
	}
	
	public void start(){
		KeyFrame frame = new KeyFrame(Duration.millis(mySpeed),
                e-> update());
		Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
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
