package puzzle;

import java.util.PriorityQueue;
import java.util.Comparator;


public class PuzzleH1 {
	protected PuzzleState head;
	protected  PriorityQueue<PuzzleState> frontier;
	public PriorityQueue<PuzzleState> getFrontier() {
		return frontier;
	}

	public void setFrontier(PriorityQueue<PuzzleState> frontier) {
		this.frontier = frontier;
	}

	public PuzzleH1() {
		head = new PuzzleState();
		frontier = new PriorityQueue<PuzzleState>();
				
	}

	public static void main(String[] args) {
		PuzzleH1 h1 = new PuzzleH1();
		PuzzleState test1 = new PuzzleState();
		test1.setTotalCost(20);
		PuzzleState test2 = new PuzzleState();
		test2.setTotalCost(10);
		PuzzleState test3 = new PuzzleState();
		test3.setTotalCost(100);
		h1.getFrontier().add(test1);
		h1.getFrontier().add(test3);
		h1.getFrontier().add(test2);
		
		System.out.println(h1.getFrontier().peek().getTotalCost());
		h1.getFrontier().remove();
		System.out.println(h1.getFrontier().peek().getTotalCost());
		h1.getFrontier().remove();
		System.out.println(h1.getFrontier().peek().getTotalCost());
		
		
		
		

	}


}
