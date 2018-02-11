package cellsociety_team07;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SimulationXMLParser {
	private Document document;
	public SimulationXMLParser(String fileName){
		document = readInFile(fileName);
	}
	/*
	 * getDocumentElement()
	 * taken from https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	 */
	private Document readInFile(String fileName){
		Document doc = null;
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc;
	}
	
	public Dimension getGridDimensions(){
		Element gridTag = (Element) document.getElementsByTagName("grid").item(0);
		Element dimensions = (Element) gridTag.getElementsByTagName("dimension").item(0);
		int width = this.getTagValue(dimensions, "width");
		int height = this.getTagValue(dimensions, "height");
		return new Dimension(width, height);
	}
	public int getSpeed(){
		Element simulation = (Element) document.getElementsByTagName("simulation").item(0);
		return this.getTagValue(simulation, "speed");
	}
	
	
	public String getTitle(){
		Element title = (Element) document.getElementsByTagName("title").item(0);
		return title.getTextContent();
	}
	public List<Cell> getInitialCells(String type){
		List<Cell> initialCells = new ArrayList<Cell>();
		NodeList cellTags = document.getElementsByTagName("cell");
		for(int i = 0; i < cellTags.getLength(); i++){
			Element cellTag = (Element) cellTags.item(i);
			String cellType = this.getCellType(cellTag);
			Cell cell  = CellFactory.generateBlankCell(cellType);
			cell.setVertices(this.getPoints(cellTag));
			int state = this.getTagValue(cellTag, "state");
			double[] params = this.getSimulationParams();
			cell.setInitialAttributes(state, params);
			initialCells.add(cell);
		}
		return initialCells;
	}
	
	public List<Point2D.Double> getPoints(Element cell){
		List<Point2D.Double> vertices = new ArrayList<>();
		NodeList cellTags = cell.getElementsByTagName("point");
		for(int i = 0; i < cellTags.getLength(); i++){
			String[] coords = cellTags.item(i).getTextContent().split(",");
			Point2D.Double vertex = new Point2D.Double(Double.parseDouble(coords[0]), Double.parseDouble(coords[1]));
			vertices.add(vertex);
		}
		return vertices;
	}
	
	public String getCellType(Element cell){
		return cell.getAttribute("type");
	}
	public String getGridShape(){
		Element gridTag = (Element) document.getElementsByTagName("shape").item(0);
		return gridTag.getTextContent();
	}
	
	public String getType(){
		Element type = (Element) document.getElementsByTagName("type").item(0);
		return type.getTextContent();
	}
	
	public double[] getSimulationParams(){
		NodeList parameters = document.getElementsByTagName("param");
		double[] params = new double[parameters.getLength()];
		for(int i = 0; i < params.length; i++){
			params[i] = Double.parseDouble(parameters.item(i).getTextContent());
		}
		return params;
	}
	
	private int getTagValue(Element directParent, String tagName){
		return Integer.parseInt(directParent.getElementsByTagName(tagName).item(0).getTextContent());
	}
}
