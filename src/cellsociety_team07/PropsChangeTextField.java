package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PropsChangeTextField  {
	
	protected  TextField propsText;
	protected  Button submit;


	
	protected TextField propsTextFieldCreator(ResourceBundle com, String txt) {
		propsText = new TextField ();
		propsText.setPromptText(com.getString(txt));
		propsText.getText();
		return propsText;
		
	}
	
	protected Button makeSubmitButton(ResourceBundle com, String str) {
		submit = new Button(com.getString(str));
		return submit;
		}
	
	protected  HBox propsHBoxMaker(ResourceBundle com, String txt, String enter) {
		
		HBox hb = new HBox();
		hb.getChildren().addAll(propsTextFieldCreator(com, txt), makeSubmitButton(com, enter));
		hb.setSpacing(10);
		return hb;
	}
	
	
	protected Button getSubmit() {
		return submit;
	}
	
	protected  TextField getTextValue() {
		return propsText;
		
	}

}
