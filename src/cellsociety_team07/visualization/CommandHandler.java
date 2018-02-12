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
	
	private static final double RATE = 1.0;
	private static final double DOUBLE_RATE = 2.0;
	private static final double HALF_RATE = 0.5;

	protected static void handleCommand(Event e, Timeline a, MenuCreator m) {
		
		String selectedAction = m.commands().getSelectionModel().getSelectedItem();
		if (selectedAction.equals("Pause")) {
			a.stop();
		}
		if (selectedAction.equals("Play")) {
			defaultRateAndPlay(RATE,a);
		}
		if (selectedAction.equals("Speed Up")) {
			defaultRateAndPlay(RATE,a);
			a.setRate(a.getRate() * DOUBLE_RATE);
		}
		if (selectedAction.equals("Slow Down")) {
			defaultRateAndPlay(RATE,a);
			a.setRate(a.getRate() * HALF_RATE);
		}
	}
	
	protected static void defaultRateAndPlay(double rate, Timeline a) {
		a.setRate(rate);
		a.play();
	}
	
}