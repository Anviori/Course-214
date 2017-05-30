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
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Account implements Serializable{
	
	private List<User> followers;
	private List<User> following;
	private String name;
	private Password password;
	
	public Account(String name, Password password) {
		
		this.name = name;
		this.password = password;
		
		followers = new ArrayList<User>();
		following = new ArrayList<User>();
		
	}
	
	/**
	 * [Gets the list of followers of the User.]
	 * 
	 * @return
	 *     [List<User>: returns the list of followers
	 *     of the current User.]
	 */
	
	public List<User> getFollowers() {
		
		return followers;
		
	}
	
	/**
	 * [Adds a follower to the users list of followers.]
	 * 
	 * @param followers
	 *     [User: given user to add to follower list.]
	 */
	
	public void setFollowers(User user) {
		
		this.followers.add(user);
		
	}
	
	/**
	 * [Gets the list of Users the current user
	 * is following.]
	 * 
	 * @return
	 *     [List<User>: Users user is following.]
	 */
	
	public List<User> getFollowing() {
		
		return following;
		
	}
	
	/**
	 * [Adds a User to the users follow list.]
	 * 
	 * @param following
	 *     [User: User that the user wants to follow.]
	 */
	
	public void setFollowing(User user) {
		
		this.following.add(user);
		
	}
	
	 /**
	 * [Gets the name of the User in this account.]
	 * 
	 * @param name
	 *     [String: User name.]
	 */
	
	public String getName() {
		
		return name;
		
	}
	
	 /**
	 * [Get the password of the user.]
	 * 
	 * @return
	 *     [Password: password variable.]
	 */
	
	public Password getPassword() {
		
		return password;
		
	}
	
}
