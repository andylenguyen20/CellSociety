package cellsociety_team07;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

public class XMLWriterFactory {
	
	/********* Tags in XML file *******************/
	public static final String SIM = "simulation";
	public static final String TYPE = "type";
	public static final String TITLE = "title";
	public static final String AUTHOR = "author";
	public static final String GRID = "grid";
	public static final String DIMENSION = "dimension";
	public static final String WIDTH = "width";
	public static final String HEIGHT = "height";
	public static final String CELL = "cell";
	public static final String POINT = "point";
	public static final String STATE = "state";
	
	/*
	 * Static class that writes current state of Simulation to an XML document. Methods are called in the
	 * Simulation class upon button click in the UI, so parameters are based on fields that are
	 * accessible within Simulation.java
	 */
	
	
	/**
	 * Loops through List of Cells and saves their current state and locations (based on vertices) to an XML
	 * document. Also saves simulation type and grid dimensions.
	 * 
	 * @param cells: List of Cells to be saved and read
	 * @param simType: String containing type of simulation
	 * @param simTitle: title of simulation
	 */
	public static void getSimData(List<Cell> cells, String simType, String simTitle) {
		String fileName = simType; // save simType
		String docTitle = simTitle;
		Document file; // document to be written to
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // DBF object to work on file
		File f = new File("xml/" + simType + "State.xml");
		// Set up file
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			file = db.newDocument(); // create new document
			// Create root element + append
			Element sim = file.createElement(SIM);
			file.appendChild(sim);
			// Append type, title, author
			sim.appendChild(addData(file,TYPE,simType));
			sim.appendChild(addData(file,TITLE,simType + "Simulation"));
			sim.appendChild(addData(file,AUTHOR,"Insert_name"));
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			// Input cell data
			for (Cell cell:cells) {
				Element c = file.createElement(CELL);
				// Set cell name
				c.setAttribute(TYPE, cell.getClass().getSimpleName());
				// get Points, State
				for (Double d:cell.getVertices()) {
					c.appendChild(addData(file, "Point", d.toString()));
				}
				c.appendChild(addData(file, "State", String.valueOf(cell.getCurrentState())));
				// add to grid node
				grid.appendChild(c);	
			}
			// Format + save file
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(file);
			StreamResult newFile = new StreamResult(f);
			transformer.transform(source, newFile);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Used to add String data to nodes in an XML file. The expected output should be as follows:
	 * <elemName> data </elemName>
	 * 
	 * @param doc: Document to be added
	 * @param elemName: Name of the node/element
	 * @param data: A string containing the data to be added
	 * @return A node to be added to the xml file
	 */
	private static Node addData(Document doc, String elemName, String data) {
		Element e = doc.createElement(elemName);
		e.appendChild(doc.createTextNode(data));
		return e;
	}
	
	public static void main(String[] args) {
		List<Cell> cells;
		cells = new ArrayList<>();
		double[] props = {0,1};
		cells.add(new GameOfLifeCell(0, props) );
		getSimData(cells,"testType","testTitle");
//		Cell fish = new FishCell(1, null);
//		System.out.println(fish.getClass().getSimpleName());
	}
	
}
