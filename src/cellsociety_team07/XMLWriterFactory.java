package cellsociety_team07;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.geom.Point2D.Double;

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
	 * accessible within Simulation.java.
	 * 
	 * Based on code written by App Shah at http://crunchify.com/java-simple-way-to-write-xml-dom-file-in-java/
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
			
			/*
			 * Add code to get props array and write data to nodes as necessary
			 */
			
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			/*
			 * Add code to get dimensions
			 */
			// Input cell data
			for (Cell cell:cells) {
				Element c = file.createElement(CELL);
				// Set cell name
				c.setAttribute(TYPE, cell.getClass().getSimpleName());
				// get Points, State
				for (Double d:cell.getVertices()) {

					c.appendChild(addData(file, "point", d.toString()));
				}
				c.appendChild(addData(file, "state", String.valueOf(cell.getCurrentState())));
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
	/**
	 * 
	 * Writes data to XML file for a rectangular simulation. Takes care of points, randomizes state
	 * 
	 * @param width: width of grid
	 * @param height: height of grid
	 * @param simType: Type of simulation (e.g. Game of Life)
	 * @param simTitle: Title of simulation:
	 * @param numStates: number of possible states
	 */
	public static void writeRectangularSimData(int width,int height, String simType, String simTitle) {
		String fileName = simType; // save simType
		String docTitle = simTitle;
		Document file; // document to be written to
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // DBF object to work on file
		File f = new File("xml/" + simType + ".xml");
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
			sim.appendChild(addData(file,AUTHOR,"Brendan Cheng"));
			
			/*
			 * Add code to get props array and write data to nodes as necessary
			 */
			
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			// get dimensions
			Element dim = file.createElement("dimension");
			grid.appendChild(dim);
			dim.appendChild(addData(file,"width",String.valueOf(width)));
			dim.appendChild(addData(file,"height",String.valueOf(height)));
			// Input cell data
			for (int c = 0; c < width; c++) {
				for (int r = 0; r < height; r++) {
					Element cell = file.createElement(CELL);
					cell.setAttribute(TYPE, simType);
					cell.appendChild(addData(file,"point",String.valueOf(c) + "," + String.valueOf(r))); // top left vertex
					cell.appendChild(addData(file,"point",String.valueOf(c + 1) + "," + String.valueOf(r))); // top right vertex
					cell.appendChild(addData(file,"point",String.valueOf(c + 1) + "," + String.valueOf(r + 1))); // bottom right vertex
					cell.appendChild(addData(file,"point",String.valueOf(c) + "," + String.valueOf(r + 1))); // bottom left vertex
					cell.appendChild(addData(file,"state",String.valueOf((int) (2*Math.random()))));
					grid.appendChild(cell);
				}
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
	
	public static void writeTriangularSimData(List<Cell> triGrid, String simType, String simTitle) {
		String fileName = simType; // save simType
		String docTitle = simTitle;
		Document file; // document to be written to
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // DBF object to work on file
		File f = new File("xml/" + simType + ".xml");
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
			sim.appendChild(addData(file,AUTHOR,"Brendan Cheng"));
			
			/*
			 * Add code to get props array and write data to nodes as necessary
			 */
			
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			// get dimensions
			Element dim = file.createElement("dimension");
			grid.appendChild(dim);
			dim.appendChild(addData(file,"width",String.valueOf(width)));
			dim.appendChild(addData(file,"height",String.valueOf(height)));
			// Input cell data
			for (int r = 0; r < width; r++) {
				for (int c = 0; c < height; c++) {
					Element cell = file.createElement(CELL);
					cell.setAttribute(TYPE, simType);
					cell.appendChild(addData(file,"point",String.valueOf(c) + "," + String.valueOf(r))); // top left vertex
					cell.appendChild(addData(file,"point",String.valueOf(c + 1) + "," + String.valueOf(r))); // top right vertex
					cell.appendChild(addData(file,"point",String.valueOf(c + 1) + "," + String.valueOf(r + 1))); // bottom right vertex
					cell.appendChild(addData(file,"state",String.valueOf((int) (2*Math.random()))));
					grid.appendChild(cell);
				}
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
	
	public static void main(String[] args) {
		writeRectangularSimData(5,5,"GameOfLife","Game of Life");
//		List<Cell> cells;
//		cells = new ArrayList<>();
//		double[] props = {0,1};
//		cells.add(new GameOfLifeCell(0, props) );
//		getSimData(cells,"testType","testTitle");
//		Cell fish = new FishCell(1, null);
//		System.out.println(fish.getClass().getSimpleName());
	}
	
}
