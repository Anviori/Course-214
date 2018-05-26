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
public class Bitter implements Serializable{
	
	private UserDatabase users;
	private AccountDatabase accounts;
	
	/*
	 * [Default constructor that initializes the databases.]
	 * 
	 */
	
	public Bitter() {
		
		users = new UserDatabase();
		accounts = new AccountDatabase();
		
	}
	
	/**
	 * [Inserts the User into the UserDatabase
	 * and inserts the Account into the AccountDatabase
	 * using the email as the key.]
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [E-mail does not already exist in the table.
	 *     user != null && account != null.]
	 * <dt><b>Postconditions:</b><dd>
	 *     [The User and Account have been inserted
	 *     into their respective databases with the 
	 *     indicated key.]
	 * 
	 * @param email
	 *     [String: email used as the key for the databases.]
	 * @param user
	 *     [User: User to be inserted into UserDatabase.]
	 * @param account
	 *     [Account: Account to be inserted into AccountDatabase.]
	 * @throws IllegalArgumentException
	 *     [If given email = null or if it's already stored in 
	 *     either of the two databases.]
	 */
	
	public void addUser(String email, User user, Account account) throws IllegalArgumentException {

		if(email == null || 
			users.getDatabase().containsKey(email) ||
			accounts.getDatabase().containsKey(email))
				throw new IllegalArgumentException();
		
		try {
		
			users.addUser(email, user);
			accounts.addAccount(email, account);
		
		} catch (IllegalArgumentException e) {
			
			System.out.println("Email is already in use. Try again.");
			
		}
	}
	
	/**
	 * [Removes a User from the Social Network.]
	 * 
	 * @param email
	 *     [String: Email to be used as the key for the databases.]
	 * @throws IllegalArgumentException
	 *     [If user with the given email is not in the social network.]
	 */

	public void removeUser(String email) throws IllegalArgumentException {
		
		if(email == null || 
			!users.getDatabase().containsKey(email) ||
			!accounts.getDatabase().containsKey(email))
				throw new IllegalArgumentException();
		
		users.removeUser(email);
		accounts.removeAccount(email);
		
	}
	
	/**
	 * [Gets the user database object.]]
	 * 
	 * @return
	 *     [UserDatabase: users object.]
	 */
	
	public UserDatabase getUserDatabase() {
		
		return users;
		
	}
	
	/**
	 * [Gets the account database object.]
	 * 
	 * @return
	 *     [AccountDatabase: accounts object.]
	 */
	
	public AccountDatabase getAccountDatabase() {
		
		return accounts;
		
	}
	
}
