package cellsociety_team07;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.io.File;

public class Simulation {
	private XMLParser xmlParser;
	private double mySpeed;
	private String myTitle;
	private Grid grid;
	public Simulation(String fileName){
		xmlParser = new XMLParser(fileName);
		initializeComponents(fileName);
		start();
	}
	
	public void start(){
		System.out.println(mySpeed);
		KeyFrame frame = new KeyFrame(Duration.millis(mySpeed),
                e-> update());
		/*Timeline timeline = new Timeline(frame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();*/
		System.out.println(frame.getTime().toSeconds());
        Timeline animation = new Timeline();
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
	}
	
	private void update(){
		System.out.println("hi");
	}
	public String getTitle(){
		return myTitle;
	}
	public void setSpeed(int speed){
		mySpeed = speed;
	}
	private Grid setUpGrid(Document document){
		Element gridTag = (Element) document.getElementsByTagName("grid").item(0);
		int width = Integer.parseInt(gridTag.getAttribute("width"));
		int height = Integer.parseInt(gridTag.getAttribute("height"));
		return new Grid(width, height);
	}
	//getDocumentElement()
	/*
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
	private void initializeComponents(String fileName){
		Document document = readInFile(fileName);
		grid = setUpGrid(document);
		mySpeed = Integer.parseInt(document.getElementsByTagName("sim_speed").item(0).getTextContent());
	}
}
