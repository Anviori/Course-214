/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 3
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_3;

public class UndoRedoStack {
	
	private Node top;
	private int size;
	
	/**
	 * [Gets the size of the 
	 * stack and returns it.]
	 * 
	 * @return
	 *     [The size of the stack.]
	 */
	
	public int getSize() {	
		
		return size;
		
	}

	/**
	 * [Initializes the top of the stack to
	 * null (empty).]
	 * 
	 */
	
	public UndoRedoStack() {
		
		top = null;
		
	}
	
	/**
	 * [Pushes 'a' onto the top of the backing data structure.]
	 * 
	 * @param a
	 *    [ActionCommand to be pushed onto the backing data
	 *    structure.]
	 */
	
	public void push(ActionCommand a) {		

		Node newNode = new Node(a);
		
		if(top == null)
			
			top = newNode;
		
		else {
	
			newNode.setNext(top);
			top = newNode;
			
		}
		
		size++;
			
	}
	
	/**
	 * [Takes the ActionCommand on-top of the backing data structure,
	 * saves the value, removes the ActionCommand from the backing 
	 * data structure and returns the removed ActionCommand.]
	 * 
	 * @return
	 *     [Removed ActionCommand]
	 * @throws EmptyStackException
	 *     [If the stack was empty]
	 */
	
	public ActionCommand pop() throws EmptyStackException{
		
		if(isEmpty())
			throw new EmptyStackException(null);
	
		ActionCommand command = top.getData();
		top = top.getNext();
		
		size--;
		
		return command;
		
	}
	
	/**
	 * [Takes the ActionCommand on-top of the backing data structure
	 * and returns the value (does not remove the ActionCommand
	 * from the backing data structure).]
	 * 
	 * @return
	 *    [The value of the of the ActionCommand on-top of the 
	 *    backing data structure.]
	 * @throws EmptyStackException
	 *    [If the stack was empty.]
	 */
	
	public ActionCommand peek() throws EmptyStackException {
	
		if(isEmpty())
			throw new EmptyStackException(null);
		
		return top.getData();
		
	}
	
	/**
	 * [Checks to see if the stack is empty or not.]
	 * 
	 * @return
	 *     [True if stack is empty, False if not]
	 */
	
	public boolean isEmpty() {
		
		return top == null;
		
	}
	
	/**
	 * [Clears the stack. Used when whenever a new
	 * action is done on a ActionCommand object to clear
	 * the redo stack.] 
	 * 
	 */
	
	public void clear() {
		
		top = null;

	}
	
	/**
	 * [toString method used to clearly and neatly print out
	 * the both the undo stack and the re-do stack.]
	 * 
	 */
	
	public String toString() {
		
		String s = "";
		
		if(isEmpty()) 
			return String.format("%29s", "[Stack is Empty]");

		Node tempNode = top;
		
		while(tempNode != null) {
			
			if(tempNode.getData().getType() == ActionType.SWAP) {
				
				s += tempNode.getData().getType() + " position " + (tempNode.getData().getPositionOne() + 1)
						+ " and position " +(tempNode.getData().getPositionTwo() + 1) + "\n";
				
				tempNode = tempNode.getNext();
				
			} else if(tempNode.getData().getType() == ActionType.MOVE) {
				
				s += tempNode.getData().getType() + " position " + (tempNode.getData().getPositionOne() + 1)
						+ " to position " +(tempNode.getData().getPositionTwo() + 1) + "\n";
				
				tempNode = tempNode.getNext();
				
			} else {
				
				s += tempNode.getData().getType() + " " + tempNode.getData().getPhoto() 
						+ " in position " + (tempNode.getData().getPositionOne() + 1) + "\n";

				tempNode = tempNode.getNext();
				
			}
			
		}
		
		return s;
		
	}

}