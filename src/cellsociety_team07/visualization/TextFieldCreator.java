package cellsociety_team07.visualization;

import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;



/**
 * This TextFieldCreator class creates a TextField and an appropriate button next to it and adds both to an HBox. This class is
 * used to create two TextFields for user input to change states of cells and params of cells. After a user inputs data into the
 * TextField and presses the button next to it, the appropriate action is executed.
 * @author Dana Park
 */

public class TextFieldCreator {
	protected  TextField textField;
	protected  Button button;
	private static final int SPACING =10;
	
	
	/**
	 * This TextFieldCreator class constructor initialized instance variable textField and button
	 * @author Dana Park
	 */
	public TextFieldCreator (ResourceBundle com, String textFieldName, String buttonName) {
		textField = new TextField ();
		textField.setPromptText(com.getString(textFieldName));
		textField.getText();
		button = new Button(com.getString(buttonName));
	}
	

	protected  HBox textHBoxMaker(ResourceBundle com, String txt, String enter) {
		HBox hb = new HBox();
		hb.getChildren().addAll(textField, button);
		hb.setSpacing(SPACING);
		return hb;
	}
	
	
	protected Button getButton() {
		return button;
	}
	
	protected  TextField getTextValue() {
		return textField;
		
	}

}