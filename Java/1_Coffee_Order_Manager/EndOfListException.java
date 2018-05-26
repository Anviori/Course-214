/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 2
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_2;

@SuppressWarnings("serial")
public class EndOfListException extends Exception {

	/**
	 * [Exception for end of list is ] 
	 * 
	 * @param message
	 *     [message informing user that the OrderListNode
	 *     they attempted to access does not exist.]
	 */
	
	public EndOfListException(String message) {
		
		super(message);
		
	}
	
}
