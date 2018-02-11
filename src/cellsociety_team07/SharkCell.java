package cellsociety_team07;

import java.util.List;

public class SharkCell extends WatorCell{
	public static final int REPRODUCTION_CHRONON = 1;
	public static final int STARTING_ENERGY =2;
	public static final int FISH_ENERGY = 3;
	private double energy;
	
	public SharkCell() {
		super();
	}

	@Override
	public void update(CellMover cm) {
		energy--;
		if((energy <= 0 && this.canMove() && super.getCellToMoveTo().getCurrentState() != FISH)
				|| (energy <= 0 && !this.canMove())){
			super.setCellToMoveTo(this);
			super.setReplacement(super.getWaterCellReplacement());
			this.setNextState(WATER);
			System.out.println("yes");
		}
		System.out.println("nah");
		if(this.canMove()){
			
			cm.moveCellInGrid(this, super.getReplacement(), super.getCellToMoveTo());
		}
	}
	
	@Override
	public void applyRules(CellFetcher cf) {
		if(this.canMove()){
			if(this.fishNearby()){
				Cell desiredMoveLocation = cf.getCellOfType(FISH, this);
				desiredMoveLocation.setNextState(SHARK);
				super.setCellToMoveTo(desiredMoveLocation);
				energy += super.getProps()[FISH_ENERGY];
			}else if(this.openSpotAvailable()){
				Cell desiredMoveLocation = cf.getCellOfType(WATER, this);
				desiredMoveLocation.setNextState(SHARK);
				super.setCellToMoveTo(desiredMoveLocation);
			}
			if(super.canReproduce()){
				super.setReplacement(this.getSharkCellReplacement());
				this.setNextState(SHARK);
			}else{
				super.setReplacement(super.getWaterCellReplacement());
				this.setNextState(WATER);
			}
		}
	}
	
	private boolean fishNearby(){
		List<Cell> neighbors = super.getNeighbors();
		int fish = 0;
		for(Cell cell : neighbors){
			if(cell.getCurrentState() == FISH && cell.getNextState() == FISH){
				fish++;
			}
		}
		return fish > 0;
	}
	
	private boolean openSpotAvailable(){
		List<Cell> neighbors = super.getNeighbors();
		int numOpen = 0;
		for(Cell cell : neighbors){
			if(cell.getCurrentState() == WATER && cell.getNextState() == WATER){
				numOpen++;
			}
		}
		return numOpen > 0;
	}
	
	@Override
	protected boolean canMove() {
		return this.fishNearby() || this.openSpotAvailable();
	}
	
	public Cell getSharkCellReplacement(){
		Cell replacement = new SharkCell();
		replacement.setInitialAttributes(SHARK, super.getProps());
		return replacement;
	}
	
	@Override
	public void setInitialAttributes(int initialState, double[] params) {
		super.setInitialAttributes(initialState, params);
		energy = super.getProps()[STARTING_ENERGY];
		super.setMinReproductionChronon(super.getProps()[REPRODUCTION_CHRONON]);
	}
}
