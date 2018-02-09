package cellsociety_team07;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
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
	public SimulationXMLParser(String fileName) throws FileNotFoundException{
		document = readInFile(fileName);
	}
	/*
	 * getDocumentElement()
	 * taken from https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
	 */
	private Document readInFile(String fileName) throws FileNotFoundException{
		Document doc = null;
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			throw new FileNotFoundException();
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
	public List<InitialCellProperties> getInitialCellInfo(){
		List<InitialCellProperties> initialCellPropList = new ArrayList<InitialCellProperties>();
		NodeList cellTags = document.getElementsByTagName("cell");
		for(int i = 0; i < cellTags.getLength(); i++){
			Element cell = (Element) cellTags.item(i);
			int row = this.getTagValue(cell, "row");
			int col = this.getTagValue(cell, "col");
			int state = this.getTagValue(cell, "state");
			String cellType = this.getCellType(cell);
			initialCellPropList.add(new InitialCellProperties(row, col, state, cellType));
		}
		return initialCellPropList;
	}
	public String getCellType(Element cell){
		return cell.getAttribute("type");
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
