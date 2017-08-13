package puzzle;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;

import java.util.ArrayList;
import java.util.HashSet;

public class PuzzleH2 {
	protected PuzzleStateH2 head;
	protected PriorityQueue<PuzzleStateH2> frontier;
	protected Set<String> stateSet;
	protected Stack<PuzzleStateH2> solutionSequence;
	protected PuzzleStateH2 solution;
	public int getNodesExpanded() {
		return nodesExpanded;
	}

	public void setNodesExpanded(int nodesExpanded) {
		this.nodesExpanded = nodesExpanded;
	}


	protected int nodesExpanded;

	public PriorityQueue<PuzzleStateH2> getFrontier() {
		return frontier;
	}

	public void setFrontier(PriorityQueue<PuzzleStateH2> frontier) {
		this.frontier = frontier;
	}

	public Set<String> getStateSet() {
		return stateSet;
	}

	public void setStateSet(Set<String> stateSet) {
		this.stateSet = stateSet;
	}

	public PuzzleH2() {
		head = new PuzzleStateH2();
		nodesExpanded = 0;
		head = new PuzzleStateH2();
		frontier = new PriorityQueue<PuzzleStateH2>();
		stateSet = new HashSet<String>();
		solutionSequence = new Stack<PuzzleStateH2>();
	}

	public PuzzleStateH2 getHead() {
		return head;
	}

	public void setHead(PuzzleStateH2 head) {
		this.head = head;
	}

	public void solvePuzzle() {
		PuzzleStateH2 currentNode;
		ArrayList<PuzzleStateH2> newStates;
		frontier.add(head);
		while (!frontier.peek().toString().equals("012345678")) {
			currentNode = frontier.peek();
			if (stateSet.contains(currentNode.toString())) {
				frontier.remove();
			} else {
				frontier.remove();
				nodesExpanded++;
				newStates = new ArrayList<PuzzleStateH2>(currentNode.expandNode());
				stateSet.add(currentNode.toString());
				for (int i = 0; i < newStates.size(); i++) {
					frontier.add(newStates.get(i));
				}
			}

		}
		solution = frontier.peek();
	}

	public void traceSolution() {
		PuzzleStateH2 tempSol = solution;
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
		return step;
	}

}
