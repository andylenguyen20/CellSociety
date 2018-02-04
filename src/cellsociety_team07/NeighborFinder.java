package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;

public class NeighborFinder {
	private Point[] offsetIgnore;
	public NeighborFinder(Point[] offsetIgnore){
		this.offsetIgnore = offsetIgnore;
	}
	public ArrayList<Cell> getCellNeighborhood(Cell[][] cells, int row, int col){
		ArrayList<Cell> neighbors = new ArrayList<Cell>();
		for(int rowOffset = -1; rowOffset <= 1; rowOffset++){
			for(int colOffset = -1; colOffset <= 1; colOffset++){
				int neighRow = row + rowOffset;
				int neighCol = col + colOffset;
				if(ignorableOffset(rowOffset, colOffset) || outOfBounds(cells, neighRow, neighCol)){
					continue;
				}
				neighbors.add(cells[neighRow][neighCol]);
			}
		}
		return neighbors;
	}
	private boolean outOfBounds(Cell[][] cells, int row, int col){
		if(row >= cells.length || row < 0 || col >= cells[0].length || col < 0){
			return true;
		}
		return false;
	}
	private boolean ignorableOffset(int rowOffset, int colOffset){
		for(Point p : offsetIgnore){
			if(p.x == rowOffset && p.y == colOffset){
				return true;
			}
		}
		return false;
	}
}
