package cellsociety_team07.visualization;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * This is my ButtonMaker class which allows for Buttons to be created and used based on a ResourceBundle
 * 
 * @author Dana Park
 */


public class ButtonMaker {
	protected  Button buttonCommand;
	/**
	 * This is my ButtonMaker constructor which initializes a button
	 * 
	 * @author Dana Park
	 */
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
