/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 5
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_5;

public class GameBoard {

	private Box[] board;
	private final int boardSize = 9;

	/**
	 * [Default constructor that initializes the
	 * array of Boxes to size boardSize. This is the
	 * representation of the Tic-Tac-Toe game board.]
	 * 
	 */
	
	public GameBoard() {
		
		board = new Box[boardSize];
		
		for(int i = 0; i < boardSize; i++) {
			
			board[i] = Box.EMPTY;
			
		}
		
	}
	
	/**
	 * [Set the gameBoard board.]
	 * 
	 * @param board
	 *     [Box: An array of Boxes.]
	 */

	public void setBoard(Box board, int i) {
		
		this.board[i] = board;
		
	}
	
	/**
	 * [Get the game array of Boxes
	 * of the game board board.]
	 * 
	 */
	
	public Box[] getBoard() {
		
		return board;
		
	}
	
	/**
	 * [Gets the size of the game board.]
	 * 
	 * @return
	 *     [int: boardSize variable.]
	 */
	
	public int getBoardSize() {
		
		return boardSize;
		
	}
	
}
