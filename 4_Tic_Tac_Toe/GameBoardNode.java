/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 5
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_5;

public class GameBoardNode {
	
	private GameBoard board;
	private boolean isEnd;
	private Box currentTurn;
	private Box winner;
	private GameBoardNode config[];
	private double winProb;
	private double loseProb;
	private double drawProb;
	
	/**
	 * [Default constructor to create the configurations 
	 * of the GameBoard based on the configuration of the
	 * board and whose turn it currently is.]
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [currentTurn != Box.EMPTY & board is not null.]
	 * @param board
	 *     [GameBoard: board being used to create
	 *     the node reference in the config array.]
	 * @param currentTurn
	 *     [Box: current play of whoever's turn it is.]
	 */
	
	public GameBoardNode(GameBoard board, Box currentTurn) {

		isEnd = false;
		this.board = board;
		this.currentTurn = currentTurn;
		config = new GameBoardNode[9];
	
	}
	
	/**
	 * [Empty constructor. Used for cloning.]
	 * 
	 */
	
	public GameBoardNode() {
		
		this.board = new GameBoard();
		this.config = new GameBoardNode[9];
		
	}
	
	/**
	 * [Sets all the probabilities of winning, losing, and 
	 * drawing from the current configuration of the GameBoard.]
	 * 
	 */
	
	public void setProbabilites() {

		int totalLeaves = getTotalLeafCount();
		int winLeaves = getTotalWinCount();
		int loseLeaves = getTotalLoseCount();
		int drawLeaves = getTotalDrawCount();

		this.winProb = (double) winLeaves / (double) totalLeaves;
		this.loseProb = (double) loseLeaves / (double) totalLeaves;
		this.drawProb = (double) drawLeaves / (double) totalLeaves;
		
	}
	
	/**
	 * [Get the total number of leaves in
	 * the current subtree.]
	 * 
	 * @return
	 *     [int: total number of leaves.]
	 */
	
	public int getTotalLeafCount() {

		int count = 0;
		
		if(leaf(this)) 
			return 1;
		
		for(int i = 0; i < this.config.length; i++) {

			if(this.config[i] != null) {
				
				count = count + this.config[i].getTotalLeafCount();
			
			}
				
		}
		
		return count;
		
	}
	
	/**
	 * [Checks if the current GameBoardNode instance 
	 * is a leaf or not.]
	 * 
	 * @return
	 *     [boolean: false is not leaf, true is leaf.]
	 */
	
	public boolean leaf(GameBoardNode leaf) {
		
		for(int i = 0; i < this.config.length; i ++) {
			
			if(leaf.config[i] != null) {
				
				return false;
				
			}
			
		}
		
		return true;
		
	}
	
	/**
	 * [Get the total number of winning leaves in the 
	 * current subtree.]
	 * 
	 * @return
	 *     [int: total number of winning leaves.]
	 */
	
	public int getTotalWinCount() {
		int count = 0;
		
		if(leaf(this)) 
			if(winOrLose(this, 1))
				return 1;
		
		for(int i = 0; i < this.config.length; i++) {

			if(this.config[i] != null) {
				
				count = count + this.config[i].getTotalWinCount();
			
			}
				
		}
		
		return count;
		
	}
	
	/**
	 * [Check if passed in node is a win.]
	 * 
	 * @param root
	 *     [GameBoardNode: Given node to check for win.]
	 * @return
	 *     [boolean: true if win, false otherwise.]
	 */
	
	public static boolean winOrLose(GameBoardNode root, int choice) {
		
		boolean win = false;
		
		Box a = null; 
		
		if(choice == 0){
			
			a = Box.X;
			
		} else if (choice == 1) {
			
			a = Box.O;
			
		}
		
		if(root.getBoard().getBoard()[0] == a && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[1] && 
				root.getBoard().getBoard()[1] == root.getBoard().getBoard()[2]) {
			
			win = true;
			
		} else if(root.getBoard().getBoard()[3] == a && 
				root.getBoard().getBoard()[3] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[5]) {
			
			win = true;
			
		} else if(root.getBoard().getBoard()[6] == a && 
				root.getBoard().getBoard()[6] == root.getBoard().getBoard()[7] && 
				root.getBoard().getBoard()[7] == root.getBoard().getBoard()[8]) {
			
			win = true;
			
		} else if(root.getBoard().getBoard()[0] == a && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[3] && 
				root.getBoard().getBoard()[3] == root.getBoard().getBoard()[6]) {
			
			win = true;
			
		} else if(root.getBoard().getBoard()[1] == a && 
				root.getBoard().getBoard()[1] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[7]) {
			
			win = true;
			
		} else if(root.getBoard().getBoard()[2] == a && 
				root.getBoard().getBoard()[2] == root.getBoard().getBoard()[5] && 
				root.getBoard().getBoard()[5] == root.getBoard().getBoard()[8]) {
			
			win = true;
			
		} else if(root.getBoard().getBoard()[0] == a && 
				root.getBoard().getBoard()[0] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[8]) {
			
			win = true;
			
		} else if(root.getBoard().getBoard()[2] == a && 
				root.getBoard().getBoard()[2] == root.getBoard().getBoard()[4] && 
				root.getBoard().getBoard()[4] == root.getBoard().getBoard()[6]) {
			
			win = true;
			
		}
		
		return win;
		
	}
	
	/**
	 * [Get the total number of losing leaves in the 
	 * current subtree.]
	 * 
	 * @return
	 *     [int: total number of losing leaves.]
	 */
	
	public int getTotalLoseCount() {
		
		int count = 0;
		
		if(leaf(this)) 
			if(winOrLose(this, 0))
				return 1;
		
		for(int i = 0; i < this.config.length; i++) {

			if(this.config[i] != null) {
				
				count = count + this.config[i].getTotalLoseCount();
			
			}
				
		}
		
		return count;
		
	}
	
	/**
	 * [Get the total number of draw leaves in the 
	 * current subtree.]
	 * 
	 * @return
	 *     [int: total number of draw leaves.]
	 */
	
	public int getTotalDrawCount() {
		
		int count = 0;
		
		if(leaf(this) && !winOrLose(this, 1) && !winOrLose(this, 0))
			return 1;
		
		for(int i = 0; i < this.config.length; i++) {

			if(this.config[i] != null) {
				
				count = count + this.config[i].getTotalDrawCount();
			
			}
				
		}
		
		return count;
		
	}

	/**
	 * [Creates and returns the String representation of the 
	 * GameBoard configuration in the current GameBoardNode.]
	 * 
	 */

	public String toString() {

		String board = "\n";
		
		for(int i = 0; i < 9; i++) {
			
			if(i == 3 || i == 6) {
				
				board += "\n";
				
			}
			
			if(getBoard().getBoard()[i] == Box.EMPTY)
				board += "|_|";
			else if(getBoard().getBoard()[i] == Box.X)
				board += "|X|";
			else if(getBoard().getBoard()[i] == Box.O)  
				board += "|O|";

		}
		
		board += String.format("\n\nProbability of winning is: %.2f", loseProb);
		board += String.format("\nProbability of losing is: %.2f", winProb);
		board += String.format("\nProbability of a draw is: %.2f", drawProb);
		
		return board;
		
	}
	
	/**
	 * [Gets the current turn symbol
	 * of the current game configuration.]
	 * 
	 * @return
	 *     [Box: currentTurn variable.]
	 */
	
	public Box getCurrentTurn() {
		
		return currentTurn;
		
	}
	
	public void setCurrentTurn(Box turn) {
		
		this.currentTurn = turn;
		
	}
	
	/**
	 * [Gets the win probability.} 
	 * 
	 * @return
	 *     [double: winProb variable.]
	 */
	
	public double getWinProb() {
		
		return winProb;
		
	}
	
	/**
	 * [Gets the lose probability.]
	 * 
	 * @return
	 *     [double: loseProb variable.]
	 */
	
	public double getLoseProb() {
		
		return loseProb;
		
	}
	
	/**
	 * [Gets the draw probability.]
	 * 
	 * @return
	 *     [double: drawProb variable.]
	 */
	
	public double getDrawProb() {
		
		return drawProb;
		
	}
	
	/**
	 * [Sets the winner variable to the 
	 * winner of the game of the GameBoardNode.]
	 * 
	 * @param winner
	 *     [Box: winner symbol passed in. (Box.EMPTY
	 *     if game is a draw.]
	 */
	
	public void setWinner(Box winner) {
		
		this.winner = winner;
		
	}
	
	/**
	 * [Sets the win probability to the given
	 * prob variable value.]
	 * 
	 * @param prob
	 *     [double: probability to me set.]
	 */
	
	public void setWinProb(double prob) {
		
		this.winProb = prob;
		
	}
	
	/**
	 * [Get the winner symbol.]
	 * 
	 * @return
	 *     [Box: The winner variable.]
	 */
	
	public Box getWinner() {
		
		return winner;
		
	}

	/**
	 * [Get the variable isEnd to determine if
	 * the end of the game has be reached.]
	 * 
	 * @return
	 *     [boolean: isEnd variable.]
	 */
	public boolean getIsEnd() {
		
		return isEnd;
		
	}
	
	/**
	 * [Sets the isEnd variable to true when
	 * the game is over.]
	 * 
	 * @param isEnd
	 *     [boolean: given boolean value.]
	 */
	
	public void setIsEnd(boolean isEnd) {
		
		this.isEnd = isEnd;
		
	}
	
	/**
	 * [Gets the current configuration of the
	 * board wrapped by a GameBoardNode.]
	 * 
	 * @return
	 *      [GameBoard: board wrapped by this GameBoardNode.]
	 */

	public GameBoard getBoard() {
		
		return board;
	
	}

	/**
	 * [Sets the board wrapped by the GameBoardNode
	 * to the given board configuration.]
	 * 
	 * @param board
	 *     [board: Given board Configuration.]
	 */
	
	public void setBoard(GameBoard board) {
	
		this.board = board;
	
	}

	/**
	 * [Gets the array of GameBoardNode references.]
	 * 
	 * @return
	 *     [GameBoardNode[]: config[] array.]
	 */
	
	public GameBoardNode[] getConfig() {
		
		return config;
		
	}
	
	/**
	 * [Set the config array.]
	 * 
	 * @param config
	 *     [GameBoardNode[]: GameBoardNode array to be used
	 *     to set the config array.]
	 */
	
	public void setConfig(GameBoardNode[] config) {
		
		this.config = config;
		
	}
	
	/**
	 * [Used to clone a GameBoardNode object.]
	 * 
	 */

	public Object clone() {
		
		GameBoardNode clone = new GameBoardNode();
		
		for(int i = 0; i < clone.getBoard().getBoard().length; i++) {
			
			clone.getBoard().getBoard()[i] = this.getBoard().getBoard()[i];
			
		}
		
		return clone;
		
	}

}