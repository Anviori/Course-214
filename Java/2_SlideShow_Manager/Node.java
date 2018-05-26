/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 3
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_3;

public class Node {

	private ActionCommand data;
	private Node next;
	
	/**
	 * [Initializes Node to the given ActionCommand.]
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [ActionCommand 'a' is not null.]
	 * <dt><b>Postconditions:</b><dd>
	 *     [The data of the node is set to the given
	 *     ActionCommand object and next has been set 
	 *     to null.]
	 * @param a
	 *     [ActionCommand to be wrapped by the node.]
	 * @throws
	 *     [If ActionCommand 'a' is null.]
	 */
	
	public Node(ActionCommand a) throws IllegalArgumentException {
		
		if(a == null) 
			throw new IllegalArgumentException();
		
		setData(a);
		setNext(null);

	}

	/**
	 * [Gets the Order data in the current Node.]
	 * 
	 * @return 
	 *     [data inside of the ActionCommand object.]
	 */
	
	public ActionCommand getData() {
		
		return data;
		
	}
	
	/**
	 * [Changes the previous ActionCommand object with
	 * a new one]
	 * 
	 * @param data
	 *     [ActionCommand object to be used]
	 */

	public void setData(ActionCommand data) {
		
		this.data = data;
		
	}
	
	/**
	 * [Gets the reference link to the next node in
	 * the list.]
	 * 
	 * @return
	 *     [Next node reference.]
	 */

	public Node getNext() {
		
		return next;
		
	}
	
	/**
	 * [Sets the 'next' reference of the node to
	 * another node.]
	 * 
	 * @param next
	 *     [Node reference.]
	 */

	public void setNext(Node next) {
		
		this.next = next;
		
	}
	
}