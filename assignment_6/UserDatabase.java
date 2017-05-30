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
import java.util.HashMap;

@SuppressWarnings("serial")
public class UserDatabase implements Serializable{
	
	private HashMap<String, User> uDatabase;
	
	/**
	 * [Constructor that creates a Hash Map used
	 * for the database.]
	 * 
	 */
	
	public UserDatabase() {
		
		uDatabase = new HashMap<String, User>();
		
	}
	
	/**
	 * [Gets the Hash Map database.]
	 * 
	 * @return
	 *     [HashMap<String, User>: UserDatabase.]
	 */
	
	public HashMap<String, User> getDatabase() {
		
		return uDatabase;
		
	}
	
	/**
	 * [Add a new User into the UserDatabase
	 * using the given email as the key.]
	 * 
	 * @param email
	 *     [String: Given user e-mail.]
	 * @param user
	 *     [User: Given user name.]
	 * @throws IllegalArgumentException
	 *     [If the given email is null or if the 
	 *     email is already in the database.]
	 */
	
	public void addUser(String email, User user) throws IllegalArgumentException {

		if(email == null || uDatabase.containsKey(email)) 
			throw new IllegalArgumentException(); 
		
		uDatabase.put(email, user);
		
	}
	
	/**
	 * [Retrieves the user from the database using the 
	 * given e-mail. Null is returned if email is not
	 * in the database.]
	 * 
	 * @param email
	 *     [String: e-mail used retrieve user.]
	 * @return
	 *     [User: the user in the database.]
	 */
	
	public User getUser(String email) {
	
		return uDatabase.get(email);
			
	}
	
	/**
	 * [Removes the user from the UserDatabase.]
	 * 
	 * @param email
	 *     [String: ]
	 * @throws IllegalArgumentException
	 *     [If the given email is null or if the user 
	 *     does not exist in the database.]
	 */
	
	public void removeUser(String email) throws IllegalArgumentException {
		
		if(email == null || !uDatabase.containsKey(email))
			throw new IllegalArgumentException();
		
		uDatabase.remove(email);
		
	}

}