/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 3
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_3;

@SuppressWarnings("serial")
public class EmptyStackException extends Exception {

	/**
	 * [Exception for when the stack is empty.] 
	 * 
	 * @param message
	 *     [Message informing the user that the stack
	 *     they attempted to access is currently empty.]
	 */
	
	public EmptyStackException(String message) {
		
		super(message);
		
	}
	
}
