/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 5
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_5;

public class GameTree {
	
	private GameBoardNode root;
	private GameBoardNode cursor;
	
	/**
	 * [Default constructor. An empty Tree should still
	 * have a root with the default initial configuration.]
	 * 
	 * @throws CloneNotSupportedException 
	 * 
	 */
	
	public GameTree() {
		
		GameBoard board = new GameBoard();
		
		Box player = Box.X;
		
		root = new GameBoardNode(board, player);
		cursor = root; 
		
		buildTree(root, root.getCurrentTurn());
		
	}

	/**
	 * [Attempt to make a move at the position passed.]
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [0 <= position <= 9]
	 * @param position
	 *     [int: position the player is trying to play.]
	 * @throws IllegalArgumentException
	 *     [When the position given is 0 or less or greater
	 *     than 9.]
	 */
	
	public void makeMove(int position) throws IllegalArgumentException {
		
		if(position < 0 || position > 9)
			throw new IllegalArgumentException();
		
		cursor = cursor.getConfig()[position];

	}
	
	/**
	 * [Builds the GameTree from the current state the game is in.]
	 * 
	 * @param root
	 *     [GameBoardNode: current state of the game]
	 * @param turn
	 *     [Box: the turn to be made when building the GameTree.]
	 * @return
	 *     [GameBoardNode: Root of the GameTree built from the 
	 *     current game state.]
	 * @throws CloneNotSupportedException
	 */
	
	public static GameBoardNode buildTree(GameBoardNode root, Box turn) {
		
		if(win(root))
			return root;
		if(full(root))
			return root;
		
		for(int i = 0; i < root.getConfig().length; i++) {
			
			GameBoardNode clonedNode = (GameBoardNode) root.clone();
			
			if(clonedNode.getBoard().getBoard()[i] == Box.EMPTY) {
				
				clonedNode.getBoard().getBoard()[i] = turn;
			
				clonedNode.setCurrentTurn(invert(turn));
				
				clonedNode.setProbabilites();
				
				root.getConfig()[i] = clonedNode;
				
				buildTree(root.getConfig()[i], root.getConfig()[i].getCurrentTurn());
			
			} else {
				
				root.getConfig()[i] = null;
				
			}
			
		}
		
		return root;
		
	}
	
	/**
	 * [Check to see if given node is a win state.]
	 * 
	 * @param root
	 *     [GameBoardNode: Node to be checked for win.]
	 * @return
	 *     [boolean: true if win (for either player), 
	 *     false otherwise.]
	 */
	
	public static boolean win(GameBoardNode root) {

		if(root.getBoard().getBoard()[0] != Box.EMPTY && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[1] && 
				root.getBoard().getBoard()[1] == root.getBoard().getBoard()[2]) {
			
			return true;
			
		} else if(root.getBoard().getBoard()[3] != Box.EMPTY && 
				root.getBoard().getBoard()[3] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[5]) {
			
			return true;
			
		} else if(root.getBoard().getBoard()[6] != Box.EMPTY && 
				root.getBoard().getBoard()[6] == root.getBoard().getBoard()[7] && 
				root.getBoard().getBoard()[7] == root.getBoard().getBoard()[8]) {
			
			return true;
			
		} else if(root.getBoard().getBoard()[0] != Box.EMPTY && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[3] && 
				root.getBoard().getBoard()[3] == root.getBoard().getBoard()[6]) {
			
			return true;
			
		} else if(root.getBoard().getBoard()[1] != Box.EMPTY && 
				root.getBoard().getBoard()[1] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[7]) {
			
			return true;
			
		} else if(root.getBoard().getBoard()[2] != Box.EMPTY && 
				root.getBoard().getBoard()[2] == root.getBoard().getBoard()[5] && 
				root.getBoard().getBoard()[5] == root.getBoard().getBoard()[8]) {
			
			return true;
			
		} else if(root.getBoard().getBoard()[0] != Box.EMPTY && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[8]) {
			
			return true;
			
		} else if(root.getBoard().getBoard()[2] != Box.EMPTY && 
				root.getBoard().getBoard()[2] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[6]) {
			
			return true;
			
		}
		
		return false;
		
	}
	
	/**
	 * [Check if given board is full (draw) or not.]
	 * 
	 * @param root
	 *     [GameBoardNode: given node to be checked.]
	 * @return
	 *     [boolean: false if not full, true if full.]
	 */
	
	public static boolean full(GameBoardNode root) {
		
		for(int i = 0; i < 9; i++) {
			
			if(root.getBoard().getBoard()[i] == Box.EMPTY) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	/**
	 * [Takes in a Box symbol and inverts it to
	 * it's opposite symbol.]
	 * 
	 * @param turn
	 *     [Turn to be inverted.]
	 * @return
	 *     [Inverted turn.]
	 */
	
	public static Box invert(Box turn) {
		
		if(turn == Box.X) {
			
			turn = Box.O;
			
		} else if(turn == Box.O) {
			
			turn = Box.X;
			
		}
		
		return turn;
		
	}
	
	/**
	 * [Checks whether the passed GameBoardNode's configuration is 
	 * a winning state or not.]
	 * 
	 * @param node
	 *     [GameBoardNode: GameBoardNode passed in to be checked
	 *     for winning state.]
	 * @return
	 *     [Box: winners symbol (X or O) if GamrBoardNode's state
	 *     is a winning state.]
	 */
	
	public static Box checkWin(GameBoardNode node) {
		
		Box wL = isWin(node);
		
		node.setWinner(wL);
		
		return wL;
		
	}
	
	/**
	 * [Check to see if given node is a win state or not.]
	 * 
	 * @param root
	 *     [GameBoardNode: Node to be checked for win, lose, or draw.]
	 * @return
	 *     [box: Box symbol of the winner (X, O, or EMPTY if draw). Null if not a leaf.]
	 */
	
	public static Box isWin(GameBoardNode root) {
		
		Box wL = null;
		
		if(root.getBoard().getBoard()[0] != Box.EMPTY && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[1] && 
				root.getBoard().getBoard()[1] == root.getBoard().getBoard()[2]) {
			
			wL = root.getBoard().getBoard()[0];
			
		} else if(root.getBoard().getBoard()[3] != Box.EMPTY && 
				root.getBoard().getBoard()[3] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[5]) {
			
			wL = root.getBoard().getBoard()[3];
			
		} else if(root.getBoard().getBoard()[6] != Box.EMPTY && 
				root.getBoard().getBoard()[6] == root.getBoard().getBoard()[7] && 
				root.getBoard().getBoard()[7] == root.getBoard().getBoard()[8]) {
			
			wL = root.getBoard().getBoard()[6];
			
		} else if(root.getBoard().getBoard()[0] != Box.EMPTY && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[3] && 
				root.getBoard().getBoard()[3] == root.getBoard().getBoard()[6]) {
			
			wL = root.getBoard().getBoard()[0];
			
		} else if(root.getBoard().getBoard()[1] != Box.EMPTY && 
				root.getBoard().getBoard()[1] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[7]) {
			
			wL = root.getBoard().getBoard()[1];
			
		} else if(root.getBoard().getBoard()[2] != Box.EMPTY && 
				root.getBoard().getBoard()[2] == root.getBoard().getBoard()[5] && 
				root.getBoard().getBoard()[5] == root.getBoard().getBoard()[8]) {
			
			wL = root.getBoard().getBoard()[2];
			
		} else if(root.getBoard().getBoard()[0] != Box.EMPTY && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[8]) {
			
			wL = root.getBoard().getBoard()[0];
			
		} else if(root.getBoard().getBoard()[2] != Box.EMPTY && 
				root.getBoard().getBoard()[2] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[6]) {
			
			wL = root.getBoard().getBoard()[2];
			
		} else if(full(root)) {
			
			wL = Box.EMPTY;
			
		}
		
		return wL;
		
	}
	
	/**
	 * [Returns the current probability of winning
	 * for the GameBoardNode configuration at the cursor.]
	 * 
	 * @return
	 *     [double: winning probability.]
	 */
	
	public double cursorProbability() {
		
		double win = (double) cursor.getTotalWinCount() / (double) cursor.getTotalLeafCount();

		return win;
		
	}
	
	/**
	 * [Gets the node that cursor is pointing too.]
	 * 
	 * @return
	 *     [GameBoardNode: the node the cursor is pointing
	 *     too.]
	 */

	public GameBoardNode getCursor() {
		
		return cursor;
		
	}
	
	/**
	 * [Set the cursor to the given node cursor.]
	 * 
	 * @param cursor
	 *     [GameBoardNode: given node to set the cursor
	 *     too.]
	 */

	public void setCursor(GameBoardNode cursor) {
		
		this.cursor = cursor;
		
	}
	
}
