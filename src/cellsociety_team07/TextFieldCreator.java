package cellsociety_team07;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class TextFieldCreator {
	protected static TextField textField;
	protected  static Button submit;
	
	protected static HBox textFieldCreator(String txt) {
		textField = new TextField ();
		textField.setPromptText(txt);
		textField.getText();
		submit = new Button("Submit");



		HBox hb = new HBox();
		hb.getChildren().addAll(textField, submit);
		hb.setSpacing(10);
		return hb;
	}
	
	protected static Button getSubmit() {
		
		return submit;
	}
	
	protected static TextField getTextValue() {
		return textField;
		
	}
}
