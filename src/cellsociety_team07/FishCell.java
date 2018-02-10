package cellsociety_team07;


public class FishCell extends WatorCell{
	private boolean toBeMoved;
	private int numChrononsAlive;
	private boolean toReproduce;
	public static final int REPRODUCTION_CHRONON = 0;
	private Cell replacement;
	private Cell openCell;

	public FishCell() {
		super();
		toBeMoved = false;
		numChrononsAlive = 0;
	}
	
	public void update(CellMover cm){
		this.update();
		if(toBeMoved){
			cm.moveCellInGrid(this, replacement, openCell);
		}
	}
	@Override
	public void update() {
		numChrononsAlive++;
	}
	@Override
	public void applyRules(CellMover cm){
		this.applyRules();
		if(toBeMoved){
			if(toReproduce){
				replacement = new FishCell();
				replacement.setInitialAttributes(FISH, super.getProps());
				super.setNextState(FISH);
				numChrononsAlive = 0;
			}else{
				replacement = new FishCell();
				replacement.setInitialAttributes(WATER, super.getProps());
				super.setNextState(WATER);
			}
			openCell = cm.getCellOfType(WATER, this);
			openCell.setNextState(FISH);
		}
	}
	
	@Override
	public void applyRules() { 
		toBeMoved = this.canMove();
		toReproduce = this.canReproduce();
	}
	
	/*
	 * must be called after canMove() in applyRules
	 */
	private boolean canReproduce(){
		return toBeMoved && this.numChrononsAlive >= super.getProps()[REPRODUCTION_CHRONON];
	}
	
	private boolean canMove(){
		int numOpenSpotsAvailable = 0;
		for (Cell neighbor:super.getNeighbors()) {
			if (neighbor.getCurrentState() == WATER && neighbor.getNextState() == WATER){
				numOpenSpotsAvailable++;
			}
		}
		return (numOpenSpotsAvailable > 0) && !this.willBeEaten() && !this.isWaterCell();
	}
	
	private boolean isWaterCell(){
		return this.getCurrentState() == WATER;
	}
	
	protected void setToBeMoved(boolean t) {
		toBeMoved = t;
	}
	
	private boolean willBeEaten(){
		//return this.getNextState() == WATER;
		return this.getNextState() == SHARK;
	}
	
	public int getChronons(){
		return this.numChrononsAlive;

	}
}

