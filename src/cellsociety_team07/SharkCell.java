package cellsociety_team07;

public class SharkCell extends WatorCell{
	
	private boolean toBeMoved;
	private double energy;
	private double reproductionCounter;
	private double reproductionTime;
	private boolean fishNeighbor;
	private boolean toReproduce;
	public static final int REPRODUCTION_CHRONON = 1;
	public static final int STARTING_ENERGY = 2;
	public static final int FISH_ENERGY = 3;
	private Cell replacement;
	private Cell openCell;

	public SharkCell(int state, double[] props) {
		super(state, props);
		toBeMoved = false;
		energy = props[STARTING_ENERGY];
		reproductionCounter = 0;
		reproductionTime = props[REPRODUCTION_CHRONON];
	}
	@Override
	public void applyRules() {
		// decrease energy
		decEnergy();
		// Increase reproductionCounter
		incReproduction();
		toReproduce = toReproduce();
		// Check neighbors for movement options
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() == FISH) {
				toBeMoved = true;
				fishNeighbor = true;
				return;
			} else if (cell.getCurrentState() == WATER) {
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
	
	public void update(CellMover cm){
		this.update();
		
		cm.moveCellInGrid(this, replacement, openCell);
	}
	
	public void applyRules(CellMover cm) {
		this.applyRules();
		if(energy <= 0) {
			this.setNextState(WATER);
		} else if(toBeMoved){
			if (fishNeighbor) {
				openCell = cm.getCellOfType(FISH, this);
				openCell.setNextState(WATER);
			} else {
				openCell = cm.getCellOfType(WATER, this);
				openCell.setNextState(SHARK);
			}
			if (toReproduce) {
				replacement = new SharkCell(SHARK, super.getProps());
			} else {
				replacement = new FishCell(WATER, super.getProps());
			}
		}
	}
	
}
