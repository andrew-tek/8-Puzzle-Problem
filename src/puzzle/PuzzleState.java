package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PuzzleState  implements Comparable<PuzzleState>{
	protected PuzzleState parent;
	protected ArrayList<PuzzleState> children;
	protected char [][] puzzleState;
	protected int pathCost;
	protected int heuristic;
	protected int totalCost;

	public PuzzleState () {
		parent = null;
		puzzleState = new char [3][3];
		children = new ArrayList<PuzzleState>();
		pathCost = 0;
		heuristic = 0;
		totalCost = 0;
	}
	public void generatePossiblePuzzle() {
		ArrayList<Integer> randomList = new ArrayList<Integer>();
		for (int i = 0; i < 9; i++) {
			randomList.add(i);
		}
		do {
			Collections.shuffle(randomList);
			int k = 0;
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					puzzleState[i][j] = Integer.toString(randomList.get(k)).charAt(0);
					k++;
				}
			}
		} while (!checkPossible()) ;		
		
	}
	public int getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(int totalCost) {
		this.totalCost = totalCost;
	}
	public int getPathCost() {
		return pathCost;
	}
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	public int getHeuristic() {
		return heuristic;
	}
	public void setHeuristic(int heuristic) {
		this.heuristic = heuristic;
	}
	public PuzzleState (PuzzleState p) {
		this.parent = p;
		puzzleState = new char [3][3];
		children = new ArrayList<PuzzleState>();
		pathCost = 0;
		heuristic = 0;
		totalCost = 0;
	    for (int i = 0; i < 3; i++) {
	        this.puzzleState[i] = Arrays.copyOf(p.puzzleState[i], 3);
	        // For Java versions prior to Java 6 use the next:
	        // System.arraycopy(original[i], 0, result[i], 0, original[i].length);
	    }
		//this.children.addAll(0, p.children);
	}
	@Override
	public String toString() {
		StringBuilder state = new StringBuilder();
		for (int i = 0; i < puzzleState.length; i++) {
			for (int j = 0; j < puzzleState.length; j++) {
				state.append(puzzleState[i][j]);
			}
		}
		return state.toString();
	}
	public void swap (int x1, int y1, int x2, int y2) {
		char temp = puzzleState[x1][y1];
		puzzleState[x1][y1] = puzzleState[x2][y2];
		puzzleState[x2][y2] = temp;
	}
	public PuzzleState getParent() {
		return parent;
	}
	public void setParent(PuzzleState parent) {
		this.parent = parent;
	}
	public int calculateHeuristic () {
		int misplaceTiles = 0;
		int counter = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (Character.getNumericValue(puzzleState[i][j]) != counter) {
					misplaceTiles++;
				}
				counter++;
			}
		}
		return misplaceTiles;
	}
	public boolean equals (PuzzleState other) {
		return this.totalCost == other.totalCost;
	}
	@Override
	public int compareTo(PuzzleState other) {
		if (this.equals(other))
			return 0;
		else if (this.totalCost > other.totalCost) 
			return 1;
		else 
			return -1;
	}
	public void setEntireState (String s) {
		char [] arr = s.toCharArray();
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				puzzleState[i][j] = arr[count];
				count++;
			}
		}
	}

	public void printPuzzle () {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(" " + puzzleState[i][j] + " ");
			}
			System.out.println();
		}
	}
	public boolean checkPossible () {
		char [] sequence = this.toString().toCharArray();
		int inversions = 0;
		for (int i = 0; i < sequence.length - 1; i++) {
			for (int j = i + 1; j < sequence.length; j++) {
				if (sequence[i] == '0' || sequence[j] == '0')
					continue;
				else if (sequence[i] > sequence[j])
					inversions++;
			}
		}
		return inversions % 2 == 0;	
	}
	

	public ArrayList<PuzzleState> expandNode() {
		PuzzleState state1 = new PuzzleState(this);
		state1.setParent(this);
		PuzzleState state2 = new PuzzleState(this);
		state2.setParent(this);
		PuzzleState state3 = new PuzzleState(this);
		state3.setParent(this);
		PuzzleState state4 = new PuzzleState(this);
		state4.setParent(this);
		ArrayList<PuzzleState> newStates = new ArrayList<>();
		pathCost = this.getPathCost() + 1;
		
		if (puzzleState[0][0] == '0') {
			state1.swap(0, 0, 0, 1);
			state2.swap(0, 0, 1, 0);
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			children.add(state1);
			children.add(state2);
		}
		else if (puzzleState[0][1] == '0') {
			state1.swap(0, 1, 0, 0);
			state2.swap(0, 1, 0, 2);
			state3.swap(0, 1, 1, 1);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state3.setHeuristic(state3.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state3.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			state3.setTotalCost(state3.getHeuristic() + state3.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			children.add(state1);
			children.add(state2);
			children.add(state3);
		}
		else if (puzzleState[0][2] == '0') {
			state1.swap(0, 2, 0, 1);
			state2.swap(0, 2, 1, 2);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			children.add(state1);
			children.add(state2);
		}
		else if (puzzleState[1][0] == '0') {
			state1.swap(1, 0, 0, 0);
			state2.swap(1, 0, 1, 1);
			state3.swap(1, 0, 2, 0);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state3.setHeuristic(state3.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state3.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			state3.setTotalCost(state3.getHeuristic() + state3.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			newStates.add(state3);
			children.add(state1);
			children.add(state2);
			children.add(state3);
		}
		else if (puzzleState[1][1] == '0') {
			state1.swap(1, 1, 0, 1);
			state2.swap(1, 1, 1, 0);
			state3.swap(1, 1, 1, 2);
			state4.swap(1, 1, 2, 1);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state3.setHeuristic(state3.calculateHeuristic());
			state4.setHeuristic(state4.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state3.setPathCost(pathCost);
			state4.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			state3.setTotalCost(state3.getHeuristic() + state3.getPathCost());
			state4.setTotalCost(state4.getHeuristic() + state4.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			newStates.add(state3);
			newStates.add(state4);
			children.add(state1);
			children.add(state2);
			children.add(state3);
			children.add(state4);
		}
		else if (puzzleState[1][2] == '0') {
			state1.swap(1, 2, 0, 2);
			state2.swap(1, 2, 1, 1);
			state3.swap(1, 2, 2, 2);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state3.setHeuristic(state3.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state3.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			state3.setTotalCost(state3.getHeuristic() + state3.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			newStates.add(state3);
			children.add(state1);
			children.add(state2);
			children.add(state3);
		}
		else if (puzzleState[2][0] == '0') {
			state1.swap(2, 0, 1, 0);
			state2.swap(2, 0, 2, 1);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			children.add(state1);
			children.add(state2);
		}
		else if (puzzleState[2][1] == '0') {
			state1.swap(2, 1, 1, 1);
			state2.swap(2, 1, 2, 0);
			state3.swap(2, 1, 2, 2);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state3.setHeuristic(state3.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state3.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			state3.setTotalCost(state3.getHeuristic() + state3.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			newStates.add(state3);
			children.add(state1);
			children.add(state2);
			children.add(state3);
		}
		else if (puzzleState [2][2] == '0') {
			state1.swap(2, 2, 1, 2);
			state2.swap(2, 2, 2, 1);
			state1.setHeuristic(state1.calculateHeuristic());
			state2.setHeuristic(state2.calculateHeuristic());
			state1.setPathCost(pathCost);
			state2.setPathCost(pathCost);
			state1.setTotalCost(state1.getHeuristic() + state1.getPathCost());
			state2.setTotalCost(state2.getHeuristic() + state2.getPathCost());
			newStates.add(state1);
			newStates.add(state2);
			children.add(state1);
			children.add(state2);
		}
		return newStates;

	}


	
	}


	
	
