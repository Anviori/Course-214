/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 7
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_7;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class ActorGraph {
	
	private static HashMap<String,Actor> actorsByName;
	private static HashMap<String, Movie> moviesByTitle;
	
	public ActorGraph() {
		
		actorsByName = new HashMap<String, Actor>();
		moviesByTitle = new HashMap<String, Movie>();
		
	}
	
	/**
	 * [Returns the breadth first traversal starting with the Actor with the 
	 * passed in name.]
	 * 
	 * @param name
	 *     [String: name of Actor where the traversal will start]
	 * @return
	 *     [LinkedList<String>: breadth first traversal] 
	 */
	
	public static LinkedList<String> bfs(String name) {
		
		LinkedList<String> bfsList = new LinkedList<String>();
		Queue<Actor> list = new LinkedList<Actor>();
		Actor temp = new Actor();
		
		KBCalculator.graph.getActorsByName().get(name).setVisited(true);
		list.add(KBCalculator.graph.getActorsByName().get(name));
		
		while(!list.isEmpty()) {
			
			temp = list.remove();
			bfsList.add(temp.getName());
			
			for(int i = 0; i < temp.getFriends().size(); i++) {
				
				if(temp.getFriends().get(i).getVisited() == false) {
					
					KBCalculator.graph.getActorsByName().get(temp.getFriends().get(i).getName()).setVisited(true);
					list.add(KBCalculator.graph.getActorsByName().get(temp.getFriends().get(i).getName()));
					
				}
				
			}
			
		}

		return bfsList;
		
	}
	
	/**
	 * [Get the Actor object HashMap.]
	 * 
	 * @return
	 *     [HashMap<String, Actor>: actorsByName HashMap.]
	 */

	public HashMap<String,Actor> getActorsByName() {
		return actorsByName;
	}

	/**
	 * [Adds an actor to the actorsByName HashMap
	 * using the name as the key and the Actor object
	 * associated with that name.]
	 * 
	 * @param key
	 *     [String: name of Actor.]
	 * @param actor
	 *     [Actor: Actor object.]
	 */
	
	public void setActorsByName(String key, Actor actor) {
		actorsByName.put(key, actor);
	}

	/**
	 * [Get the Movie object HashMap.]
	 * 
	 * @return
	 *     [HashMap<String, Movie>: moviesByTitle HashMap.]
	 */
	
	public HashMap<String, Movie> getMoviesByTitle() {
		return moviesByTitle;
	}

	/**
	 * [Adds a movie to the moviesByTitle HashMap using
	 * the title of the Movie as the key and the Movie
	 * object associated with that movie title.]
	 * 
	 * @param key
	 *     [String: movie title.]
	 * @param movie
	 *     [Movie: Movie object.]
	 */
	
	public void setMoviesByTitle(String key, Movie movie) {
		moviesByTitle.put(key, movie);
	}

}
