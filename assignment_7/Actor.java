/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 7
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Actor implements Comparable<Actor> {

	private String name;
	private List<Movie> movies;
	private List<Actor> friends;
	private boolean visited;
	private LinkedList<String> path;
	
	/**
	 * [Default constructor that initializes the member variables.]
	 * 
	 */
	
	public Actor() {
		
		movies = new ArrayList<Movie>();
		friends = new ArrayList<Actor>();
		path = new LinkedList<String>();
		visited = false;
		
	}
	
	/**
	 * [Get the name of the actor.]
	 * 
	 * @return
	 *     [String: name variable]
	 */
	
	public String getName() {
		return name;
	}
	
	/**
	 * [Set the actors name.]
	 * 
	 * @param name
	 *     [String: name of the actor]
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * [Get the List of Movies that the actor is or has been in.]
	 * 
	 * @return
	 *     [List<Movie>: list of movies]
	 */
	
	public List<Movie> getMovies() {
		return movies;
	}
	
	/**
	 * [Set the movies that the actor is or has been in.]
	 * 
	 * @param movies
	 *     [Movie: movie to add to list]
	 */
	
	public void setMovies(Movie movie) {
		movies.add(movie);
	}
	
	/**
	 * [Get the list of actors that share movies with the current actor.]
	 * 
	 * @return
	 *     [List<Actor>: list of actors]
	 */
	
	public List<Actor> getFriends() {
		return friends;
	}
	
	/**
	 * [Set the list of actors the current actor is associated with.]
	 * 
	 * @param friends
	 *     [Actor: Actor to be added to friends list]
	 */
	
	public void setFriends(Actor friend) {
		this.friends.add(friend);
	}
	
	/**
	 * [True if Actor has been visited when traversing the graph.
	 * Along with the path variable, this variable is modified when
	 * finding the shortest path and the breadth first traversal.]
	 * 
	 * @return
	 *     [boolean: visited variable]
	 */
	
	public boolean getVisited() {
		return visited;
	}
	
	/**
	 * [Sets the visited variable to true when the actor has been visited in the traversal.]
	 * 
	 * @param visited
	 *     [boolean: true if visited, false if not]
	 */
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	/**
	 * [Gets the path of the current Actor in the current traversal.]
	 * 
	 * @return
	 *     [LinkedList<String>: path variable]
	 */
	
	public LinkedList<String> getPath() {
		return path;
	}
	
	/**
	 * [Sets the path of the current Actor in the current traversal.]
	 * 
	 * @param path
	 *     [LinkedList<String>: path variable]
	 */
	
	public void setPath(LinkedList<String> path) {
		this.path = path;
	}
	
	/**
	 * [Compares the passed in Actor object to the current one and
	 * sorts the two with regards to their names.]
	 * 
	 */

	@Override
	public int compareTo(Actor o) {

		int length1 = this.name.length();
		int length2 = o.name.length();
		int lim = Math.min(length1, length2);
		char a[] = this.name.toCharArray();
		char b[] = o.name.toCharArray();
		
		for(int i = 0; i < lim; i++) {
			
			char c = a[i];
			char d = b[i];
			
			if(c != d) {
				
				return c - d;
				
			}
			
		}
			
		return length1 - length2;
	}
	
}
