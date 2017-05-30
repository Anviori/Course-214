/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 4
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_4;

public class Course {

	private int courseNumber;
	private int courseDifficulty;
	private double arrivalProbability;
	
	/**
	 * [Default Constructor that initializes a Course Object
	 * with the given course number and arrival probability.
	 * (courseDifficulty is set after all Courses are sorted with
	 * their course numbers).]
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [courseNumber exists in the given courseNumbers.]
	 * @param courseNumber
	 *     [int: Course number.]
	 * @param arrivalProbability
	 *     [double: The probability of arrival.]
	 * @throws IllegalArgumentException
	 *     [If courseNumber does not exist in the given courseNumbers.]
	 */
	
	public Course(int courseNumber, double arrivalProbability) throws IllegalArgumentException {
		
		//if(!exists(OfficeHourSimulator.getCourseNums(), courseNumber))
			//throw new IllegalArgumentException();
		
		this.courseNumber = courseNumber;
		this.arrivalProbability = arrivalProbability;
		
	}
	
	/**
	 * [Checks if courseNumber does not exists in given 
	 * courseNums array that was read from the file]
	 * 
	 * @return
	 *     [boolean: true if it does not exists.
	 *     false if it does.]
	 */
	
	public boolean exists(int[] a, int b) {
		
		for(int n: a) {
			
			if(b == n) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}

	/**
	 * [Get the difficulty of the course.]
	 * 
	 * @return
	 *     [int: Course difficulty.]
	 */
	
	public int getCourseDifficulty() {
		
		return courseDifficulty;
		
	}

	/**
	 * [Set the difficulty of the Course Objects.]
	 * 
	 * @param 											courseNums
	 *     [int[]: Array of course numbers.]
	 * @param temp
	 *     [int[]: Array of the course numbers sorted from
	 *     lowest value to highest.]
	 * @param courseNumber
	 *     [int: The course number given to find 
	 *     the difficulty of.]
	 */
	
	public void setCourseDifficulty(int[] courseNums, int[] temp, int courseNumber) {
		
		for(int i = 0; i < temp.length; i++) {
		
			if(temp[i] == courseNumber) {
				
				courseDifficulty = i + 1;
				
			}
			
		}
	
	}
	
	/**
	 * [Get the course number.]
	 * 
	 * @return
	 *     [int: Course number.]
	 */
	
	public int getCourseNumber() {
		
		return courseNumber;
	
	}
	
	/**
	 * [Get the arrival probability.]
	 * 
	 * @return
	 *     [double: arrival probability.]
	 */
	
	public double getArrivalProbability() {
		
		return arrivalProbability;
		
	}
	
}
