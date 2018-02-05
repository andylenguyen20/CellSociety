package cellsociety_team07;


public class FishCell extends WatorCell{
	private boolean toBeMoved;
	private int numChrononsAlive;
	private boolean toReproduce;
	public static final int REPRODUCTION_CHRONON = 0;
	
	public FishCell(int initialState, double[] props) {
		super(initialState, props);
		super.setColors(colors);
		toBeMoved = false;
		numChrononsAlive = 0;
	}
	
	public void update(CellMover cm){
		if(toBeMoved){
			Cell replacement;
			if(toReproduce){
				replacement = new FishCell(FISH, super.getProps());
			}else{
				replacement = new FishCell(WATER, super.getProps());
			}
			Cell openCell = cm.getCellOfType(WATER, this);
			cm.moveCellInGrid(this, replacement, openCell);
		}
	}
	
	
	@Override
	public void update() {
		super.setCurrentState(super.getNextState());	
		numChrononsAlive++;
	}
	@Override
	public void applyRules() {
		int numOpenSpots = 0;
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() == WATER){
				numOpenSpots++;
			}
		}
		if(numOpenSpots > 0){
			toBeMoved = true;
		}else{
			toBeMoved = false;
		}
		if(numChrononsAlive == super.getProps()[REPRODUCTION_CHRONON]){
			toReproduce = true;
		}else{
			toReproduce = false;
		}
	}
}
