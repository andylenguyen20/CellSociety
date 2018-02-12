package cellsociety_team07.simulation;

import javafx.scene.paint.Paint;

import javafx.scene.paint.Color;

/**
 * 
 * The purpose of this class is to act as a specific subtype of Cell under the Wator simulation
 * conditions, following the rules of a Wator Cell accordingly.
 * @author Andy Nguyen
 *
 */
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

	/**
	 * instantiates a new WatorCell object
	 */
	public WatorCell() {
		super();
		numChrononsAlive = 0;
		super.setColors(colors);
	}
	
	/**
	 * extends upon the setInitialAttributes method of Cell by initializing other values
	 */
	@Override
	public void setInitialAttributes(int initialState, double[] props){
		super.setInitialAttributes(initialState, props);
		if(initialState == FISH) minReproductionChrononIndex = 0;
		if(initialState == SHARK) minReproductionChrononIndex = 1;
		startingEnergyIndex = 2;
		fishEnergyIndex = 3;
		energy = props[startingEnergyIndex];
	}
	
	/**
	 * increases the chronon count by one
	 */
	public void applyRules(){
		this.numChrononsAlive++;
	}
	
	/**
	 * applies the rules for this given cell
	 * @param cf
	 */
	public void applyRules(CellFetcher cf){
		this.applyRules();
		if(super.getCurrentState() == SHARK){
			this.applySharkRules(cf);
		}else if(super.getCurrentState() == FISH){
			this.applyFishRules(cf);
		}
	}
	
	/**
	 * applies the rules of the shark cell explained in the Wator simulation rules
	 * @param cf
	 */
	private void applySharkRules(CellFetcher cf){
		this.energy--;
		if(this.fishesAvailableToEat()){
			WatorCell prey = (WatorCell) cf.getCellOfType(FISH, this);
			prey.setNextState(SHARK);
			this.energy += super.getParams()[fishEnergyIndex];
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
	
	/**
	 * applies the rules of the fish cell explained in the Wator simulation rules
	 * @param cf
	 */
	private void applyFishRules(CellFetcher cf){
		boolean canMove = this.openSpotsAvailable() && this.getCurrentState() == FISH && this.getNextState() == FISH;
		if(canMove){
			reproduce();
			WatorCell openSpot = (WatorCell) cf.getCellOfType(WATER, this);
			openSpot.setNextState(super.getCurrentState());
			this.transferDataTo(openSpot);
		}
	}
	
	/**
	 * 
	 * @return	a boolean indicating whether this cell is a shark type cell
	 */
	public boolean isShark(){
		return this.getCurrentState() == SHARK;
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
			this.setEnergy(super.getParams()[startingEnergyIndex]);
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
		return numChrononsAlive >= super.getParams()[minReproductionChrononIndex];
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
}
