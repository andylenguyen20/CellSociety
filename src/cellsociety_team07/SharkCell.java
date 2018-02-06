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
	public static final int STARTING_ENERGY = 2;
	public static final int FISH_ENERGY = 3;
	

	public SharkCell(int state, double[] props) {
		super(state, props);
		toBeMoved = false;
		energy = props[STARTING_ENERGY];
		reproductionCounter = 0;
		reproductionTime = props[REPRODUCTION_CHRONON];
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

	public boolean toReproduce() {
		return reproductionCounter >= reproductionTime;
	}
	
	// Decrease energy every turn
	public void decEnergy() {
		energy--;
	}
	
	public void incReproduction() {
		reproductionCounter++;
	}
	
	public boolean toBeMoved() {
		return toBeMoved;
	}
	@Override
	public void update() {
		
		super.setCurrentState(super.getNextState());	
		incReproduction();
		
	}
	
	public void update(CellMover cm) {
		
		this.update();
		// decrease energy
		decEnergy();
		if(energy <= 0) {
			this.setNextState(WATER);
		} else if(toBeMoved){
			cm.moveCellInGrid(this, replacement, openCell);
		}
		
	}
	
	public void applyRules(CellMover cm) {
		
		this.applyRules();
		if(toBeMoved){
			if (fishNeighbor) {
				openCell = cm.getCellOfType(FISH, this);
				openCell.setNextState(SHARK);
				energy+=super.getProps()[FISH_ENERGY];
			} else {
				openCell = cm.getCellOfType(WATER, this);
				openCell.setNextState(WATER);
			}
			if (toReproduce) {
				replacement = new SharkCell(SHARK, super.getProps());
				reproductionCounter = 0;
			} else {
				replacement = new FishCell(WATER, super.getProps());
			}
		}
		
	}
	
}
