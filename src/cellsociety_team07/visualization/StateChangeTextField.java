package cellsociety_team07.visualization;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

/**
 * This StateChangeTextField class is responsible for initializing the textfield at the left of the screen which allows you to change
 * the states of the cells once data is entered in the textfield and the Enter button next to the textfield is pressed
 * @author Dana Park
 */


public class StateChangeTextField {
	
	protected  TextField stateText;
	protected  Button enter;


	
	protected TextField stateTextFieldCreator(ResourceBundle com, String txt) {
		stateText = new TextField ();
		stateText.setPromptText(com.getString(txt));
		stateText.getText();
		return stateText;
		
	}
	
	protected Button makeEnterButton(ResourceBundle com, String str) {
		enter = new Button(com.getString(str));
		return enter;
		
	}
	protected  HBox stateHBoxMaker(ResourceBundle com, String txt, String str) {
		
		HBox hb = new HBox();
		hb.getChildren().addAll(stateTextFieldCreator(com,txt), makeEnterButton(com, str));
		hb.setSpacing(10);
		return hb;
	}
	
	
	protected Button getEnter() {
		return enter;
	}
	
	protected  TextField getTextValue() {
		
		return stateText;
		
	}


	
}