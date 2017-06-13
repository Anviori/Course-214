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
import java.util.Map;

@SuppressWarnings("serial")
public class AccountDatabase implements Serializable {
	
	private HashMap<String, Account> aDatabase;
	
	/**
	 * [Default constructor that creates the AccountDatabase
	 * using a HashMap.]
	 * 
	 */
	
	public AccountDatabase() {
		
		aDatabase = new HashMap<String, Account>();
		
	}
	
	/**
	 * [Gets the AccoutDatabase HashMap.]
	 * 
	 * @return
	 *     [HashMap<String, Account>: AccountDatabase.]
	 */
	
	public HashMap<String, Account> getDatabase() {
		
		return aDatabase;
		
	}
	
	/**
	 * [Adds a new Account into the AccountDatabase
	 * using the given email as a key.]
	 * 
	 * @param email
	 *     [String: given e-mail to be used to add the Account
	 *     into the Account Database.]
	 * @param account
	 *     [Account: Account to be stored in the database.]
	 */
	
	public void addAccount(String email, Account account) throws IllegalArgumentException {
		
		if(email == null || aDatabase.containsKey(email))
			throw new IllegalArgumentException();
		
		aDatabase.put(email, account);
		
	}
	
	/**
	 * [This method retrieves the Account from the database that has
	 * the given email. If the email is not in the database, returns null.]
	 * 
	 * @param email
	 *     [String: email used retrieve Account.]
	 * @return
	 *     [Account: Account retrieved from database.]
	 */
	
	public Account getAccountInformation(String email) {
		
		return aDatabase.get(email);
		
	}
	
	/**
	 * [Removes an account from the Account database.]
	 * 
	 * @param eamil
	 *     [String: Key used to delete account.]
	 */
	
	public void removeAccount(String email) throws IllegalArgumentException {
		
		if(email == null || !aDatabase.containsKey(email))
			throw new IllegalArgumentException();
		
		aDatabase.remove(email);
		
	}
	
	/**
	 * [Prints out all the users in the Account Database.]
	 * 
	 */
	
	public String toString() {
		
		String allUsers = "\nAll Users: \n\n"
				+ String.format("%-30s%-40s", "E-mail", "Name")
				+ "\n------------------------------------------------------";
		
		if(!aDatabase.isEmpty()) {
		
			for(Map.Entry<String, Account> entry : aDatabase.entrySet()) {
				
				String key = entry.getKey();
				String value = entry.getValue().getName();
				
				allUsers += String.format("\n%-30s%-40s", key, value);
				
			}
			
		} else {
			
			allUsers += "\n[No Users in Database]";
			
		}
		
		return allUsers += "\n";
		
	}

}
