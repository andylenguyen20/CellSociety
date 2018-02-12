package cellsociety_team07.config;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.awt.geom.Point2D;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.Grid;

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
	public static final String AUTHOR_NAME = "Brendan Cheng";
	public static final String PROPS_FILE = "xml/random_properties.xml";
	public static final String DESTINATION_FILE = "xml/Random.xml";
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
	 * @param g:     Grid containing cells to saved and read
	 * @param simType: String containing type of simulation
	 */
	public static void saveSimData(Grid g, String simType) {
		Document file; // document actively being written to
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // DBF object to work on file
		File f = new File("xml/" + simType + "State.xml"); // filepath where document will eventually be saved
		// Set up file
		List<Cell> cells = g.getCells();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			file = db.newDocument(); // create new document
			// Create root element + append
			Element sim = file.createElement(SIM);
			file.appendChild(sim);
			// Append type, title, author
			setHeader(sim,file,simType);
			// get + write props
			double[] props = cells.get(0).getParams();
			appendProps(simType,props,sim,file);
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			// Dimensions
			Element dim = file.createElement(DIMENSION);
			dim.appendChild(addData(file,WIDTH,String.valueOf(g.numCols())));
			dim.appendChild(addData(file,HEIGHT,String.valueOf(g.numRows())));
			grid.appendChild(dim);
			// Input cell data
			for (Cell cell:cells) {
				Element c = file.createElement(CELL);
				// Set cell name
				c.setAttribute(TYPE, cell.getClass().getSimpleName());
				// get Points, State
				for (Point2D.Double d:cell.getVertices()) {
					c.appendChild(addData(file, "point", d.getX() + "," + d.getY())); // set vertices
				}
				c.appendChild(addData(file, "state", String.valueOf(cell.getCurrentState()))); // set state
				// add to grid node
				grid.appendChild(c);	
			}
			// Format + save file
			saveXMLFile(file,f);
		} catch(Exception e) {
			throw new BadSimulationException();
		}
	}
	
	/**
	 * Writes an XML file specifying a random simulation, given certain initial parameters (cell shape, grid dimensions, simulation type).
	 * 
	 * @param height    Height of grid to be generated
	 * @param width     Width of grid to be generated
	 * @param simType   Type of simulation
	 * @param shape     Cell shape
	 */
	public static void writeRandomSimData(int height, int width, String simType, String shape) {
		SimulationXMLParser simPars = new SimulationXMLParser(PROPS_FILE); // create object to read random properties
		Document file; // document to be written to
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // DBF object to work on file
		File f = new File(DESTINATION_FILE);
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
			setHeader(sim,file,simType);
			// Append Props
			appendRandomProps(simType,sim,file, simPars);
			// Set up grid node
			Element grid = file.createElement(GRID);
			sim.appendChild(grid);
			// get dimensions
			Element dim = file.createElement("dimension");
			grid.appendChild(dim);
			dim.appendChild(addData(file,"width",String.valueOf(width)));
			dim.appendChild(addData(file,"height",String.valueOf(height)));
			// Input cell data
			HashMap<String,ArrayList<Integer>> stateMap = getPossibleStates(simPars,simType);
			Set<String> typeSet = stateMap.keySet();
			String[] types = new String[typeSet.size()];
			int counter = 0;
			for (String s: typeSet) { // convert keySet to array of Strings
				types[counter] = s;
				counter++;
			}
			for (Cell c:cells) {
				Element cell = file.createElement(CELL);
				String cellType = types[(int) (Math.random()*types.length)]; // select random cell type by getting random string in types array
				cell.setAttribute(TYPE, cellType); // set cell type attribute
				for (Point2D.Double v:c.getVertices()) {
					cell.appendChild(addData(file,"point",String.valueOf(v.getX()) + "," + String.valueOf(v.getY()))); // set vertices
				}
				ArrayList<Integer> states = stateMap.get(cellType);
				cell.appendChild(addData(file,"state",String.valueOf(states.get((int)(Math.random()*states.size()))))); // choose random cell state for given type
				grid.appendChild(cell);
			}
			// Format + save file
			saveXMLFile(file,f);
		} catch(Exception e) {
			throw new BadSimulationException();
		}
	}
	
	/**
	 * Method to set up header for any given XML document (simulation type, simulation title, author name)
	 * 
	 * @param sim     Node to append child nodes to (usually simulation)
	 * @param file    file in which nodes are created
	 * @param simType String containing simulation type
	 */
	private static void setHeader(Element sim, Document file, String simType) {
		sim.appendChild(addData(file,TYPE,simType));
		sim.appendChild(addData(file,TITLE,simType + "Simulation"));
		sim.appendChild(addData(file,AUTHOR,AUTHOR_NAME));
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
	 * Code to create a param node, append it to the parent simulation node, and set its attributes/values
	 * 
	 * @param paramName Name of node to be created
	 * @param prop      property/value of node
	 * @param file      file to create element in
	 * @param sim       parent node to which param node will be attached; usually simulation
	 */
	private static void setParams(String paramName, double prop, Document file, Element sim) {
		Element e = file.createElement("param");
		sim.appendChild(e);
		e.setAttribute("id",paramName);
		e.appendChild(file.createTextNode(String.valueOf(prop)));
	}
	
	/**
	 * Helper method that finds each cell type and its corresponding states
	 * 
	 * @param tag	The simulation tag that pars should look under
	 * @param pars  XML parser
	 * @return		Map to be returned by getPossibleStates
	 */
	private static HashMap<String,ArrayList<Integer>> getStates(Element tag, SimulationXMLParser pars) {
		HashMap<String,ArrayList<Integer>> map = new HashMap<>();
		NodeList cellTypes = tag.getElementsByTagName("cell");
		for (int i = 0; i < cellTypes.getLength(); i++) {
			Element cell = (Element) cellTypes.item(i);
			ArrayList<Integer> states = new ArrayList<>();
			NodeList nodes = cell.getElementsByTagName("state");
			Element stateList = (Element) (nodes.item(0));
			String s = stateList.getTextContent();
			String[] arr = s.split(" ");
			for (String str:arr) {
				states.add(Integer.parseInt(str));
			}
			map.put(cell.getAttribute("type"), states);
		}
		return map;
	}
	/**
	 * Generates a HashMap mapping possible cell types to an ArrayList of possible states that the corresponding cell type can take on
	 * 
	 * @param pars    XMLParser object that will be used to check random_properties.xml
	 * @param simType Pretty self-explanatory
	 * @return 		  A map of cell types to possible states for each cell type
	 */
	private static HashMap<String,ArrayList<Integer>> getPossibleStates(SimulationXMLParser pars, String simType) {
		Document doc = pars.getDocument();
		NodeList listOfSims = doc.getElementsByTagName("simulation");
		boolean check = false;
		HashMap<String,ArrayList<Integer>> stateMap = null;
		for (int i = 0; i < listOfSims.getLength(); i++) {
			Element simulationTag = (Element) listOfSims.item(i);
			if(simulationTag.getAttribute("type").equals(simType)) {
				check = true;
				stateMap = getStates(simulationTag,pars);
			}
		}
		if (!check || stateMap == null)
			throw new BadSimulationException();
		return stateMap;
	}
	/**
	 * Works in conjunction with setParams method to append random param nodes
	 * 
	 * @param sim		node to append children to
	 * @param parent    parent node to which params are attached
	 * @param file		file to append nodes to
	 */
	private static void setUpParams(Element sim, Element parent, Document file) {
		NodeList paramList = parent.getElementsByTagName("param");
		double[] props = new double[paramList.getLength()];
		String[] paramNames = new String[props.length];
		System.out.println(props.length);
		for (int i = 0; i < props.length; i++) {
			String s = paramList.item(i).getTextContent();
			String[] arr = s.split(" ");
			double minBound = Double.parseDouble(arr[0]);
			double maxBound = Double.parseDouble(arr[1]);
			props[i] = Math.random() * (maxBound - minBound) + minBound;
			paramNames[i] = ((Element)paramList.item(i)).getAttribute("id");
		}
		for (int i = 0; i < props.length; i++) {
			setParams(paramNames[i],props[i],file,sim);
		}
	}
	/**
	 * Generates <param> tags for each element in the props array for the given simulation and appends them to the simulation tag.
	 * 
	 * @param simType String containing name of simulation
	 * @param sim     The element that each <param> should be appended to. Should be the simulation tag every time
	 * @param file    The file in which elements are being created
	 * @param pars    SimulationXMLParser object that will scan through random_properties.xml to set each prop
	 */
	private static void appendRandomProps(String simType, Element sim, Document file, SimulationXMLParser pars) {
		Document doc = pars.getDocument();
		NodeList listOfSims = doc.getElementsByTagName("simulation"); // get list of all simulation types
		boolean check = false; // variable to check if simulation type was found; assumed false
		for (int i = 0; i < listOfSims.getLength(); i++) { // search for specified simulation type within listOfSims
			Element simulationTag = (Element) listOfSims.item(i);
			if(simulationTag.getAttribute("type").equals(simType)) {
				check = true;
				setUpParams(sim, simulationTag, file); // if simulation type is found within file, set up param nodes
			}
		}
		if (!check) {
			throw new BadSimulationException();
		}
	}
	/**
	 * Used to append param nodes to file when saving state
	 * 
	 * @param simType Simulation type
	 * @param props   props array of this simulation
	 * @param sim     node to append to
	 * @param file    file to create nodes within
	 */
	private static void appendProps(String simType, double[] props, Element sim, Document file) {
		String param;
		if (simType.equals("Fire")) {
			param = "probCatch";
		} else
			param = "similarityReq";
		switch (simType) {
		case "Fire":
		case "Segregation":
			setParams(param,props[0],file,sim);
			return;
		case "Wator":
			for (int i = 0; i < WATOR_PROPS.length; i++) {
				setParams(WATOR_PROPS[i],props[i],file,sim);
			}
		}
	}	
}