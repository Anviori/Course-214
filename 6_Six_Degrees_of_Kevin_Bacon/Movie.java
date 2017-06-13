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
import java.util.List;
import big.data.DataSource;

public class Movie implements Comparable<Movie> {

	private String title;
	private List<Actor> actors;
	private int year;
	protected static int flag;
	
	/**
	 * [Gets the title of the movie.]
	 * 
	 * @return
	 *     [String: title variable]
	 */
	
	public String getTitle() {
		return title;
	}
	
	/**
	 * [Sets the title of the movie.]
	 * 
	 * @param title
	 *     [String: title variable]
	 */
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * [Gets the list of actors in the current movie.]
	 * 
	 * @return
	 *     [List<Actor>: actors list]
	 */
	
	public List<Actor> getActors() {
		return actors;
	}
	
	/**
	 * [Sets the lists of actors in the current movie.]
	 * 
	 * @param actors
	 *     [Actor: actor object to add to list]
	 */
	
	public void setActors(Actor actor) {
		actors.add(actor);
	}
	
	/**
	 * [Gets the year the movie was created.]
	 * 
	 * @return
	 *     [int: year variable]
	 */
	
	public int getYear() {
		return year;
	}
	
	/**
	 * [Sets the year the movie was made.]
	 * 
	 * @param year
	 *     [int: year variable]
	 */
	
	public void setYear(int year) {
		this.year = year;
	}
	
	/**
	 * [Constructor that loads a movie using the passed in title
	 * to create a URL and makes a new Movie object from it.]
	 * 
	 * @param title
	 *     [String: title of movie]
	 */
	
	public Movie(String title) {
		
		ActorGraph graphTemp = KBCalculator.graph;
		flag = 0;
		
		String prefix = "http://www.omdbapi.com/?t=";
		String postfix = "&y=&plot=short&r=xml";
		this.actors = new ArrayList<Actor>();
		
		try {
		    
		    if(title.length() > 0){
		   	 
		        DataSource ds = DataSource.connectXML(prefix + title.replace(' ' , '+') + postfix);
		        ds.load();
		        
		        String movie = ds.fetchString("movie/title");
		        String actors = ds.fetchString("movie/actors");
		        int year = ds.fetchInt("movie/year");
		        String[] actorsArr;
		        
		        System.out.println("\n" + movie + " (" + year + ")" + " Starring: " 
		        + actors + "\n");
		        
		        actorsArr = actors.split(", ");
		       
		        this.setTitle(movie);
		        this.setYear(year);
		        
		        for(int i = 0; i < actorsArr.length; i++) {
		        	
		        	if(graphTemp.getActorsByName().containsKey(actorsArr[i])) {
		        		
		        		graphTemp.getActorsByName().get(actorsArr[i]).setMovies(this);
		        		this.setActors(graphTemp.getActorsByName().get(actorsArr[i]));
		        		
		        		continue;
		        		
		        	}

		        	Actor actor = new Actor();
		        	actor.setName(actorsArr[i]);
		        	actor.setMovies(this);
		        	this.setActors(actor);
		        	
		        }
		        
		        for(int i = 0; i < this.actors.size(); i++) {
		        	
		        	for(int j = 0; j < this.actors.size(); j++) {
		        	
		        		if(this.actors.get(j) != this.actors.get(i)) {
		        			
		        			this.actors.get(i).setFriends(this.actors.get(j));
		        			
		        		}
		        		
		        	}
		        	
		        	graphTemp.setActorsByName(this.actors.get(i).getName(), this.actors.get(i));
		        	
		        }
		    
		    }
		    
	    } catch (big.data.DataInstantiationException e) {
	    	
	    	System.out.println("\nMovie could not be found.");
	    	flag = 1;
	    	
	    }
		
	}
	
	/**
	 * [Compares the passed in Movie object to the current one and
	 * sorts the two with regards to their titles.]
	 * 
	 */

	@Override
	public int compareTo(Movie o) {
		
		int length1 = this.title.length();
		int length2 = o.title.length();
		int lim = Math.min(length1, length2);
		char a[] = this.title.toCharArray();
		char b[] = o.title.toCharArray();
		
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
