package cellsociety_team07;


import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class NodeMaker{
	private double sceneWidth = 400;
	private double sceneHeight = 400;
	private int n = 10;
	private int m = 10;
	double gridWidth = sceneWidth / n;
	double gridHeight = sceneHeight / m;

	    MyNode[][] grid = new MyNode[n][m];

	    public void gridToNodes() {
	    		for( int i=0; i < n; i++) {
	            for( int j=0; j < m; j++) {
	            		MyNode node = new MyNode(i * gridWidth, j * gridHeight,  gridWidth, gridHeight);
	                grid[i][j] = node;

	            }
	        }
	    	}

	  

	    public static class MyNode extends StackPane {

	        public void myNode( double x, double y, double width, double height) {

	            // create rectangle
	            Rectangle rectangle = new Rectangle( width, height);
	            rectangle.setStroke(Color.BLACK);
	            rectangle.setFill(Color.LIGHTBLUE);


	            // set position
	            setTranslateX( x);
	            setTranslateY( y);


	        }

	    }
	}


