package cellsociety_team07;

import java.util.ArrayList;

public class SharkCell extends WatorCell{
	
	private double energy;
	public static final int REPRODUCTION_CHRONON = 1;
	public static final int STARTING_ENERGY = 2;
	public static final int FISH_ENERGY = 3;
	

	public SharkCell(int state, double[] props) {
		super(state, props);
		energy = props[STARTING_ENERGY];
	}

	@Override
	public void update() {

		super.setChronons(super.getChronons() + 1);
	}
	
	@Override
	public void applyRules() {
		energy--;
		if(energy <= 0){
			this.setNextCellState(new FishCell(WATER, super.getProps()));
		}else{
			if(this.canMove()){
				if(this.canEatFish()){
					WatorCell prey = this.getRandomCellOfType(FISH);
					prey.setNextCellState(this);
					this.energy += super.getProps()[FISH_ENERGY];
				}else if(this.hasOpenSpot()){
					WatorCell openCell = getRandomCellOfType(WATER);
					openCell.setNextCellState(this);
				}
				if(this.canReproduce()){
					this.setNextCellState(new SharkCell(SHARK, super.getProps()));
					super.setChronons(0);
				}else{
					this.setNextCellState(new FishCell(WATER, super.getProps()));
				}
			}else{
				this.setNextCellState(this);
			}
		}
	}
	
	private WatorCell getRandomCellOfType(int state){
		ArrayList<WatorCell> possibleList = new ArrayList<WatorCell>();
		for (Cell cell:super.getNeighbors()) {
			WatorCell neigh = (WatorCell) cell;
			if (neigh.getCurrentState() == state && neigh.getNextCellState().getCurrentState() == state) {
				possibleList.add((WatorCell) cell);
			}
		}
		return possibleList.get((int) (Math.random() * possibleList.size()));
	}
	private boolean canEatFish(){
		boolean nearbyFish = false;
		for (Cell cell:super.getNeighbors()) {
			WatorCell neigh = (WatorCell) cell;
			if (neigh.getCurrentState() == FISH && neigh.getNextCellState().getCurrentState() == FISH) {
				nearbyFish = true;
			}
		}
		return nearbyFish;
	}
	private boolean hasOpenSpot(){
		for (Cell cell:super.getNeighbors()) {
			WatorCell neigh = (WatorCell) cell;
			if (neigh.getCurrentState() == WATER && neigh.getNextCellState().getCurrentState() == WATER) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public boolean canMove() {
		return this.canEatFish() || this.hasOpenSpot();
	}
	
	@Override
	public boolean canReproduce() {
		return super.getChronons() >= super.getProps()[REPRODUCTION_CHRONON];
	}
	
}
