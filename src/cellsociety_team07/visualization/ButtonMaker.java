package cellsociety_team07.visualization;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class ButtonMaker {
	protected  Button buttonCommand;
	
	public ButtonMaker (ResourceBundle com, String buttonName) {
		buttonCommand = new Button(com.getString(buttonName));
	}
	
	protected Button makeButton(Button button, ResourceBundle com, String string) {
		button = new Button(com.getString(string));
		return button;
	}
	
	protected Button getSubmit() {
		return buttonCommand;
	}

}
