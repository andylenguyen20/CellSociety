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
	
	public Simulation(String fileName){
		simXMLParser = new SimulationXMLParser(fileName);
		//grid = new Grid(simXMLParser.getGridDimensions().width, simXMLParser.getGridDimensions().height);
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
			cells[point.x][point.y] = new GameOfLifeCell(state);
		}
		for(int i = 0; i < cells.length; i++){
			for(int j = 0; j < cells[i].length; j++){
				if(cells[i][j] == null){
					cells[i][j] = new GameOfLifeCell(Cell.DEFAULT_STATE);
				}
			}
		}
	}
}
