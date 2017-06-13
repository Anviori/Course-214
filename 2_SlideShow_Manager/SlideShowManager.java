/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 3
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class SlideShowManager {
	
	static UndoRedoStack undo = new UndoRedoStack();
	static UndoRedoStack redo = new UndoRedoStack();
	static String positionn;
	static ArrayList<String> slideshow = new ArrayList<String>();
	
	/**
	 * [Employs a loop that prompts the user to make
	 * a choice based on the menu options given. The loop exits
	 * when the user chooses the Quit option.]
	 * 
	 * @param args
	 * @throws IOException 
	 */
	
	public static void main(String[] args) throws IOException {

		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);
		
		System.out.println("---------------------------------\n"
				+ "  Welcome to Slideshow Manager!\n"
				+ "--------------------------------");

		while(true) {
			
			System.out.print("\n------------------------------\nMenu:\n\n  A: Add a Photo\n"
					+ "  R: Remove a Photo\n  S: Swap Photos\n"
					+ "  M: Move Photo\n  P: Print\n  Z: Undo\n"
					+ "  Y: Redo\n  Q: Quit\n"
					+ "\n------------------------------\n");
			
			System.out.print("\nEnter option: ");
			String choice = input.readLine().toUpperCase();
			System.out.println();
			
			switch(choice) {
			
				case "A":

					add();
					
					break;
					
				case "R":

					remove();
					
					break;
					
				case "S":
					
					swap();
					
					break;
					
				case "M":

					move();
					
					break;
					
				case "P":
					
					print();
		
					break;
					
				case "Z":
					
					undo();
			
					break;
					
				case "Y":
					
					redo();
					
					break;
					
				case "Q":
					
					System.out.println("\nGoodbye!");
					System.exit(0);
					
				default:
					
					System.out.println("\nInvalid Input. Try Again.\n");
					
					break;
					
			}
			
		}
		
	}
	
	/**
	 * [Add a new photo the slideshow.]
	 * 
	 * @throws IOException
	 */
	
	public static void add() throws IOException {
		
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);

		try {
			
			System.out.print("Enter photo name: ");
			String photo = input.readLine();
			
			System.out.print("Enter position: ");
			positionn = input.readLine();
			int positionA = Integer.parseInt(positionn);	
			positionA--;
		
			ActionCommand actionTempA = new ActionCommand(positionA, photo, ActionType.ADD);
		
			actionTempA.perform(slideshow);
		
			undo.push(actionTempA);
		
			redo.clear();
			
		} catch (NumberFormatException e) {
			
			System.out.println("\nInteger value only. Try Again.\n");
			add();
			
		} catch (IndexOutOfBoundsException e) {
			
			System.out.println("\nIndex out of bounds. Try Again.\n");
			add();
			
		}
		
	}
	
	/**
	 * [Remove a photo from the slideshow.]
	 * 
	 * @throws IOException
	 */
	
	public static void remove() throws IOException {
		
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);
		
		try {
		
			System.out.print("Enter position: ");
			positionn = input.readLine();
			int positionR = Integer.parseInt(positionn);
		
			positionR--;
		
			ActionCommand actionTempR = new ActionCommand(positionR, ActionType.REMOVE);
		
			actionTempR.perform(slideshow);
		
			undo.push(actionTempR);
		
			redo.clear();
		
		} catch (NumberFormatException e) {
			
			System.out.println("\nInteger value only. Try Again.\n");
			remove();
			
		} catch (IndexOutOfBoundsException e) {
			
			if(slideshow.isEmpty()) {
				
				System.out.println("\nSlideshow is empty!");
				
			} else 
				System.out.println("\nIndex out of bounds. Try Again.\n");
			
		}
		
	}
	
	/**
	 * [Swap two photos in the slideshow.]
	 * 
	 * @throws IOException
	 */
	
	public static void swap() throws IOException {
		
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);
		
		try {
	
			System.out.print("Enter first position: ");
			positionn = input.readLine();
			int positionS1 = Integer.parseInt(positionn);
		
			positionS1--;
		
			System.out.print("Enter second position: ");
			positionn = input.readLine();
			int positionS2 = Integer.parseInt(positionn);
		
			positionS2--;
		
			ActionCommand actionTempS = new ActionCommand(positionS1, positionS2, ActionType.SWAP);
		
			actionTempS.perform(slideshow);
		
			undo.push(actionTempS);
		
			redo.clear();
			
		} catch (NumberFormatException e) {
			
			System.out.println("\nInteger value only. Try Again.\n");
			swap();
			
		} catch (IndexOutOfBoundsException e) {
			
			if(slideshow.isEmpty()) {
				
				System.out.println("\nSlideshow is empty!\n");
				
				
			} else {
				
				System.out.println("\nIndex out of bounds. Try Again.\n");
		
				swap();
			
			}
			
		}
		
	}
	
	/**
	 * [Move a photo to a certain spot in the slideshow.]
	 * 
	 * @throws IOException
	 */
	
	public static void move() throws IOException {
		
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);
		
		try {
			
			System.out.print("Enter source position: ");
			positionn = input.readLine();
			int positionM1 = Integer.parseInt(positionn);
			
			positionM1--;
			
			System.out.print("Enter destination position: ");
			positionn = input.readLine();
			int positionM2 = Integer.parseInt(positionn);
			
			positionM2--;
			
			ActionCommand actionTempM = new ActionCommand(positionM1, positionM2, ActionType.MOVE);
			
			actionTempM.perform(slideshow);
			
			undo.push(actionTempM);
			
			redo.clear();
			
		} catch (NumberFormatException e) {
			
			System.out.println("\nInteger value only. Try Again.\n");
			
			move();
			
		} catch (IndexOutOfBoundsException e) {
			
			if(slideshow.isEmpty()) {
				
				System.out.println("\nSlideshow is empty!\n");
				
			} else {
				
				System.out.println("\nIndex out of bounds. Try Again.\n");
		
				move();
			
			}
			
		}
		
	}
	
	/**
	 * [Prints slideshow items and both, the redo and undo stack.]
	 * 
	 */
	
	public static void print() {
		
		System.out.format("\n%50s", "[Slideshow]");
		System.out.print("\n------------------------------------------"
				+ "-----------------------------------------------\n");
	
		int pos = 0;
		
		if(slideshow.size() == 0) 
				System.out.format("%54s", "[Slideshow is Empty]");
		
		for(int i = 0; i < slideshow.size(); i++) {

			pos++;
			
			System.out.print(pos + "." + slideshow.get(i) + " ");
			
			if(pos == 4) {
				
				System.out.println();
				
			}
			
		}
		System.out.print("\n------------------------------------------"
				+ "-----------------------------------------------\n");
		
		System.out.format("\n%27s", "[Undo Stack]");
		System.out.print("\n------------------------------------------");
		System.out.print("\n" + undo.toString());
		System.out.print("\n------------------------------------------\n");

		System.out.format("\n%27s", "[Redo Stack]");
		System.out.print("\n------------------------------------------");
		System.out.print("\n" + redo.toString());
		System.out.print("\n------------------------------------------\n\n");
		
	}
	
	/**
	 * [Undoes a previous action.]
	 * 
	 */
	
	public static void undo() {

		try {
			
			ActionCommand tempAction = undo.pop();
			tempAction = tempAction.getInverse();
			
			tempAction.perform(slideshow);
			
			redo.push(tempAction);
			
			switch(tempAction.getType()) {
			
				case ADD:
				
					System.out.println("Added " + tempAction.getPhoto() + " at position " 
							+ (tempAction.getPositionOne() + 1));
					
					break;
				
				case REMOVE:
				
					System.out.println("Removed " + tempAction.getPhoto() + " from position " 
							+ (tempAction.getPositionOne() + 1));
				
					break;
				
				case SWAP:
				
					System.out.println("Swapped position " + (tempAction.getPositionOne() + 1)
							+ " and position " + (tempAction.getPositionTwo() + 1));
				
					break;
				
				case MOVE: 
				
					System.out.println("Moved " + tempAction.getPhoto() + " from position " 
							+ (tempAction.getPositionOne() + 1) + " to position " + (tempAction.getPositionTwo() + 1));
				
					break;
		
			}
			
		} catch (EmptyStackException e) {
		
			System.out.println("The stack is empty!");
	
		}
		
	}
	
	/**
	 * [Redo a previously undone action on a slideshow
	 * item.]
	 * 
	 */
	
	public static void redo() {
		
		try {
			
			ActionCommand tempAction = redo.pop();
			tempAction = tempAction.getInverse();
			
			tempAction.perform(slideshow);
			
			undo.push(tempAction);

			switch(tempAction.getType()) {
			
				case ADD:
				
					System.out.println("Added " + tempAction.getPhoto() + " at position " 
							+ (tempAction.getPositionOne() + 1));
				
					break;
				
				case REMOVE:
				
					System.out.println("Removed " + tempAction.getPhoto() + " from position " 
							+ (tempAction.getPositionOne() + 1));
				
					break;
				
				case SWAP:
				
					System.out.println("Swapped position " + (tempAction.getPositionOne() + 1)
							+ " and position " + (tempAction.getPositionTwo() + 1));
				
					break;
				
				case MOVE: 
				
					System.out.println("Moved " + tempAction.getPhoto() + " from position " 
							+ (tempAction.getPositionOne() + 1) + " to position " + (tempAction.getPositionTwo() + 1));
				
					break;
		
			}
			
		} catch (EmptyStackException e) {
			
			System.out.println("The stack is empty!");
			
		}
		
	}

}