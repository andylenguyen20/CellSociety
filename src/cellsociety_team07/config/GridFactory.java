package cellsociety_team07.config;

import java.awt.Dimension;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cellsociety_team07.simulation.Cell;
import cellsociety_team07.simulation.FireGrid;
import cellsociety_team07.simulation.GameOfLifeGrid;
import cellsociety_team07.simulation.Grid;
import cellsociety_team07.simulation.SegregationGrid;
import cellsociety_team07.simulation.WatorGrid;

/**
 * The purpose of this class is to provide a static way to generate either a grid that is fully
 * initialized with initialized cells or a grid that is fully initialized with unitialized cells.
 * It does this for Rectangular and Triangular grids for all four simulation types.
 * @author Andy Nguyen and Brendan Cheng
 *
 */
public class GridFactory {
	private static final HashMap<String,ArrayList<String>> typeMap = new HashMap<>();
	static {
		ArrayList<String> gol = new ArrayList<>();
		gol.add("GameOfLifeCell");
		typeMap.put("GameOfLife",gol);
		ArrayList<String> fire = new ArrayList<>();
		fire.add("FireCell");
		typeMap.put("Fire",fire);
		ArrayList<String> seg = new ArrayList<>();
		seg.add("SegregationCell");
		typeMap.put("Segregation",seg);
		ArrayList<String> wator = new ArrayList<>();
		wator.add("WatorCell");
		typeMap.put("Wator",wator);
	}
	private static final double triangleXStep = 0.5;
	private static final double triangleYStep = 1;
	private static final double rectangleXStep = 1;
	private static final double rectangleYStep = 1;
	
	/**
	 * Creates a new grid object that contains the initializedCells and grid dimensions. This method
	 * does not need to call and helpers or perform any coordinate system calculations
	 * @param initializedCells
	 * @param gridDimensions
	 * @param simType
	 * @return	the Grid of initialized cells
	 */
	public static Grid generateInitializedGrid(List<Cell> initializedCells, Dimension gridDimensions, String simType){
		switch(simType){
		case "Fire": 
			return new FireGrid(initializedCells, gridDimensions);
		case "GameOfLife":
			return new GameOfLifeGrid(initializedCells, gridDimensions);
		case "Segregation":
			return new SegregationGrid(initializedCells, gridDimensions);
		case "Wator":
			return new WatorGrid(initializedCells, gridDimensions);
		default:
			throw new BadSimulationException("Bad simulation type while getting default cell");		
		}
	}
	
	/**
	 * Creates a grid that has a list of blank cells of a given simulation type and grid shape. These cells 
	 * have the appropriate vertices for the specific grid shape, but are blank in that they are not yet initialized
	 * with any initial state or simulation parameters.
	 * @param gridShape
	 * @param gridDim
	 * @param simType
	 * @return	the Grid of interest
	 */
	public static Grid generateRandomizedGrid(String gridShape, Dimension gridDim, String simType){
		switch(simType){
		case "Fire": 
			return new FireGrid(generateCellsOfGrid(gridShape, gridDim, simType), gridDim);
		case "GameOfLife":
			return new GameOfLifeGrid(generateCellsOfGrid(gridShape, gridDim, simType), gridDim);
		case "Segregation":
			return new SegregationGrid(generateCellsOfGrid(gridShape, gridDim, simType), gridDim);
		case "Wator":
			return new WatorGrid(generateCellsOfGrid(gridShape, gridDim, simType), gridDim);
		default:
			throw new BadSimulationException("Bad simulation type while getting default cell");		
		}
	}
	
	/**
	 * given grid type and grid dimensions, return a list of uninitialized cells that have vertices corresponding to the
	 * matching grid coordinate system
	 * @param gridShape	the shape of the grid of interest
	 * @param gridDim	the grid dimensions of interest
	 * @param simType	the simulation type of interest
	 * @return
	 */
	public static List<Cell> generateCellsOfGrid(String gridShape, Dimension gridDim, String simType){
		switch(gridShape){
			case "Triangle":
				return getTriangularGrid(gridDim, simType);
			case "Rectangle":
				return getRectangularGrid(gridDim, simType);
			default:
				throw new BadSimulationException("Wrong grid type!");
		}
	}
	private static String getCellType(String simType) {
		int numStates = typeMap.get(simType).size();
		return typeMap.get(simType).get((int)(numStates*Math.random()));
	}
	private static List<Cell> getRectangularGrid(Dimension gridDim, String simType){
		int numCols = gridDim.width;
		int numRows = gridDim.height;
		List<Cell> cells = new ArrayList<Cell>();
		for(int rowCount = 0; rowCount < numRows; rowCount++){
			for(int colCount = 0; colCount < numCols; colCount++){
				Cell cell = CellFactory.generateBlankCell(getCellType(simType));
				cell.setVertices(getRectangleVertices(rowCount, colCount));
				cells.add(cell);
			}
		}
		return cells;
	}
	private static List<Point2D.Double> getRectangleVertices(int rowCount, int colCount){
		Point2D.Double topLeft = new Point2D.Double(colCount, rowCount);
		Point2D.Double topRight = new Point2D.Double(colCount + rectangleXStep, rowCount);
		Point2D.Double bottomRight = new Point2D.Double(colCount + rectangleXStep, rowCount + rectangleYStep);
		Point2D.Double bottomLeft = new Point2D.Double(colCount, rowCount + rectangleYStep);
		return Arrays.asList(topLeft, topRight, bottomRight, bottomLeft);
	}
	private static List<Cell> getTriangularGrid(Dimension grid, String simType){
		int numCols = grid.width;
		int numRows = grid.height;
		List<Cell> cells = new ArrayList<Cell>();
		cells.addAll(getDownwardWideTriangleRows(numRows, numCols, simType));
		cells.addAll(getDownwardNarrowTriangleRows(numRows, numCols, simType));
		cells.addAll(getUpwardWideTriangleRows(numRows, numCols, simType));
		cells.addAll(getUpwardNarrowTriangleRows(numRows, numCols, simType));
		return cells;
	}
	private static List<Cell> getDownwardWideTriangleRows(int numRows, int numCols, String simType){
		List<Cell> cells = new ArrayList<Cell>();
		for(int rowCount = 0; rowCount < numRows; rowCount+= 2){
			Point2D.Double topLeft = new Point2D.Double(0, rowCount);
			cells.addAll(getTriangleRow(topLeft,numCols, 1, simType));
		}
		return cells;
	}
	private static List<Cell> getDownwardNarrowTriangleRows(int numRows, int numCols, String simType){
		List<Cell> cells = new ArrayList<Cell>();
		for(int rowCount = 1; rowCount < numRows; rowCount+= 2){
			Point2D.Double left = new Point2D.Double(triangleXStep, rowCount * triangleYStep);
			cells.addAll(getTriangleRow(left, numCols - 1, 1, simType));
		}
		return cells;
	}
	private static List<Cell> getUpwardWideTriangleRows(int numRows, int numCols, String simType){
		List<Cell> cells = new ArrayList<Cell>();
		for(int rowCount = 2; rowCount <= numRows; rowCount+= 2){
			Point2D.Double bottomLeft = new Point2D.Double(0, rowCount * triangleYStep);
			cells.addAll(getTriangleRow(bottomLeft, numCols, -1, simType));
		}
		return cells;
	}
	private static List<Cell> getUpwardNarrowTriangleRows(int numRows, int numCols, String simType){
		List<Cell> cells = new ArrayList<Cell>();
		for(int rowCount = 1; rowCount <= numRows; rowCount+= 2){
			Point2D.Double left = new Point2D.Double(triangleXStep, rowCount * triangleYStep);
			cells.addAll(getTriangleRow(left, numCols - 1, -1, simType));
		}
		return cells;
	}
	private static List<Cell> getTriangleRow(Point2D.Double leftMost, int numTriangles, int direction, String simType){
		List<Cell> triangleRow = new ArrayList<Cell>();
		double topLeftX = leftMost.getX();
		double topLeftY = leftMost.getY();
		for(int triangleHorizontalCount = 0; triangleHorizontalCount < numTriangles; triangleHorizontalCount++, topLeftX += 2*triangleXStep){
			Cell cell = CellFactory.generateBlankCell(getCellType(simType));
			cell.setVertices(getTriangleVertices(topLeftX, topLeftY, direction));
			triangleRow.add(cell);
		}
		return triangleRow;
	}
	private static List<Point2D.Double> getTriangleVertices(double topLeftX, double topLeftY, int direction){
		Point2D.Double leftVertice = new Point2D.Double(topLeftX, topLeftY);
		Point2D.Double middleVertice = new Point2D.Double(topLeftX + triangleXStep, topLeftY + direction * triangleYStep);
		Point2D.Double rightVertice = new Point2D.Double(topLeftX + 2*triangleXStep, topLeftY);
		return Arrays.asList(leftVertice, middleVertice, rightVertice);
	}

}

