package cellsociety_team07;

public class SharkCell extends WatorCell{
	
	private boolean toBeMoved;
	private double energy;
	private double reproductionCounter;
	private double reproductionTime;
	private boolean fishNeighbor;
	private boolean toReproduce;
	private Cell openCell;
	private Cell replacement;
	public static final int REPRODUCTION_CHRONON = 1;
	public static final int STARTING_ENERGY =2;
	public static final int FISH_ENERGY = 3;
	
	public SharkCell() {
		super();
		toBeMoved = false;
		reproductionCounter = 0;
	}

	
	public void update(CellMover cm) {
		
		this.update();
		
		decEnergy();
		if(energy < 0) {
			this.setCurrentState(WATER);
		} else if(toBeMoved){
			cm.moveCellInGrid(this, replacement, openCell);
		}
		
	}
	
	@Override
	public void update() {
		
		super.setCurrentState(super.getNextState());	
		incReproduction();
		
	}
	
	@Override
	public void applyRules() {
		
		// Increase reproductionCounter
		incReproduction();
		toReproduce = toReproduce();
		// Check neighbors for movement options
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() == FISH && cell.getNextState() != WATER) {
				toBeMoved = true;
				fishNeighbor = true;
				return;
			} else if (cell.getCurrentState() == WATER && cell.getNextState() == WATER) {
				toBeMoved = true;
				fishNeighbor = false;
				return;
			} else {
				toBeMoved = false;
				fishNeighbor = false;
			}
		}
		
	}
	
	public void applyRules(CellMover cm) {
		
		this.applyRules();
		if(toBeMoved){
			if (fishNeighbor) {
				openCell = cm.getCellOfType(FISH, this);
				
				openCell.setNextState(WATER);
				energy+=super.getParams()[FISH_ENERGY];
			} else {
				openCell = cm.getCellOfType(WATER, this);
				openCell.setNextState(WATER);
			}
			if (toReproduce) {
				replacement = new SharkCell();
				super.setNextState(SHARK);
				reproductionCounter = 0;
			} else {
				replacement = new FishCell();
				super.setNextState(SHARK);
			}
		}
	}

	@Override
	public void setInitialAttributes(int initialState, double[] params){
		super.setInitialAttributes(initialState, params);
		energy = super.getParams()[STARTING_ENERGY];
		reproductionCounter = 0;
		reproductionTime = super.getParams()[REPRODUCTION_CHRONON];
	}
	
	public boolean toReproduce() {
		return reproductionCounter >= reproductionTime;
	}
	
	public void decEnergy() {
		energy--;
	}
	
	public void incReproduction() {
		reproductionCounter++;
	}
	
	public boolean toBeMoved() {
		return toBeMoved;
	}
	
	

	
	
	
}
