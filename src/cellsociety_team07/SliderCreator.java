package cellsociety_team07;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class SliderCreator extends GridPane{
	
	private Slider slider;
	private GridPane gPane;
	
    public VBox sliderInitializer() {
    		slider = new Slider();
    		slider.setMin(0);
    		slider.setMax(100);
    		slider.setValue(40);
    		slider.setShowTickLabels(true);
    		slider.setShowTickMarks(true);
    		slider.setMajorTickUnit(50);
    		slider.setMinorTickCount(5);
    		slider.setBlockIncrement(10);
    		slider.setMaxWidth(100);
    		slider.setOrientation(Orientation.VERTICAL);
    		slider.setPrefHeight(500);

    		VBox vbox = new VBox(slider);
    		vbox.setPrefHeight(500);
    		return vbox;
    }

	
	public Slider getSlider() {
		return slider;
	}
	public GridPane getPane() {
		return gPane;
	}

}
