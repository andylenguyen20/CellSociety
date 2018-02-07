package cellsociety_team07;

import javafx.animation.Timeline;
import javafx.event.Event;

public class CommandHandler {
	
	protected void handleCommand(Event e, Timeline a, MenuCreator m) {
		
		String selectedAction = m.commands().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Pause")) {
			a.stop();
		}
		if (selectedAction.equals("Play")) {
			defaultRateAndPlay(1.0,a);
		}
		if (selectedAction.equals("Speed Up")) {
			defaultRateAndPlay(1.0,a);
			a.setRate(a.getRate() * 2);
		}
		if (selectedAction.equals("Slow Down")) {
			defaultRateAndPlay(1.0,a);
			a.setRate(a.getRate() * 0.5);
		}
	}
	
	protected void defaultRateAndPlay(double rate, Timeline a) {
		a.setRate(rate);
		a.play();
	}
	
}
