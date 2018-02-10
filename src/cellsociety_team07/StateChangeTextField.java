package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class StateChangeTextField extends TextFieldFactory{
	
	protected  TextField textField;
	protected  Button enter;


	
	protected TextField stateTextFieldCreator(String txt) {
		textField = new TextField ();
		textField.setPromptText(txt);
		textField.getText();
		return textField;
		
	}
	
	protected Button makeEnterButton() {
		enter = new Button("Enter");
		return enter;
		
	}
	protected  HBox stateHBoxMaker(String txt) {
		
		HBox hb = new HBox();
		hb.getChildren().addAll(stateTextFieldCreator(txt), makeEnterButton());
		hb.setSpacing(10);
		return hb;
	}
	
	
	protected Button getEnter() {
		return enter;
	}
	
	protected  TextField getTextValue() {
		
		return textField;
		
	}
	
}