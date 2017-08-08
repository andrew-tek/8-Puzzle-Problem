package puzzle;

public class PuzzleState {
	protected PuzzleState parent;
	protected PuzzleState [] children;
	protected char [][] state;
	
	
	public PuzzleState () {
		parent = null;
		state = new char [3][3];
	}
	@Override
	public String toString() {
		StringBuilder state = new StringBuilder();
		for (int i = 0; i < state.length(); i++) {
			for (int j = 0; j < state.length(); j++) {
				state.append(state[i][j]);
			}
		}
		return state.toString();
	}

}
