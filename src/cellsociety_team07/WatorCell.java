package cellsociety_team07;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public abstract class WatorCell extends Cell{

	public static final int WATER = 0;
	public static final int FISH = 1;
	public static final int SHARK = 2;
	public static final Paint[] colors = {Color.BLUE, Color.PINK, Color.GREY};


	public WatorCell() {
		super();
		super.setColors(colors);
	}

	public abstract void update(CellMover cm);
	public abstract void applyRules(CellMover cm);
}
