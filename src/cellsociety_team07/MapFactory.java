package cellsociety_team07;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapFactory {
	public static Map<String, List<ExperimentalCell>> generateVertexMap(List<ExperimentalCell> grid){
		Map<String, List<ExperimentalCell>> vertexMap = new HashMap<>();
		for(ExperimentalCell cell : grid){
			for(Point2D.Double coordinate : cell.getVertices()){
				String vertexKey = coordinate.getX() + "," + coordinate.getY();
				if(!vertexMap.containsKey(vertexKey)){
					List<ExperimentalCell> cellsMappedToVertex = new ArrayList<ExperimentalCell>();
					cellsMappedToVertex.add(cell);
					vertexMap.put(vertexKey, cellsMappedToVertex);
				}else{
					vertexMap.get(vertexKey).add(cell);
				}
			}
		}
		return vertexMap;
	}
}
