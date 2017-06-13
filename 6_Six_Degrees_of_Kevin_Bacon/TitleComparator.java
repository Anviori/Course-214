/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 7
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_7;

import java.util.Comparator;

public class TitleComparator implements Comparator<Movie> {

	/**
	 * [Compares the Movie objects's titles after Collections.sort is called.
	 * Used for alphabetically sorting the imported movies titles.]
	 * 
	 */
	
	@Override
	public int compare(Movie left, Movie right) {

		return (left.getTitle().compareTo(right.getTitle()));
				
	}
	
}
