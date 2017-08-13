package puzzle;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		double startTime, h1Time, h2Time;
		int stepsH1, stepsH2;
		int input;
		String sequence;
		PuzzleH1 h1 = new PuzzleH1();
		PuzzleH2 h2 = new PuzzleH2();
		Scanner scanner = new Scanner(System.in);
		
		do {
			printMenu();
			System.out.print("Please make a selection: ");
			input = scanner.nextInt();
			switch (input) {
			case 1:
				//solution usingh1
				System.out.println("Solution using H1\n");
				h1.getHead().generatePossiblePuzzle();
				sequence = h1.getHead().toString();
				startTime = System.nanoTime();
				h1.solvePuzzle();
				h1Time = System.nanoTime() - startTime;
				h1.traceSolution();
				stepsH1 = h1.printSolution();
				
				//Solution Using h2
				System.out.println("Solution using H2\n");
				h2.getHead().setEntireState(sequence);
				startTime = System.nanoTime();
				h2.solvePuzzle();
				h2Time = System.nanoTime() - startTime;
				h2.traceSolution();
				stepsH2 = h2.printSolution();
				
				System.out.println("Number of Moves: " + stepsH1);
				System.out.println("h1 took " + h1Time + " ns");
				System.out.println("A*(h1): " + h1.getNodesExpanded() + " nodes expanded");
				System.out.println("h2 took " + h2Time + " ns");
				System.out.println("A*(h2): " + h2.getNodesExpanded() + " nodes expanded" + "\n");
				break;
			case 2:
				System.out.println("Enter Sequence");
				sequence = scanner.next();
				sequence = sequence.concat(scanner.nextLine());
				sequence = sequence.concat(scanner.nextLine());
				sequence = sequence.concat(scanner.nextLine());
				sequence = sequence.replaceAll("\\s","");
				
				//Solution Using h1
				System.out.println("Solution using H1");
				h1.getHead().setEntireState(sequence);
				if (h1.getHead().checkPossible()) {
					startTime = System.nanoTime();
					h1.solvePuzzle();
					h1Time = System.nanoTime() - startTime;
					sequence = h1.getHead().toString();
					h1.traceSolution();
					stepsH1 = h1.printSolution();
					
					//Solution Using h2
					System.out.println("Solution using H2");
					h2.getHead().setEntireState(sequence);
					startTime = System.nanoTime();
					h2.solvePuzzle();
					h2Time = System.nanoTime() - startTime;
					h2.traceSolution();
					stepsH2 = h2.printSolution();
					
					System.out.println("Number of Moves: " + stepsH1);
					System.out.println("h1 took " + h1Time + " ns");
					System.out.println("A*(h1): " + h1.getNodesExpanded() + " nodes expanded");
					System.out.println("h2 took " + h2Time + " ns");
					System.out.println("A*(h2): " + h2.getNodesExpanded() + " nodes expanded" + "\n");
				}
				else {
					System.out.println("Sequnce not solveable.");
				}
				break;
			default:
				input = 0;
				System.out.println("Program Shutting Down.");
			}
			
			
			
		} while (input != 0);

	}
	public static void printMenu() {
		System.out.println("Menu");
		System.out.println("1. Solve Random Puzzle");
		System.out.println("2. Enter Custom Puzzle");
		System.out.println("0. Exit Program");
	}

}
