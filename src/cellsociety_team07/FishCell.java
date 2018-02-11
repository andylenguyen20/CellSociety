 package cellsociety_team07;


public class FishCell extends WatorCell{
	public static final int REPRODUCTION_CHRONON = 0;
	public FishCell() {
		super();
	}
	
	public void update(CellFetcher cf){
		if(this.isWaterCell()){
			return;
		}
		if(this.canMove()){
			cm.moveCellInGrid(this, super.getReplacement(), super.getCellToMoveTo());
		}
		super.setChronons(super.getChronons() + 1);
	}
	@Override
	public void applyRules(CellFetcher cf){
		if(this.isWaterCell()){
			return;
		}
		if(this.canMove()){
			Cell desiredMoveLocation = cf.getCellOfType(WATER, this);
			desiredMoveLocation.setNextState(FISH);
			super.setCellToMoveTo(desiredMoveLocation);
			if(super.canReproduce()){
				super.setReplacement(this.getFishCellReplacement());
				super.setChronons(0);
				this.setNextState(FISH);
			}else{
				super.setReplacement(super.getWaterCellReplacement());
				this.setNextState(WATER);
			}
		}
	}
	
	@Override
	protected boolean canMove(){
		int numOpenSpotsAvailable = 0;
		for (Cell neighbor:super.getNeighbors()) {
			if (neighbor.getCurrentState() == WATER && neighbor.getNextState() == WATER){
				numOpenSpotsAvailable++;
			}
		}
		return (numOpenSpotsAvailable > 0) && !this.willBeEaten();
	}
	
	private boolean isWaterCell(){
		return this.getCurrentState() == WATER;
	}
	
	private boolean willBeEaten(){
		return this.getNextState() == SHARK;
	}
	
	public Cell getFishCellReplacement(){
		Cell replacement = new FishCell();
		replacement.setInitialAttributes(FISH, super.getProps());
		return replacement;
	}
	@Override
	public void setInitialAttributes(int initialState, double[] params) {
		super.setInitialAttributes(initialState, params);
		super.setMinReproductionChronon(super.getProps()[REPRODUCTION_CHRONON]);
	}
}
