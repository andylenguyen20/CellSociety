package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class TextFieldFactory {
	
	protected Button button;
	protected TextField textField;
	
	protected HBox textFieldCreator(String txt, Button b, ResourceBundle com, String string) {
		
		TextField txtField = new TextField ();
		textField = txtField;
		textField.setPromptText(txt);
		textField.getText();
		button = b;
		button = new Button(com.getString(string));



		HBox hb = new HBox();
		hb.getChildren().addAll(textField, button);
		hb.setSpacing(10);
		return hb;
	}
	
	protected Button getButton() {
		return button;
	}
	

	protected TextField getTextField() {
		return textField;
	}


}


