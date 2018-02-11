package cellsociety_team07;

import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;

public abstract class WatorCell extends Cell{
	public static final int WATER = 0;
	public static final int FISH = 1;
	public static final int SHARK = 2;
	public static final Paint[] colors = {Color.BLUE, Color.PINK, Color.GREY};
	private Cell replacement;
	private Cell cellToMoveTo;
	private double minReproductionChronon;
	private double numChrononsAlive;

	public WatorCell() {
		super();
		numChrononsAlive = 0;
		super.setColors(colors);
	}

	public void update(){

	}
	
	public void applyRules(){
		
	}
	
	public abstract void update(CellMover cm);
	public abstract void applyRules(CellFetcher cm);
	
	protected abstract boolean canMove();
	
	
	protected double getChronons(){
		return numChrononsAlive;
	}
	protected void setChronons(double numChronons){
		this.numChrononsAlive = numChronons;
	}
	protected boolean canReproduce(){
		return numChrononsAlive >= minReproductionChronon;
	}
	
	protected void setMinReproductionChronon(double chronon){
		this.minReproductionChronon = chronon;
	}
	protected void setReplacement(Cell replacement){
		this.replacement = replacement;
	}
	protected void setCellToMoveTo(Cell cellToMoveTo){
		this.cellToMoveTo = cellToMoveTo;
	}
	protected Cell getReplacement(){
		return replacement;
	}
	protected Cell getCellToMoveTo(){
		return cellToMoveTo;
	}
	
	protected Cell getWaterCellReplacement(){
		Cell replacement = new FishCell();
		replacement.setInitialAttributes(WATER, super.getProps());
		return replacement;
	}
}
