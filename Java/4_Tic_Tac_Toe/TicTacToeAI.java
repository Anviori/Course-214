/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 5
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToeAI {

	/**
	 * [Creates an Empty GameTree and initializes 
	 * which player is going to have which symbol 
	 * from the Box enum.]
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws CloneNotSupportedException 
	 */
	
	public static void main(String[] args) throws IOException, CloneNotSupportedException {
		
		GameTree tree = new GameTree();
		
		playGame(tree);
	
	}
	
	/**
	 * [Provides a user interface allowing the player to play 
	 * against the AI represented by tree.]
	 * 
	 * @param tree
	 *     [GameTree: the GameTree variable used to show the
	 *     interface.]
	 * @throws IOException
	 */
	
	public static void playGame(GameTree tree) throws IOException {
		
		int move = 0;
		boolean play = true;
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);

		System.out.print("-------------------------");
		System.out.print("\n Welcome to Tic Tac Toe!");
		System.out.println("\n-------------------------\n");
		System.out.println("This is the board: \n");
		
		for(int i = 1; i <= 9; i ++) {
			
			if(i == 4 || i == 7) {
				
				System.out.println();
				
			}
		
			System.out.print("|" + i + "|");
			
		}
		
		while(play) {
		
			try {

				System.out.print("\n\nMake a move: ");
				String m = input.readLine();
				move = Integer.parseInt(m);
			
				tree.makeMove(--move);

				for(int i = 0; i < 9; i++) {
					
					if(tree.getCursor().getConfig()[i] != null) {
					
						tree.getCursor().getConfig()[i].setProbabilites();
						
					}
					
				}
						
				double probA = 0.0;
				double probB = 0.0;
				int aiMove = 0;

				for(int i = 0; i < 9; i++) {
					
					if(tree.getCursor().getConfig()[i] != null) {
						
						probA = tree.getCursor().getConfig()[i].getWinProb();
						
							if(probA > probB) {
								
								aiMove = i;
								
								probB = probA;
								
							if(GameTree.win(tree.getCursor().getConfig()[aiMove])) {
								
								break;
								
							}
							
							for(int j = 0; j < 9; j ++) {
								
								if(tree.getCursor().getBoard().getBoard()[j] == Box.EMPTY) {
									
									tree.getCursor().getBoard().getBoard()[j] = Box.X;
									
									if(GameTree.win(tree.getCursor())) {
										
										aiMove = j;
										
									}
									
									tree.getCursor().getBoard().getBoard()[j] = Box.EMPTY;
									
								}
								
							}
							
						}
					
					}
				
				}
							
				tree.makeMove(aiMove);
				
				tree.getCursor().setProbabilites();
				
				System.out.print(tree.getCursor().toString());		
				
				if(GameTree.win(tree.getCursor())) {
					
					play = false;
					
				}
				
			} catch (IllegalArgumentException | NullPointerException e) {
					
				System.out.println("\nIncorrect input. Try again.");
				
			}
			
		}

		if(GameTree.checkWin(tree.getCursor()) == Box.X) {
			
			System.out.println("You win!");

		} 
		
		if(GameTree.checkWin(tree.getCursor()) == Box.O) {
			
			System.out.println("\n\nYou lose!");

		} 
		
		if(GameTree.checkWin(tree.getCursor()) == Box.EMPTY) {
			
			System.out.println("It's a draw!");

		}
		
	}
	
}
