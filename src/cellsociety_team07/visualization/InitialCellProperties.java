package cellsociety_team07.visualization;

import java.awt.Point;

public class InitialCellProperties {
	private Point locationInGrid;
	private int state;
	private double[] props;
	private String type;
	public InitialCellProperties(int x, int y, int state, String type){
		this.locationInGrid = new Point(x,y);
		this.state = state;
		this.type = type;
	}
	public int getState(){
		return state;
	}
	public Point getLocation(){
		return locationInGrid;
	}
	public String getCellType(){
		return type;
	}
}
