package puzzle;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import java.util.ArrayList;
import java.util.HashSet;

public class PuzzleH1 {
	protected PuzzleState head;
	protected PuzzleStateH2 headH2;
	protected PriorityQueue<PuzzleState> frontier;
	protected Set<String> stateSet;
	protected Stack<PuzzleState> solutionSequence;
	protected PuzzleState solution;
	public int getNodesExpanded() {
		return nodesExpanded;
	}

	public void setNodesExpanded(int nodesExpanded) {
		this.nodesExpanded = nodesExpanded;
	}

	protected PuzzleStateH2 solutionH2;
	protected int nodesExpanded;

	public PriorityQueue<PuzzleState> getFrontier() {
		return frontier;
	}

	public void setFrontier(PriorityQueue<PuzzleState> frontier) {
		this.frontier = frontier;
	}

	public Set<String> getStateSet() {
		return stateSet;
	}

	public void setStateSet(Set<String> stateSet) {
		this.stateSet = stateSet;
	}

	public PuzzleH1() {
		head = new PuzzleState();
		nodesExpanded = 0;
		headH2 = new PuzzleStateH2();
		frontier = new PriorityQueue<PuzzleState>();
		stateSet = new HashSet<String>();
		solutionSequence = new Stack<PuzzleState>();
	}

	public PuzzleState getHead() {
		return head;
	}

	public void setHead(PuzzleState head) {
		this.head = head;
	}

	public void solvePuzzle() {
		PuzzleState currentNode;
		ArrayList<PuzzleState> newStates;
		frontier.add(head);
		while (!frontier.peek().toString().equals("012345678")) {
			currentNode = frontier.peek();
			// currentNode = new PuzzleState(frontier.peek());
			if (stateSet.contains(currentNode.toString())) {
				frontier.remove();
			} else {
				frontier.remove();
				nodesExpanded++;
				newStates = new ArrayList<PuzzleState>(currentNode.expandNode());
				stateSet.add(currentNode.toString());
				for (int i = 0; i < newStates.size(); i++) {
					frontier.add(newStates.get(i));
				}
			}

		}
		solution = frontier.peek();
	}

	public void traceSolution() {
		PuzzleState tempSol = solution;
		while (tempSol != null) {
			solutionSequence.push(tempSol);
			tempSol = tempSol.getParent();
		}
	}

	public int printSolution() {
		int step = 1;
		System.out.println("Starting Configuration ");
		solutionSequence.pop().printPuzzle();
		System.out.println();
		while (!solutionSequence.isEmpty()) {
			System.out.println("Step " + step);
			solutionSequence.pop().printPuzzle();
			System.out.println();
			step++;
		}
		return step - 1;
	}

}








