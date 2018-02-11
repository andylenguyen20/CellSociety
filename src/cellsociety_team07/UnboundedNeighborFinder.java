package cellsociety_team07;

import java.awt.Dimension;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnboundedNeighborFinder extends NeighborFinder {
	private double numRows;
	private double numCols;
	public UnboundedNeighborFinder(List<Integer> allowedNumSharedVertices, Dimension gridDimensions) {
		super(allowedNumSharedVertices);
		this.numRows = gridDimensions.getHeight();
		this.numCols = gridDimensions.getWidth();
	}

	/*
	 * Given the cell whose neighbors we want to find and given the map of vertices linked to all cells in the grid,
	 * returns a list of cell neighbors for the given cell, including neighbors that wrap around the grid.
	 */
	@Override
	public List<Cell> findNeighbors(Cell cell, Map<String, List<Cell>> vertexMap){
		List<Cell> neighborList = super.findNeighbors(cell, vertexMap);
		Map<Cell, Integer> numSharedVertices = new HashMap<>();
		List<String> wrapAroundKeys = this.getWrapAroundKeys(cell);
		for(String wrapAroundKey : wrapAroundKeys){
			List<Cell> potentialNeighborList = vertexMap.get(wrapAroundKey);
			System.out.println(cell.getVertices().toString());
			for(Cell potentialNeighbor : potentialNeighborList){
				if(!numSharedVertices.containsKey(potentialNeighbor)){
					numSharedVertices.put(potentialNeighbor, 1);
				}else{
					numSharedVertices.put(potentialNeighbor, numSharedVertices.get(potentialNeighbor) + 1);
				}
			}
		}
		neighborList.addAll(super.getValidNeighbors(numSharedVertices));
		return neighborList;
	}
	
	private List<String> getWrapAroundKeys(Cell cell){
		List<String> wrapAroundKeys = new ArrayList<>();
		List<Double> cellVertices = cell.getVertices();
		for(Double vertice : cellVertices){
			if(vertice.getX() == 0){
				wrapAroundKeys.add(numCols + "," + vertice.getY());
			}
			if(vertice.getY() == 0){
				wrapAroundKeys.add(vertice.getX() + "," + numRows);
			}
			if(vertice.getX() == numCols){
				wrapAroundKeys.add(0.0 + "," + vertice.getY());
			}
			if(vertice.getY() == numRows){
				wrapAroundKeys.add(vertice.getX() + "," + 0.0);
			}
		}
		return wrapAroundKeys;
	}
}