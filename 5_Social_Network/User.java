/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 6
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_6;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{

	private String name; //Doesn't have to be unique.

	/**
	 * [Gets the name of the user.]
	 * 
	 * @return
	 *     [String: name of user.]
	 */
	
	public String getName() {
		
		return name;
		
	}

	/**
	 * [Sets the name of the user.]
	 * 
	 * @param name
	 *     [String: name of the user.]
	 */
	
	public void setName(String name) {
		
		this.name = name;
	
	}
	
}
