package cellsociety_team07;


public class FishCell extends WatorCell{
	private boolean toBeMoved;
	public static final int REPRODUCTION_CHRONON = 0;
	
	public FishCell(int initialState, double[] props) {
		 
		super(initialState, props);
		super.setColors(colors);
		toBeMoved = false;
		
	}
	
	public void applyRules(CellMover cm){
		this.applyRules();
		if(toBeMoved){
			Cell replacement = cm.getRandomEmptyCell(WATER);
			replacement.setNextState(super.getCurrentState());
			super.setNextState(0);
			//super.getProps()[0];
		}
	}
	
	@Override
	public void applyRules() {
		
		int numLiveNeighbors = 0;
		
		for (Cell cell:super.getNeighbors()) {
			if (cell.getCurrentState() != this.getCurrentState()){
				numLiveNeighbors++;
			}
		}
		if(numLiveNeighbors == 4)
			toBeMoved = false;
		else
			toBeMoved = true;
		
	}
	
	public boolean toBeMoved(){
		return toBeMoved;
	}

}
