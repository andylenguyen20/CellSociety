package cellsociety_team07.simulation;

import javafx.scene.paint.Paint;

import javafx.scene.paint.Color;

public class WatorCell extends Cell{
	public static final int WATER = 0;
	public static final int FISH = 1;
	public static final int SHARK = 2;
	public static final Paint[] colors = {Color.BLUE, Color.PINK, Color.GREY};
	private int minReproductionChrononIndex;
	private double numChrononsAlive;
	private double energy;
	private int startingEnergyIndex;
	private int fishEnergyIndex;

	public WatorCell() {
		super();
		numChrononsAlive = 0;
		super.setColors(colors);
	}
	
	@Override
	public void setInitialAttributes(int initialState, double[] props){
		super.setInitialAttributes(initialState, props);
		if(initialState == FISH) minReproductionChrononIndex = 0;
		if(initialState == SHARK) minReproductionChrononIndex = 1;
		startingEnergyIndex = 2;
		fishEnergyIndex = 3;
		energy = props[startingEnergyIndex];
	}
	
	public void applyRules(){
		this.numChrononsAlive++;
	}
	
	@Override
	public void update(){
		this.setCurrentState(this.getNextState());
	}
	public void applyRules(CellFetcher cf){
		this.applyRules();
		if(super.getCurrentState() == SHARK){
			this.applySharkRules(cf);
		}else if(super.getCurrentState() == FISH){
			this.applyFishRules(cf);
		}
	}
	
	private void applySharkRules(CellFetcher cf){
		this.energy--;
		if(this.fishesAvailableToEat()){
			WatorCell prey = (WatorCell) cf.getCellOfType(FISH, this);
			prey.setNextState(SHARK);
			this.energy += super.getProps()[fishEnergyIndex];
			reproduce();
			this.transferDataTo(prey);
		}else if(this.openSpotsAvailable()){
			WatorCell openSpot = (WatorCell) cf.getCellOfType(WATER, this);
			openSpot.setNextState(super.getCurrentState());
			reproduce();
			this.transferDataTo(openSpot);
			if(energy <= 0){
				this.setNextState(WATER);
				openSpot.setNextState(WATER);
			}
		}else{
			if(energy <= 0){
				this.setNextState(WATER);
			}
		}
	}
	
	private void applyFishRules(CellFetcher cf){
		boolean canMove = this.openSpotsAvailable() && this.getCurrentState() == FISH && this.getNextState() == FISH;
		if(canMove){
			reproduce();
			WatorCell openSpot = (WatorCell) cf.getCellOfType(WATER, this);
			openSpot.setNextState(super.getCurrentState());
			this.transferDataTo(openSpot);
		}
	}
	
	private boolean fishesAvailableToEat(){
		int fishes = 0;
		for(Cell cell : super.getNeighbors()){
			if(cell.getCurrentState() == FISH && cell.getNextState() == FISH){
				fishes++;
			}
		}
		return this.getCurrentState() == SHARK && fishes > 0;
	}
	
	private boolean openSpotsAvailable(){
		int numOpenSpotsAvailable = 0;
		for (Cell neighbor : super.getNeighbors()) {
			if (neighbor.getCurrentState() == WATER && neighbor.getNextState() == WATER){
				numOpenSpotsAvailable++;
			}
		}
		return numOpenSpotsAvailable > 0;
	}
	
	private void reproduce(){
		if(this.canReproduce()){
			numChrononsAlive = 0;
			this.setNextState(this.getCurrentState());
			this.setEnergy(super.getProps()[startingEnergyIndex]);
		}else{
			this.setNextState(WATER);
		}
	}
	private void transferDataTo(WatorCell toBeMovedTo){
		toBeMovedTo.setChronons(this.numChrononsAlive);
		toBeMovedTo.setMinReproductionChrononIndex(minReproductionChrononIndex);
		toBeMovedTo.setEnergy(this.energy);
	}
	private boolean canReproduce(){
		return numChrononsAlive >= super.getProps()[minReproductionChrononIndex];
	}
	private void setChronons(double chronons){
		this.numChrononsAlive = chronons;
	}
	private void setEnergy(double energy){
		this.energy = energy;
	}
	private void setMinReproductionChrononIndex(int index){
		this.minReproductionChrononIndex = index;
	}
	public boolean isShark(){
		return this.getCurrentState() == SHARK;
	}
}