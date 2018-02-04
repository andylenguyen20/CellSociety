package cellsociety_team07;

import java.awt.Point;

public class InitialCellProperties {
	private Point locationInGrid;
	private int state;
	private double[] props;
	public InitialCellProperties(int x, int y, int state){
		this.locationInGrid = new Point(x,y);
		this.state = state;
	}
	public int getState(){
		return state;
	}
	public Point getLocation(){
		return locationInGrid;
	}
}
