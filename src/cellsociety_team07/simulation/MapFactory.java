package cellsociety_team07.simulation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The purpose of this class is to act as a factory for the vertexMap private instance variable of all
 * Grids. This vertexMap allows the Grid to find which vertice on the Grid is linked to a particular list
 * of cells in the grid.
 * @author Andy Nguyen
 *
 */
public class MapFactory {
	/**
	 * generates a map of grid coordinate system vertices to the cells that exist on the grid
	 * @param grid	the grid whose map we want to generate
	 * @return		the map of grid coordinate system vertices to the cells of the grid
	 */
	public static Map<String, List<Cell>> generateVertexMap(List<Cell> grid) {
		Map<String, List<Cell>> vertexMap = new HashMap<>();
		for (Cell cell : grid) {
			for (Point2D.Double coordinate : cell.getVertices()) {
				String vertexKey = coordinate.getX() + "," + coordinate.getY();
				if (!vertexMap.containsKey(vertexKey)) {
					List<Cell> cellsMappedToVertex = new ArrayList<Cell>();
					cellsMappedToVertex.add(cell);
					vertexMap.put(vertexKey, cellsMappedToVertex);
				} else {
					vertexMap.get(vertexKey).add(cell);
				}
			}
		}
		return vertexMap;
	}
}