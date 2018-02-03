package cellsociety_team07;

public class FireCell extends Cell{

	public static final int EMPTY = 0; // no tree/burnt tree
	public static final int TREE = 1; // living tree
	public static final int BURNING = 2; // tree on fire
	private double probCatch; // probability that tree will catch on fire
	
	public FireCell(int initialState) {
		super(initialState);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void applyRules() {
		if (super.getCurrentState() == BURNING) {
			super.setNextState(EMPTY);
			return;
		}
		
	}

}
