package cellsociety_team07;


public class FishCell extends WatorCell{
	private boolean toBeMoved;
	private int numChrononsAlive;
	private boolean toReproduce;
	public static final int REPRODUCTION_CHRONON = 0;
	private Cell replacement;
	private Cell openCell;
	
	public FishCell(int initialState, double[] props) {
		super(initialState, props);
		super.setColors(colors);
		toBeMoved = false;
		numChrononsAlive = 0;
	}
	
	public void update(CellMover cm){
		this.update();
		cm.moveCellInGrid(this, replacement, openCell);
	}
	@Override
	public void update() {
		super.setCurrentState(super.getNextState());	
		numChrononsAlive++;
	}
	public void applyRules(CellMover cm){
		this.applyRules();
		if(toBeMoved){
			if(toReproduce){
				replacement = new FishCell(FISH, super.getProps());
				numChrononsAlive = 0;
			}else{
				replacement = new FishCell(WATER, super.getProps());
			}
			openCell = cm.getCellOfType(WATER, this);
			openCell.setNextState(FISH);
		}
	}
	
	@Override
	public void applyRules() {
		int numOpenSpots = 0;
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() == WATER && cell.getNextState() == WATER){
				numOpenSpots++;
			}
		}
		boolean willBeEaten = this.getNextState() == WATER;
		if(numOpenSpots > 0 && !willBeEaten){
			toBeMoved = true;
		}else{
			toBeMoved = false;
		}
		if(numChrononsAlive >= super.getProps()[REPRODUCTION_CHRONON] && !willBeEaten && toBeMoved){
			toReproduce = true;
		}else{
			toReproduce = false;
		}
	}
}
