package cellsociety_team07.visualization;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class TextFieldFactory {
	
	protected TextField textFieldCreator(TextField textField) {
		
		textField = new TextField ();
		textField.setPromptText("Enter State");
		textField.getText();
		return textField;
		
	}
	
	protected Button makeButton(Button button) {
		button = new Button("Enter");
		return button;
		
	}
	
	
}


