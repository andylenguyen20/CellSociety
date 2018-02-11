package cellsociety_team07;

import javafx.scene.paint.Paint;

import javafx.scene.paint.Color;

public class WatorCell extends Cell{
	public static final int WATER = 0;
	public static final int FISH = 1;
	public static final int SHARK = 2;
	public static final Paint[] colors = {Color.BLUE, Color.PINK, Color.GREY};
	private double minReproductionChronon;
	private double numChrononsAlive;
	private double energy;
	private double startingEnergy;
	private double fishEnergy;

	public WatorCell() {
		super();
		numChrononsAlive = 0;
		super.setColors(colors);
	}
	
	@Override
	public void setInitialAttributes(int initialState, double[] props){
		super.setInitialAttributes(initialState, props);
		if(initialState == FISH) minReproductionChronon = props[0];
		if(initialState == SHARK) minReproductionChronon = props[1];
		startingEnergy = props[2];
		fishEnergy = props[3];
		energy = startingEnergy;
	}
	
	public void applyRules(){
		
	}
	
	@Override
	public void update(){
		this.setCurrentState(this.getNextState());
	}
	public void applyRules(CellFetcher cf){
		if(super.getCurrentState() == SHARK){
			this.applySharkRules(cf);
		}else if(this.getCurrentState() == FISH){
			this.applyFishRules(cf);
		}
	}
	
	private void applySharkRules(CellFetcher cf){
		this.energy--;
		this.numChrononsAlive++;
		int fishes = 0, openSpots = 0;
		for(Cell cell : super.getNeighbors()){
			if(cell.getCurrentState() == FISH && cell.getNextState() == FISH){
				fishes++;
			}
			if(cell.getCurrentState() == WATER && cell.getNextState() == WATER){
				openSpots++;
			}
		}
		if(fishes > 0){
			WatorCell prey = (WatorCell) cf.getCellOfType(FISH, this);
			prey.setNextState(super.getCurrentState());
			this.transferDataTo(prey);
			this.energy += this.fishEnergy;
			if(this.canReproduce()){
				numChrononsAlive = 0;
				this.setNextState(SHARK);
			}else{
				this.setNextState(WATER);
			}
		}else if(openSpots > 0){
			WatorCell openSpot = (WatorCell) cf.getCellOfType(WATER, this);
			openSpot.setNextState(super.getCurrentState());
			this.transferDataTo(openSpot);
			if(this.canReproduce()){
				numChrononsAlive = 0;
				this.setNextState(SHARK);
			}else{
				this.setNextState(WATER);
			}
			if(energy <= 0){
				openSpot.setNextState(WATER);
			}
		}else{
			if(energy <= 0){
				this.setNextState(WATER);
			}
		}
	}
	
	private void applyFishRules(CellFetcher cf){
		this.numChrononsAlive++;
		int numOpenSpotsAvailable = 0;
		for (Cell neighbor : super.getNeighbors()) {
			if (neighbor.getCurrentState() == WATER && neighbor.getNextState() == WATER){
				numOpenSpotsAvailable++;
			}
		}
		boolean canMove = numOpenSpotsAvailable > 0 && this.getCurrentState() == FISH && this.getNextState() == FISH;
		if(canMove){
			if(this.canReproduce()){
				numChrononsAlive = 0;
				this.setNextState(FISH);
			}else{
				this.setNextState(WATER);
			}
			WatorCell openSpot = (WatorCell) cf.getCellOfType(WATER, this);
			openSpot.setNextState(super.getCurrentState());
			this.transferDataTo(openSpot);
		}
	}
	private void transferDataTo(WatorCell toBeMovedTo){
		toBeMovedTo.setChronons(this.numChrononsAlive);
		toBeMovedTo.setMinReproductionChronon(this.minReproductionChronon);
		toBeMovedTo.setEnergy(this.energy);
	}
	private boolean canReproduce(){
		return numChrononsAlive >= minReproductionChronon;
	}
	private void setChronons(double chronons){
		this.numChrononsAlive = chronons;
	}
	private void setEnergy(double energy){
		this.energy = energy;
	}
	private void setMinReproductionChronon(double minReproductionChronon){
		this.minReproductionChronon = minReproductionChronon;
	}
	public boolean isShark(){
		return this.getCurrentState() == SHARK;
	}
}
