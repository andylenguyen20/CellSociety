package cellsociety_team07;

import java.awt.geom.Point2D.Double;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class ExperimentalCell extends Polygon{
	private List<Double> vertices;
	public ExperimentalCell(){
		this.setFill(Color.RED);
		this.setStroke(Color.WHITE);
	}
	
	public void setVertices(List<Double> vertices){
		this.vertices = vertices;
	}
	public List<Double> getVertices(){
		return vertices;
	}
}
