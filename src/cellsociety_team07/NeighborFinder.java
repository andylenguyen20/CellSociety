package cellsociety_team07;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NeighborFinder {
	private int minSharedVertices;
	public NeighborFinder(int minSharedVertices){
		this.minSharedVertices = minSharedVertices;
	}
	
	/*
	 * Given the cell whose neighbors we want to find and given the map of vertices linked to all cells in the grid,
	 * returns a list of cell neighbors for the given cell.
	 */
	public List<ExperimentalCell> findNeighbors(ExperimentalCell cell, Map<String, ExperimentalCell> verticeMap){
		List<Double> cellVertices = cell.getVertices();
		Map<ExperimentalCell, Integer> numSharedVertices = new HashMap<ExperimentalCell, Integer>();
		for(Double vertex : cellVertices){
			ExperimentalCell neigh = verticeMap.get(vertex.getX() + "," + vertex.getY());
			if(!numSharedVertices.containsKey(neigh)){
				numSharedVertices.put(neigh,  1);
			}else{
				numSharedVertices.put(neigh,  numSharedVertices.get(neigh) + 1);
			}
		}
		return this.getValidNeighbors(numSharedVertices);
	}
	
	protected List<ExperimentalCell> getValidNeighbors(Map<ExperimentalCell, Integer> numSharedVertices){
		List<ExperimentalCell> neighborList = new ArrayList<>();
		for(ExperimentalCell potentialNeighbor : numSharedVertices.keySet()){
			if(numSharedVertices.get(potentialNeighbor) >= minSharedVertices){
				neighborList.add(potentialNeighbor);
			}
		}
		return neighborList;
	}
}
