package cellsociety_team07;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NodeMaker extends StackPane{
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private int rows = 10;
	private int columns = 10;
	double gridWidth = sceneWidth / rows;
	double gridHeight = sceneHeight / columns;

	    Rectangle[][] grid = new Rectangle[rows][columns];

	    public void gridToNodes() {
	    		for( int i=0; i < rows; i++) {
	            for( int j=0; j < columns; j++) {
	            		Rectangle rectangle = new Rectangle( gridWidth, gridHeight);
		            rectangle.setStroke(Color.BLACK);
		            rectangle.setFill(Color.LIGHTBLUE);
		            setTranslateX( i * gridWidth);
		            setTranslateY( j * gridHeight);
		            grid[i][j] = rectangle;

	            }
	        }
	    	}
	}


