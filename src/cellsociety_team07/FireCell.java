package cellsociety_team07;


import javafx.scene.paint.*;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.paint.Paint;


public class FireCell extends Cell{

	public static final int EMPTY = 0; // no tree/burnt tree
	public static final int TREE = 1; // living tree
	public static final int BURNING = 2; // tree on fire
	private double probCatch; // probability that tree will catch on fire
	public static final Paint[] colors = {Color.BLACK, Color.GREEN, Color.RED};
	public FireCell(int initialState) {
		super(initialState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyRules() {
		if (super.getCurrentState() == BURNING) {
			super.setNextState(EMPTY);
			return;
		}
		
	}

	@Override
	public Paint getColors() {

		return colors[super.getCurrentState()];
	}

}
