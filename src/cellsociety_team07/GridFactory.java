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
   
    private static Cell getDefaultCell(String simulationType){
        switch(simulationType){
        case "Fire":
            return new FireCell();
        case "GameOfLife":
            return new GameOfLifeCell();
        case "Segregation":
            return new SegregationCell();
        case "Wator":
            return new FishCell();
        default:
            throw new BadSimulationException("Bad simulation type while getting default cell");    
        }
    }
   
    public static Grid generateGrid(String gridShape, Dimension gridDim, String simType){
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
   
    /*
     * given grid type and grid dimensions, return a list of uninitialized cells that have vertices corresponding to the
     * matching grid coordinate system
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
    private static List<Cell> getRectangularGrid(Dimension gridDim, String simType){
        int numCols = gridDim.width;
        int numRows = gridDim.height;
        List<Cell> cells = new ArrayList<Cell>();
        for(int rowCount = 0; rowCount < numRows; rowCount++){
            for(int colCount = 0; colCount < numCols; colCount++){
                Cell cell = getDefaultCell(simType);
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
            Cell cell = getDefaultCell(simType);
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