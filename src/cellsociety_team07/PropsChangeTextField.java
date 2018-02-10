package cellsociety_team07;

import java.util.ResourceBundle;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class PropsChangeTextField  {
	
	protected  TextField propsText;
	protected  Button submit;


	
	protected TextField propsTextFieldCreator(String txt) {
		propsText = new TextField ();
		propsText.setPromptText(txt);
		propsText.getText();
		return propsText;
		
	}
	
	protected Button makeSubmitButton() {
		submit = new Button("Submit");
		return submit;
		
	}
	protected  HBox propsHBoxMaker(String txt) {
		
		HBox hb = new HBox();
		hb.getChildren().addAll(propsTextFieldCreator(txt), makeSubmitButton());
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
