package cellsociety_team07;

public class SharkCell extends WatorCell{
	
	private boolean toBeMoved;
	private double energy;
	private double reproductionCounter;
	private double reproductionTime;
	private boolean fishNeighbor;
	public static final int REPRODUCTION_CHRONON = 1;
	public static final int STARTING_ENERGY = 2;
	

	public SharkCell(int state, double[] props) {
		super(state, props);
		super.setColors(colors);
		toBeMoved = false;
		energy = props[STARTING_ENERGY];
		reproductionCounter = 0;
		reproductionTime = props[REPRODUCTION_CHRONON];
	}

	public void applyRules(CellMover cm){
		this.applyRules();
		if(toBeMoved){
			super.setNextState(0);
			Cell replacement;
			if (fishNeighbor) {
				replacement = cm.getRandomEmptyCell(FISH);
			} else {
				replacement = cm.getRandomEmptyCell(WATER);
			}
			replacement.setNextState(super.getCurrentState());
		}
	}
	
	@Override
	public void applyRules() {
		// decrease energy
		decEnergy();
		// Increase reproductionCounter
		incReproduction();
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
			}
				else {
				toBeMoved = false;
				fishNeighbor = false;
			}
		}
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

	}
	
}
