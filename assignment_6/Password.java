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
public class Password implements Serializable{
	
	private String password;
	
	/**
	 * [Default constructor for a Password object.]
	 * 
	 * @param initPassword
	 *     [String: Given password to be encapsulated in the object.]
	 * @throws IllegalArgumentException
	 *     [If password doesn't satisfy the following requirements:
	 *     At least 1 upper-case letter.
	 *     At least 1 number.
	 *     At least 1 special character (!@#$%^&*).
	 *     At least 1 lower-case letter.]
	 */
	
	public Password(String initPassword) throws IllegalArgumentException{
		
		initPassword.trim();
		String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*])(?=\\S+$).{4,}$";
		
		if(!initPassword.matches(pattern))
			throw new IllegalArgumentException();
		
		password = initPassword;
		
	}
	
	/**
	 * [Gets the password of the user.]
	 * 
	 * @return
	 *     [String: password variable.]
	 */
	
	public String getPassword() {
		
		return password;
		
	}

}
