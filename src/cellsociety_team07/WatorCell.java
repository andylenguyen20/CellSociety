package cellsociety_team07;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public abstract class WatorCell extends Cell{

	public static final int WATER = 0;
	public static final int FISH = 1;
	public static final int SHARK = 2;
	public static final Paint[] colors = {Color.BLUE, Color.PINK, Color.GREY};
	private WatorCell nextCellState;
	private int numChrononsAlive;

	public WatorCell(int state, double[] props) {
		super(state, props);
		super.setColors(colors);
		nextCellState = this;
	}
	
	public void setNextCellState(WatorCell nextCell){
		this.nextCellState = nextCell;
	}
	public WatorCell getNextCellState(){
		return nextCellState;
	}
	public boolean isWaterCell(){
		return this.getCurrentState() == WATER;
	}
	public int getChronons(){
		return this.numChrononsAlive;
	}
	public void setChronons(int chronons){
		numChrononsAlive = chronons;
	}
	
	public abstract boolean canMove();
	public abstract boolean canReproduce();
}
