package cellsociety_team07.simulation;
 
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
/**
 * The purpose of this class is to find neighbors for all cells in the grid in a bounded
 * grid type. This class works for all shape types and should be closed for modification because it
 * is a universal way of finding neighbors.
 * @author Andy Nguyen
 *
 */
public class NeighborFinder {
    public static final List<Integer> TOUCHING_NEIGHBORS = new ArrayList<>(Arrays.asList(1,2));
    public static final List<Integer> ADJACENT_NEIGHBORS = new ArrayList<>(Arrays.asList(2));
    public static final List<Integer> DIAGONAL_NEIGHBORS = new ArrayList<>(Arrays.asList(1));
    private List<Integer> allowedNumSharedVertices;
    
    /**
     * Initializes a new NeighborFinder object that has a given neighbor type: adjacent, touching, or diagonal
     * @param allowedNumSharedVertices
     */
    public NeighborFinder(List<Integer> allowedNumSharedVertices){
        this.allowedNumSharedVertices = allowedNumSharedVertices;
    }
   
    /**
     * Finds the neighbors for a given cell using the grid's vertexMap. This works for all shape types.
     * @param cell
     * @param verticeMap
     * @return
     */
    public List<Cell> findNeighbors(Cell cell, Map<String, List<Cell>> verticeMap){
        List<Double> cellVertices = cell.getVertices();
        Map<Cell, Integer> numSharedVertices = new HashMap<Cell, Integer>();
        for(Double vertex : cellVertices){
            List<Cell> potentialNeighList = verticeMap.get(vertex.getX() + "," + vertex.getY());
            for(Cell neigh : potentialNeighList){
                if(!numSharedVertices.containsKey(neigh)){
                    numSharedVertices.put(neigh,  1);
                }else{
                    numSharedVertices.put(neigh,  numSharedVertices.get(neigh) + 1);
                }
            }
        }
        return this.getValidNeighbors(numSharedVertices);
    }

    protected List<Cell> getValidNeighbors(Map<Cell, Integer> numSharedVertices){
        List<Cell> neighborList = new ArrayList<>();
        for(Cell potentialNeighbor : numSharedVertices.keySet()){
            if(allowedNumSharedVertices.contains(numSharedVertices.get(potentialNeighbor))){
                neighborList.add(potentialNeighbor);
            }
        }
        return neighborList;
    }
}