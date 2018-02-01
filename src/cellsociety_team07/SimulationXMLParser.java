package cellsociety_team07;

import java.awt.Dimension;
import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SimulationXMLParser {
	private Document document;
	public SimulationXMLParser(String fileName){
		document = readInFile(fileName);
	}
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
		Element dimensions = (Element) gridTag.getElementsByTagName("dimension");
		int width = Integer.parseInt(dimensions.getElementsByTagName("width").item(0).getTextContent());
		int height = Integer.parseInt(dimensions.getElementsByTagName("height").item(0).getTextContent());
		return new Dimension(width, height);
	}
	public int getSpeed(String attributeName){
		Element root = (Element) document.getElementsByTagName("simulation").item(0);
		return Integer.parseInt(root.getAttribute("speed"));
	}
}
