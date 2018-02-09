package cellsociety_team07;

import java.awt.Dimension;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridFactory {
	private static final double triangleXStep = 0.5;
	private static final double triangleYStep = 1;
	private static final double rectangleXStep = 1;
	private static final double rectangleYStep = 1;
	
	/*
	 * given grid type and grid dimensions, return a list of unitialized cells that have vertices corresponding to the
	 * matching grid coordinate system
	 */
	public static List<ExperimentalCell> generateGrid(String gridType, Dimension gridDim){
		switch(gridType){
			case "Triangle":
				return getTriangularGrid(gridDim);
			case "Rectangle":
				return getRectangularGrid(gridDim);
			default:
				throw new BadSimulationException("Wrong grid type!");
		}
	}
	private static List<ExperimentalCell> getRectangularGrid(Dimension gridDim){
		int numCols = gridDim.width;
		int numRows = gridDim.height;
		List<ExperimentalCell> cells = new ArrayList<ExperimentalCell>();
		for(int rowCount = 0; rowCount < numRows; rowCount++){
			for(int colCount = 0; colCount < numCols; colCount++){
				ExperimentalCell cell = new ExperimentalCell();
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
	private static List<ExperimentalCell> getTriangularGrid(Dimension grid){
		int numCols = grid.width;
		int numRows = grid.height;
		List<ExperimentalCell> cells = new ArrayList<ExperimentalCell>();
		cells.addAll(getDownwardWideTriangleRows(numRows, numCols));
		cells.addAll(getDownwardNarrowTriangleRows(numRows, numCols));
		cells.addAll(getUpwardWideTriangleRows(numRows, numCols));
		cells.addAll(getUpwardNarrowTriangleRows(numRows, numCols));
		return cells;
	}
	private static List<ExperimentalCell> getDownwardWideTriangleRows(int numRows, int numCols){
		List<ExperimentalCell> cells = new ArrayList<ExperimentalCell>();
		for(int rowCount = 0; rowCount < numRows; rowCount+= 2){
			Point2D.Double topLeft = new Point2D.Double(0, rowCount);
			cells.addAll(getTriangleRow(topLeft,numCols, 1));
		}
		return cells;
	}
	private static List<ExperimentalCell> getDownwardNarrowTriangleRows(int numRows, int numCols){
		List<ExperimentalCell> cells = new ArrayList<ExperimentalCell>();
		for(int rowCount = 1; rowCount < numRows; rowCount+= 2){
			Point2D.Double left = new Point2D.Double(triangleXStep, rowCount * triangleYStep);
			cells.addAll(getTriangleRow(left, numCols - 1, 1));
		}
		return cells;
	}
	private static List<ExperimentalCell> getUpwardWideTriangleRows(int numRows, int numCols){
		List<ExperimentalCell> cells = new ArrayList<ExperimentalCell>();
		for(int rowCount = 2; rowCount <= numRows; rowCount+= 2){
			Point2D.Double bottomLeft = new Point2D.Double(0, rowCount * triangleYStep);
			cells.addAll(getTriangleRow(bottomLeft, numCols, -1));
		}
		return cells;
	}
	private static List<ExperimentalCell> getUpwardNarrowTriangleRows(int numRows, int numCols){
		List<ExperimentalCell> cells = new ArrayList<ExperimentalCell>();
		for(int rowCount = 1; rowCount <= numRows; rowCount+= 2){
			Point2D.Double left = new Point2D.Double(triangleXStep, rowCount * triangleYStep);
			cells.addAll(getTriangleRow(left, numCols - 1, -1));
		}
		return cells;
	}
	private static List<ExperimentalCell> getTriangleRow(Point2D.Double leftMost, int numTriangles, int direction){
		List<ExperimentalCell> triangleRow = new ArrayList<ExperimentalCell>();
		double topLeftX = leftMost.getX();
		double topLeftY = leftMost.getY();
		for(int triangleHorizontalCount = 0; triangleHorizontalCount < numTriangles; triangleHorizontalCount++, topLeftX += 2*triangleXStep){
			ExperimentalCell cell = new ExperimentalCell();
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
