package cellsociety_team07;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XMLParser {
	private Element root;
	public XMLParser(String fileName){
		root = readInFile(fileName);
	}
	private Element readInFile(String fileName){
		Document doc = null;
		try {
			File fXmlFile = new File(fileName);
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doc.getDocumentElement();
	}
	
}
