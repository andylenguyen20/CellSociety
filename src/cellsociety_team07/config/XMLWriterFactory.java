package cellsociety_team07.config;

import java.awt.Dimension;
import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.Grid;

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
	private static final String[] WATOR_PROPS = {"FishCrononReproduce","SharkCrononReproduce","SharkStartingEnergy","EnergyForEatingFish"};
	
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
	 * @param g:     Grid containing cells to saved and read
	 * @param simType: String containing type of simulation
	 * @param simTitle: title of simulation
	 */
	public static void getSimData(List<Cell> cells, Grid g, String simType, String simTitle) {
		Document file; // document actively being written to
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // DBF object to work on file
		File f = new File("xml/" + simType + "State.xml"); // filepath where document will eventually be saved
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
			// get + write props
			double[] props = cells.get(0).getProps();
			appendProps(simType,props,sim,file);
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			// Dimensions
			Element dim = file.createElement(DIMENSION);
			dim.appendChild(addData(file,WIDTH,String.valueOf(g.numCols())));
			dim.appendChild(addData(file,HEIGHT,String.valueOf(g.numRows())));
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
			saveXMLFile(file,f);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes an XML file specifying a random simulation, given certain initial parameters (cell shape, grid dimensions, simulation type).
	 * 
	 * @param height    Height of grid to be generated
	 * @param width     Width of grid to be generated
	 * @param simType   Type of simulation
	 * @param simTitle  Title
	 * @param shape     Cell shape
	 */
	public static void writeRandomSimData(int height, int width, String simType, String simTitle, String shape) {
		Document file; // document to be written to
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // DBF object to work on file
		File f = new File("xml/" + simType + ".xml");
		// Dimensions
		Dimension d = new Dimension(width,height);
		// Get Triangular Cell Grid
		List<Cell> cells = GridFactory.generateCellsOfGrid(shape,d,simType);
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
			
			appendRandomProps(simType,sim,file);
			
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			// get dimensions
			Element dim = file.createElement("dimension");
			grid.appendChild(dim);
			dim.appendChild(addData(file,"width",String.valueOf(width)));
			dim.appendChild(addData(file,"height",String.valueOf(height)));
			// Input cell data
			for (Cell c:cells) {
				Element cell = file.createElement(CELL);
				cell.setAttribute(TYPE, String.valueOf(c.getClass().getSimpleName()));
				for (Point2D.Double v:c.getVertices()) {
					cell.appendChild(addData(file,"point",String.valueOf(v.getX()) + "," + String.valueOf(v.getY())));
				}
				cell.appendChild(addData(file,"state",String.valueOf((int)(getNumStates(simType)*Math.random()))));
				grid.appendChild(cell);
			}
			// Format + save file
			saveXMLFile(file,f);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns the number of possible states for a given simulation type
	 * 
	 * @param simType String containing simulation type
	 * @return        number of states for that simulation type
	 */
	private static int getNumStates(String simType) {
		switch (simType) {
		case "GameOfLife":
			return 2;
		case "Fire":
			return 3;
		case "Segregation":
			return 3;
		case "Wator":
			return 3;
		default:
			return -1;
		}
	}
	
	/**
	 * Transforms a document object that has been written into a Document object into an XML file with the specified filepath and name
	 * 
	 * @param doc   Document being saved. Will have already been written algorithmically
	 * @param file  File object containing filepath to which doc will be written and saved
	 * @throws TransformerException
	 */
	private static void saveXMLFile(Document doc, File file) throws TransformerException {
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(doc);
		StreamResult newFile = new StreamResult(file);
		transformer.transform(source, newFile);
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
	 * FireSimulation and SegregationSimulation both require only one property, which is a double between 0 and 1. This randomizes
	 * the data in props array
	 * 
	 * @return double array of one index containing a number between 0 and 1
	 */
	private static double[] getFireSegProps() {
		double[] props = new double[1];
		props[0] = Math.random();
		return props;
	}
	
	private static double[] getWatorProps() {
		double[] props = {3,3,4,5}; // this will change inevitably
		return props;
	}
	
	
	/**
	 * Code to create a param node, append it to the parent simulation node, and set its attributes/values
	 * 
	 * @param paramName Name of node to be created
	 * @param prop      property/value of node
	 * @param file      file to create element in
	 * @param sim       parent node to which param node will be attached; usually simulatoin
	 */
	private static void setParams(String paramName, double prop, Document file, Element sim) {
		Element e = file.createElement("param");
		sim.appendChild(e);
		e.setAttribute("id",paramName);
		e.appendChild(file.createTextNode(String.valueOf(prop)));
	}
	
	/**
	 * Generates <param> tags for each element in the props array for the given simulation and appends them to the simulation tag.
	 * 
	 * @param simType String containing name of simulation
	 * @param sim     The element that each <param> should be appended to. Should be the simulation tag every time
	 * @param file    The file in which elements are being created
	 */
	private static void appendRandomProps(String simType, Element sim, Document file) {
		String param;
		if (simType.equals("FireCell")) {
			param = "probCatch";
		} else {
			param = "similarityReq";
		}
		double[] props;
		switch (simType) {
		case "Fire":
		case "Segregation":
			props = getFireSegProps();
			setParams(param,props[0],file,sim);
			return;
		case "Wator":
			props = getWatorProps();
			for (int i = 0; i < WATOR_PROPS.length; i++) {
				setParams(WATOR_PROPS[i],props[i],file,sim);
			}
		}
	}
	/**
	 * 
	 * @param simType
	 * @param props
	 * @param sim
	 * @param file
	 */
	private static void appendProps(String simType, double[] props, Element sim, Document file) {
		String param;
		if (simType == "FireCell") {
			param = "probCatch";
		} else
			param = "similarityReq";
		switch (simType) {
		case "Fire":
		case "Segregation":
			setParams(param,props[0],file,sim);
			return;
		case "Wator":
			props = getWatorProps();
			for (int i = 0; i < WATOR_PROPS.length; i++) {
				setParams(WATOR_PROPS[i],props[i],file,sim);
			}
		}
	}
	
	public static void main(String[] args) {
		writeRandomSimData(10,10,"Wator","Wator","Triangle");
	}
	
}
