package cellsociety_team07;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;



public class SliderCreator extends GridPane{
	
	private Slider slider;
	private GridPane gPane;
	
	public GridPane sliderInitializer() {
		gPane = new GridPane();	

		
		slider = new Slider();
		slider.setMin(0);
		slider.setMax(100);
		slider.setValue(40);
		slider.setShowTickLabels(true);
		slider.setShowTickMarks(true);
		slider.setMajorTickUnit(50);
		slider.setMinorTickCount(5);
		slider.setBlockIncrement(10);
		slider.setMaxWidth(1000);
	
		slider.setOrientation(Orientation.VERTICAL);
		gPane.add(slider, 1, 1);
		
		return gPane;

		
	}
	
	public Slider getSlider() {
		return slider;
	}
	public GridPane getPane() {
		return gPane;
	}

}
