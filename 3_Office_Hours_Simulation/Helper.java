/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 4
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_4;

public class Helper {
	
	private int timeLeftTilFree;
	private final boolean isProfessor;

	/**
	 * [Default constructor that creates a Helper Object and
	 * initializes the Objects timeLeftTilFree variable to 0.
	 * Helper can be either a TA or a Professor. ]
	 * 
	 * <dt><b>Postconditions:</b><dd>
	 *     [timeLeftTilFree variable = 0;]
	 * @param isProfessor
	 *     [boolean: true if helper is a Professor,
	 *     false if helper is a TA.]
	 */
	
	public Helper(boolean isProfessor) {

		this.isProfessor = isProfessor;
		timeLeftTilFree = 0;
		
	}
	
	/**
	 * [Get the value of the variable timeLeftTilFree.]
	 * 
	 * @return
	 *     [int: timeLeftTilFree variable.]
	 */
	
	public int getTimeLeftTilFree() {
		
		return timeLeftTilFree;
		
	}
	
	/**
	 * [Change the value of the timeLeftTilFree variable
	 * to the value in the given time variable.]
	 * 
	 * @param time
	 *     [int: time variable used to change the timeLeftTilFree
	 *     variable.]
	 */
	
	public void setTimeLeftTilFree(int time) {
		
		timeLeftTilFree = time;
		
	}

	/**
	 * [Get the the value of the isProfessor variable.
	 * false = TA, true = Professor.]
	 * 
	 * @return 
	 *     [boolean: isProfessor variable.]
	 */
	public boolean getIsProfessor() {
		
		return isProfessor;
		
	}

}
