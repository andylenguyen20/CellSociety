package cellsociety_team07;

import java.awt.Point;
import java.util.ArrayList;

public class WatorGrid extends Grid implements CellMover{
	public static final Point[] NEIGHBOR_OFFSETS = new Point[]{new Point(-1,-1), new Point(-1,1), new Point(1,-1), new Point(1,1)};
	public WatorGrid(int width, int height){
		super(width, height);
	}
	@Override
	public void setCellNeighbors() {
		// TODO Auto-generated method stub
	}
	@Override
	public Cell getRandomEmptyCell(int desiredState, Cell cell) {
		ArrayList<Cell> potentialCells = new ArrayList<Cell>();
		for(Cell neighbor : cell.getNeighbors()){
			if(neighbor.getCurrentState() == desiredState){
				potentialCells.add(neighbor);
			}
		}
		return potentialCells.get((int) (Math.random() * potentialCells.size()));
	}
	@Override
	public void moveCellInGrid(Cell movingCell, Cell movingCellReplacement, Cell toBeOverwritten) {
		Cell[][] cells = super.getCells();
		for(int row = 0; row < cells.length; row++){
			for(int col = 0; col < cells[0].length; col++){
				if(cells[row][col] == toBeOverwritten){
					cells[row][col] = movingCell;
				}
				if(cells[row][col] == movingCell){
					cells[row][col] = movingCellReplacement;
				}
			}
		}
	}
}
