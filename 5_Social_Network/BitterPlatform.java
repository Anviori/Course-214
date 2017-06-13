/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 6
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BitterPlatform {
	
	protected static Bitter bitter;
	private static Account account;
	private static User user;
	
	/**
	 * [Main method that prompts the user to log in, sign up or quit.]
	 * 
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException 
	 */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		bitter = new Bitter();
		InputStreamReader inStreamR = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStreamR);
		
		try {
		
			FileInputStream fileIn = new FileInputStream("Bitter.txt");
			ObjectInputStream inStream = new ObjectInputStream(fileIn);
			bitter = (Bitter) inStream.readObject();
			inStream.close();
			
		} catch (FileNotFoundException e) { }
				
		System.out.println("------------------------------\n"
				+ "      Welcome to Bitter!\n"
				+ "------------------------------\n");

		while(true) {
			
			System.out.print("------------------------------\n\n"
					+ "  L: Login\n"
					+ "  S: Sign Up\n"
					+ "  Q: Quit\n"
					+ "\n------------------------------\n");
			
			System.out.print("\nEnter option: ");
			String choice = input.readLine().toUpperCase();
					
			switch(choice) {
			
				case "L":
					
					login();
						
					break;

				case "S" :
					
					signUp();
					
					break;
					
				case "Q" :
					
					FileOutputStream fileOut = new FileOutputStream("Bitter.txt");
					ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
					outStream.writeObject(bitter);
					outStream.close();
					
					System.out.println("\nGoodbye!");
					System.exit(0);
					
					break;
	
				default:
					
					System.out.println("\nInvalid Input. Try Again.\n");
					
					break;
					
			}
			
		}
		
	}
	
	/**
	 * [Asks the user to login
	 * using their email and password.]
	 * 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 * 
	 */
	
	@SuppressWarnings("resource")
	public static void login() throws IOException, ClassNotFoundException {
		
		System.out.println("------------------------------\n"
				+ "            Login!\n"
				+ "------------------------------\n");
		
		Scanner input = new Scanner(System.in);
		String email, password;
		
		while(true) {
		
			System.out.print("Enter your e-mail: ");
			email = input.nextLine();
			System.out.print("Enter your password: ");
			password = input.nextLine();
			
			email.trim();
			
			if(bitter.getAccountDatabase().getDatabase().containsKey(email)) {
				
				if(bitter.getAccountDatabase().getDatabase().get(email).getPassword().getPassword().equals(password)) {
					
					account = bitter.getAccountDatabase().getDatabase().get(email);
					user = bitter.getUserDatabase().getDatabase().get(email);
					
					userMenu();
					
					break;
					
				} else {
					
					System.out.println("\nPassword is incorrect.\n");
					
					return;
					
				}
			
			} else {
				
				System.out.println("\nE-mail does not exist in database.\n");
				
				return;
				
			}
			
		}
		
	}
	
	/**
	 * [Prompts the user for email, name, and password. Then, 
	 * creates an Account and User object and sends them to the 
	 * bitter class to be put into the corresponding databases.]
	 * @throws IOException 
	 * 
	 */
	
	@SuppressWarnings("resource")
	public static void signUp() throws IOException {
		
		while (true) {
			
			try {
				
				System.out.println("\n------------------------------\n"
						+ "           Sign Up!\n"
						+ "------------------------------\n");
				
				user = new User();
				Password passW;
				Scanner input = new Scanner(System.in);
				String email, name, password;
				
				System.out.print("Enter your e-mail: ");
				email = input.nextLine();
				System.out.print("Enter your name: ");
				name = input.nextLine();
				
				while(true) {
					
					try {
						
						System.out.println("\nPassword must satisfy the following requirements: "
								+ "\n1. At least 1 upper-case letter"
								+ "\n2. At least 1 number"
								+ "\n3. At least 1 special character (!@#$%^&*)"
								+ "\n4. At least 1 lower-case letter");
						System.out.print("\nEnter your password: ");
						password = input.next();
						
						user.setName(name);
		
						passW = new Password(password);
						
						break;
					
					} catch (IllegalArgumentException e) {
						
						System.out.println("\nPassword does not satisfy given requirements. Try again.");
						
					}
				
				}
				
				account = new Account(name, passW);
				
				bitter.addUser(email, user, account);

				userMenu();
				
				break;
				
			} catch (IllegalArgumentException e) {
	
				System.out.println("\nEmail already exists. Enter a different one.");
				
			}
			
		}
	
	}
	
	/**
	 * [Menu after the user logs in or signs up.]
	 * @throws IOException 
	 * 
	 */
	
	public static void userMenu() throws IOException {
		
		InputStreamReader inStreamR = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStreamR);
		
		System.out.println("\n------------------------------\n"
				+ "         Logged in!\n"
				+ "------------------------------\n");

		while(true) {
			
			System.out.print("------------------------------\n\n"
					+ "  F: Follow someone\n"
					+ "  U: Unfollow someone\n"
					+ "  V: View Followers\n"
					+ "  S: See who you follow\n"
					+ "  A: List all users\n"
					+ "  L: Logout\n"
					+ "  C: Close your account\n"
					+ "\n------------------------------\n");
		 
			System.out.print("\nEnter option: ");
			String choice = input.readLine().toUpperCase();
					
			switch(choice) {
			
				case "F":
					
					follow();
											
					break;

				case "U" :
					
					unfollow();
					
					break;
					
				case "V" :
					
					viewFollowers();
					
					break;
	
				case "S" :
					
					viewFollowing();
					
					break;
					
				case "A" :
					
					allUsers();
					
					break;
					
				case "L" :
					
					return;
					
				case "C" :
					
					closeAccount();
					
					return;
					
				default:
					
					System.out.println("\nInvalid Input. Try Again.\n");
					
					break;
					
			}
			
		}
		
	}
	
	/**
	 * [Asks the user to enter the name
	 * of who they want to follow and, if the
	 * User exists, adds them to the users following list.]
	 * 
	 */
	
	@SuppressWarnings("resource")
	public static void follow() {
		
		Scanner input = new Scanner(System.in);
		String email;
		User user1;
		
		System.out.print("\nEnter the e-mail of the user you would like to follow: ");
		email = input.nextLine();
		
		user1 = bitter.getUserDatabase().getUser(email);
		
		if(user1 != null) {
			
			account.setFollowing(user1);
			bitter.getAccountDatabase().getAccountInformation(email).setFollowers(user);
			
		} else if (user1 == null){
			
			System.out.println("\nUser with assoiciated e-mail: '" + email + "' does not exist.\n");
			
		}
		
		if(user1 != null) {
			
			System.out.println("\nYou are now following " + user1.getName() + ".\n");
			
		}
		
	}
	
	/**
	 * [Asks user who they want to unfollow and, if they 
	 * are currently following them, removes them from the users 
	 * following list.]
	 * 
	 */
	
	@SuppressWarnings("resource")
	public static void unfollow() {
		
		Scanner input = new Scanner(System.in);
		String email;
		User user1;
		
		System.out.print("\nEnter the e-mail of the user you would like to unfollow: ");
		email = input.nextLine();
	
		if(bitter.getUserDatabase().getUser(email) != null) {
		
			user1 = bitter.getUserDatabase().getUser(email);
			
		} else {
			
			user1 = null;
			
		}
		
		if(user1 != null) {
			
			account.getFollowing().remove(user1);
			bitter.getAccountDatabase().getAccountInformation(email).getFollowers().remove(user);
			
			System.out.println("\nYou are no longer following " + user1.getName() + ".\n");
			
		} else if (user1 == null) {
			
			System.out.println("\nYou are not following this user.\n");
			
		}
		
	}
	
	/**
	 * [Displays the followers of the user.]
	 * 
	 */
	
	public static void viewFollowers() {
		
		List<User> usersFollowing = account.getFollowers();
		
		String follower = "\nYour followers: \n"
				+ String.format("\n%-30s%-40s", "E-mail", "Name")
				+ "\n------------------------------------------------------------------------------";
		
		for(Map.Entry<String, Account> entry : bitter.getAccountDatabase().getDatabase().entrySet()) {
			
			String key = entry.getKey();
			String value = entry.getValue().getName();
			
			for(int i = 0; i < usersFollowing.size(); i++) {
				
				if(value == usersFollowing.get(i).getName())
					follower += String.format("\n%-30s%-40s", key, value);
				
			}
			
		}
		
		if(account.getFollowers().isEmpty()) {
			
			follower += "\n[No One is Following You]";
			
		}

		System.out.println(follower + "\n");
		
	}
	
	/**
	 * [Displays the people the user is following.]
	 * 
	 */
	
	public static void viewFollowing() {
		
		List<User> usersFollowing = account.getFollowing();

		String following = "\nYou follow: \n"
				+ String.format("\n%-30s%-40s", "E-mail", "Name")
				+ "\n------------------------------------------------------------------------------";
		
		for(Map.Entry<String, Account> entry : bitter.getAccountDatabase().getDatabase().entrySet()) {
			
			String key = entry.getKey();
			String value = entry.getValue().getName();
			
			for(int i = 0; i < usersFollowing.size(); i++) {
				
				if(value == usersFollowing.get(i).getName())
					following += String.format("\n%-30s%-40s", key, value);
				
			}
			
		}
		
		if(account.getFollowing().isEmpty()) {
			
			following += "\n[You Are Not Following Anyone]";
			
		}

		System.out.println(following + "\n");
		
	}
	
	/**
	 * [Displays ALL users in the database.]
	 * 
	 */
	
	public static void allUsers() {
		
		System.out.println(bitter.getAccountDatabase().toString());
		
	}
	
	/**
	 * [Removes the users account from the Account Database.]
	 * 
	 */
	
	public static void closeAccount() {
		
		String name = account.getName();
		String email = "";
		
		for(Map.Entry<String, Account> entry : bitter.getAccountDatabase().getDatabase().entrySet()) {
			
			String key = entry.getKey();
			String value = entry.getValue().getName();
			
			if(value == name)
				email = key;
			
		}
		
		for(Map.Entry<String, Account> entry : bitter.getAccountDatabase().getDatabase().entrySet()) {
			
			entry.getValue().getFollowing().remove(bitter.getUserDatabase().getUser(email));
			entry.getValue().getFollowers().remove(bitter.getUserDatabase().getUser(email));
			
		}
		
		bitter.removeUser(email);
		
		System.out.println("\n" + name + "'s account has been closed.\n");
		
	}

}
