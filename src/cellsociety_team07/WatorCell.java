package cellsociety_team07;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public abstract class WatorCell extends Cell{
	public static final int WATER = 0;
	public static final int SHARK = 1;
	public static final int FISH = 2;
	public static final Paint[] colors = {Color.BLACK, Color.BLUE, Color.GREEN};

	public WatorCell(int state, double[] props) {
		super(state, props);
		super.setColors(colors);
	}

}
