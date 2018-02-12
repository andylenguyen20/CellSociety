package cellsociety_team07.visualization;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class TextFieldFactory {
	
	protected static TextField textFieldCreator(TextField textField, ResourceBundle com, String txt) {
		
		textField = new TextField ();
		textField.setPromptText(com.getString(txt));
		textField.getText();
		return textField;
		
	}
	
	protected static Button makeButton(Button button) {
		button = new Button("Enter");
		return button;
		
	}
	
	
}

