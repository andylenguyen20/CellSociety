package cellsociety_team07;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public abstract class WatorCell extends Cell{
	protected static final int WATER = 0;
	private static final int SHARK = 1;
	private static final int FISH = 2;
	protected static final Paint[] colors = {Color.BLACK, Color.BLUE, Color.GREEN};

	public WatorCell(int state, double[] props) {
		super(state, props);
		super.setColors(colors);
	}

}
