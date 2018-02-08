package cellsociety_team07;

import java.util.ArrayList;

public class FishCell extends WatorCell{
	public static final int REPRODUCTION_CHRONON = 0;
	
	public FishCell(int initialState, double[] props) {
		super(initialState, props);
	}
	
	
	@Override
	public void update(){
		if(this.isWaterCell()){
			return;
		}
		super.setChronons(super.getChronons() + 1);
	}
	@Override
	public void applyRules() {
		if(this.isWaterCell()){
			return;
		}
		if(this.canMove()){
			FishCell openCell = (FishCell) getRandomOpenSpot();
			openCell.setNextCellState(this);
			if(this.canReproduce()){
				this.setNextCellState(new FishCell(FISH, super.getProps()));
				super.setChronons(0);
			}else{
				this.setNextCellState(new FishCell(WATER, super.getProps()));
			}
		}else if(!this.willBeEaten()){
			this.setNextCellState(this);
		}
	}
	private Cell getRandomOpenSpot(){
		ArrayList<Cell> openSpots = new ArrayList<>();
		for (Cell neighbor:super.getNeighbors()) {
			WatorCell neigh = (WatorCell) neighbor;
			if (neigh.getCurrentState() == WATER && neigh.getNextCellState().getCurrentState() == WATER){
				openSpots.add(neighbor);
			}
		}
		//return openSpots.get(0);
		return openSpots.get((int) (Math.random() * openSpots.size()));
	}
	
	public boolean canReproduce(){
		return super.getChronons() >= super.getProps()[REPRODUCTION_CHRONON];
	}
	
	@Override
	public boolean canMove(){
		int numOpenSpotsAvailable = 0;
		for (Cell neighbor:super.getNeighbors()) {
			WatorCell neigh = (WatorCell) neighbor;
			if (neigh.getCurrentState() == WATER && neigh.getNextCellState().getCurrentState() == WATER){
				numOpenSpotsAvailable++;
			}
		}
		return (numOpenSpotsAvailable > 0) && !this.willBeEaten() && !this.isWaterCell();
	}
	
	private boolean willBeEaten(){
		return this.getNextCellState().getCurrentState() == SHARK;
	}
}
