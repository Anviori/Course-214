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

public class NameComparator implements Comparator<Actor> {

	/**
	 * [Compares the Actor objects's names after Collections.sort is called.
	 * Used for alphabetically sorting the imported actors' names.]
	 * 
	 */
	
	@Override
	public int compare(Actor left, Actor right) {

		return (left.getName().compareTo(right.getName()));

	}
	
}
