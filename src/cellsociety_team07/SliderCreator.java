package cellsociety_team07;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class SliderCreator {
	
	private Slider slider;
	private GridPane gPane;
	private static final int SLIDER_MIN=0;
	private static final int SLIDER_MAX=100;
	private static final int HBOX_SPACING=10;
	private static final int VALUE=40;
	private static final int MINOR_TICK = 5;
	private static final int MAJOR_TICK = 50;
	private static final int BLOCK_INCREMENT = 10;
	private static final int MAX_WIDTH = 100;
	private static final int PREF_HEIGHT = 500;

	
    public VBox sliderInitializer() {
    		slider = new Slider();
    		slider.setMin(SLIDER_MIN);
    		slider.setMax(SLIDER_MAX);
    		slider.setValue(VALUE);
    		slider.setShowTickLabels(true);
    		slider.setShowTickMarks(true);
    		slider.setMajorTickUnit(MAJOR_TICK);
    		slider.setMinorTickCount(MINOR_TICK);
    		slider.setBlockIncrement(BLOCK_INCREMENT);
    		slider.setMaxWidth(MAX_WIDTH);
    		slider.setOrientation(Orientation.VERTICAL);
    		slider.setPrefHeight(PREF_HEIGHT);

    		VBox vbox = new VBox(slider);
    		vbox.setPrefHeight(PREF_HEIGHT);
    		return vbox;
    }

	
	public Slider getSlider() {
		return slider;
	}
	public GridPane getPane() {
		return gPane;
	}

}
