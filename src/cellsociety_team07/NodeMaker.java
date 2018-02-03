package cellsociety_team07;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NodeMaker extends StackPane{
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private int n = 10;
	private int m = 10;
	double gridWidth = sceneWidth / n;
	double gridHeight = sceneHeight / m;

	    Rectangle[][] grid = new Rectangle[n][m];

	    public void gridToNodes() {
	    		for( int i=0; i < n; i++) {
	            for( int j=0; j < m; j++) {
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


