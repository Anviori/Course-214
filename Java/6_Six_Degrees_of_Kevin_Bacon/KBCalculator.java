/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 7
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class KBCalculator {
	
	protected static ActorGraph graph;

	/**
	 * [Prompts the user what they would like to do.]
	 * 
	 * @param args
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		graph = new ActorGraph();
		InputStreamReader inStreamR = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStreamR);	
		
		System.out.println("-------------------------------------------------------------\n"
				+ "                     Welcome to KBC!"
				+ "\n-------------------------------------------------------------\n");
		
		while(true) {
			
			System.out.print("Menu:\n-------------------------------------------------------------\n\n"
					+ "  I: Import a Movie\n"
					+ "  A: Print all Actors\n"
					+ "  M: Print all Movies\n"
					+ "  P: Print the shortest path between two actors\n"
					+ "  B: Print the BFS (Breadth First Search) from a given actor\n"
					+ "  L: Lookup Actor By Name\n"
					+ "  Q: Quit\n"
					+ "\n-------------------------------------------------------------\n");
			
			System.out.print("\nEnter option: ");
			String choice = input.readLine().toUpperCase();
					
			switch(choice) {
			
				case "I":
					
					importMovie();
						
					break;

				case "A" :
					
					printActors();
					
					break;
					
				case "M" :
					
					printMovies();
					
					break;
					
				case "P" :
					
					printShortestPath();
					
					break;
					
				case "B" :
	
					printBFS();
	
					break;
					
				case "L" :
					
					lookupActor();
					
					break;
					
				case "Q" :

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
	 * [Asks the user for a movie title and then 
	 * creates and imports the movie associated with that title
	 * into a HashMap of Movie objects. Also imports all the actors 
	 * associated with that movie into a different HashMap of Actor objects.]
	 * 
	 */
	
	public static void importMovie() {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String title = "";
		Movie movie;

		System.out.println("\n-------------------------------------------------------------");
		
	    System.out.print("\nEnter the movie title: ");
	    title = input.nextLine();
	    
	    if(!graph.getMoviesByTitle().containsKey(title)) {
	    	
	    	movie = new Movie(title);
	   
	    } else {
	    	
	    	System.out.println("\nMove has already been imported!\n");
	    	return;
	    	
	    }
	    
	    if(Movie.flag == 0) {
	    	
	    	graph.setMoviesByTitle(title, movie);
	    	System.out.println(movie.getTitle() + " Successfully Imported!\n");
	    	
	    }
	    
	    System.out.println("-------------------------------------------------------------\n");
	    
	}
	
	/**
	 * [Prints all the actors in the Actor object HashMap in
	 * alphabetical order.]
	 * 
	 */
	
	public static void printActors() {
		
		System.out.println("\nList of Actors in Alphabetical order:\n"
				+ "-------------------------------------------------------------");
		
		ArrayList<Actor> actors = new ArrayList<Actor>();
		
		for(Map.Entry<String, Actor> entry : graph.getActorsByName().entrySet()) {
			
			Actor value = entry.getValue();
			actors.add(value);
			
		}
		
		Collections.sort(actors, new NameComparator());
		
		for(int i = 0; i < actors.size(); i++) {
			
			System.out.println(actors.get(i).getName());
			
		}
		
		System.out.println("\n-------------------------------------------------------------\n");
		
	}
	
	/**
	 * [Prints all movies in the Movie object HashMap in 
	 * alphabetical order.]
	 * 
	 */
	
	public static void printMovies() {
		
		System.out.println("\nList of Movies in Alphabetical order:\n"
				+ "-------------------------------------------------------------");
		
		ArrayList<Movie> movies = new ArrayList<Movie>();
		
		for(Map.Entry<String, Movie> entry : graph.getMoviesByTitle().entrySet()) {
			
			Movie value = entry.getValue();
			movies.add(value);
			
		}
		
		Collections.sort(movies, new TitleComparator());
		
		for(int i = 0; i < movies.size(); i++) {
			
			System.out.println(movies.get(i).getTitle());
			
		}
		
		System.out.println("\n-------------------------------------------------------------\n");
		
	}
	
	/**
	 * [Prints the shortest path between two actors.]
	 * 
	 */
	
	public static void printShortestPath() {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String name1 = "";
		String name2 = "";
		List<String> list = new LinkedList<String>();
	
		System.out.println("\n-------------------------------------------------------------");
		
		System.out.print("\nEnter first name: ");
		name1 = input.nextLine();
		
		System.out.print("\nEnter second name: ");
		name2 = input.nextLine();
		
		list = findShortestPath(name1, name2);
		
		if(!list.isEmpty()) {
			
			System.out.println("\nShortest path between: " + name1 + " and " + name2);
			System.out.println("-------------------------------------------------------------");
		
			for(int i = 0; i < list.size(); i++) {
			
				System.out.println(list.get(i));
				
			} 
			
		}
		
		System.out.println("\n-------------------------------------------------------------\n");
		
	}
	
	/**
	 * [Finds the shortest path between two actors.]
	 * 
	 * @param name1
	 *     [String: first actor name.]
	 * @param name2
	 *     [String: second actor name.]
	 */
	
	public static List<String> findShortestPath(String start, String end) {
		
		LinkedList<String> bfsList = new LinkedList<String>();
		Queue<Actor> queue = new LinkedList<Actor>();
		Map<String, Actor> prev = new HashMap<String, Actor>();
		Actor current = graph.getActorsByName().get(start);
	
		queue.add(current);
		current.setVisited(true);
		
		while(!queue.isEmpty()) {
			
			current = queue.remove();;
		
			if(current.getName().equals(end)) {
				
				break;
				
			} else {
			
				for(int i = 0; i < current.getFriends().size(); i++) {
					
					if(current.getFriends().get(i).getVisited() == false) {
						
						queue.add(graph.getActorsByName().get(current.getFriends().get(i).getName()));
						graph.getActorsByName().get(current.getFriends().get(i).getName()).setVisited(true);
						prev.put(current.getFriends().get(i).getName(), current);
						
					}
					
				}
		
			}
			
		}
		
		if(!current.getName().equals(end)) {
			
			System.out.println("\nThere is no path between " + start + " and " + end);
			return Collections.emptyList();
			
		}
		
		for(Actor node = graph.getActorsByName().get(end); node != null; node = prev.get(node.getName())) {
			
			bfsList.add(node.getName());
			
		}

		Collections.reverse(bfsList);
		
		return bfsList;
		
	}
	
	/**
	 * [Print the Breadth First Search from a given actor.]
	 * 
	 */
	
	public static void printBFS() {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String name = "";
		LinkedList<String> list = new LinkedList<String>();
		
		System.out.println("\n-------------------------------------------------------------");
		
		System.out.print("\nEnter starting actor: ");
		name = input.nextLine();
		
		list = ActorGraph.bfs(name);
		
		System.out.println("\nBFS - With starting actor: " + name);
		System.out.println("-------------------------------------------------------------");
		
		for(int i = 0; i < list.size(); i++) {
			
			System.out.println(list.get(i));
			
		} 
		
		System.out.println("\n-------------------------------------------------------------\n");
		
	}
	
	/**
	 * [Looks up an Actor object using the given name.]
	 * 
	 */

	public static void lookupActor() {
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);
		String name = "";
		
		System.out.println("\n-------------------------------------------------------------");
		
		System.out.print("\nEnter name of actor: ");
		name = input.nextLine();
		
		System.out.println("\nActor: " + graph.getActorsByName().get(name).getName());
		System.out.println("\nFriends: ");
		
		for(int i = 0; i < graph.getActorsByName().get(name).getFriends().size(); i++) {
			
			System.out.println(graph.getActorsByName().get(name).getFriends().get(i).getName());
			
		}

		System.out.println("\nMovies: ");

		for(int i = 0; i < graph.getActorsByName().get(name).getMovies().size(); i++) {
			
			System.out.println(graph.getActorsByName().get(name).getMovies().get(i).getTitle());
			
		}
		
		System.out.println("\n-------------------------------------------------------------\n");
		
	}

}