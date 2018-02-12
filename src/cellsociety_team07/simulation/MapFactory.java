package cellsociety_team07.simulation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFactory {
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
