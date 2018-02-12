package cellsociety_team07.visualization;

import javafx.animation.Timeline;
/**
 * This CommandHandler class executes appropriate actions whenever a button on the Choose Command drop down is pushed. 
 * It allows the user to Play, Pause, Slow Down,
 * and Speed Up, as well as Step Forward through the simulation
 * @author Dana Park
 */

import javafx.event.Event;

public class CommandHandler {
	protected static void handleCommand(Event e, Timeline a, MenuCreator m) {
		
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
	
	protected static void defaultRateAndPlay(double rate, Timeline a) {
		a.setRate(rate);
		a.play();
	}
	
}
