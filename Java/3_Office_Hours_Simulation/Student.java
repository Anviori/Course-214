/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 4
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_4;

public class Student {

	private static int studentCounter = 0;
	private int studentId = 0;
	private int timeArrived;
	private int timeRequired;
	private Course course;
	
	/**
	 * [Constructor to initialize student object.] 
	 * 
	 * <dt><b>Preconditions:</b><dd>
	 *     [initTimeArrived > 0.
	 *     course's field courseNumer exists in the given courseNumber.]
	 * @param initTimeArrived
	 *     [int: Given time the student arrived.]
	 * @param course
	 *     [Course: Given course the student is in.]
	 * @param timeRequired
	 *     [int: Given time the student needs with the Helper. 
	 *     (Randomly generated value between minTime and maxTime).]
	 * @throws IllegalArgumentException
	 *     [If the initialTimeArrived is less than or equal to zero or
	 *     if the course's field courseNumber does not exists in the given
	 *     courseNumbers.]
	 */
	
	public Student(int initTimeArrived, Course course, int timeRequired) throws IllegalArgumentException {
		
		//if(initTimeArrived <= 0 || !course.exists(OfficeHourSimulator.getCourseNums(), course.getCourseNumber()))
		//	throw new IllegalArgumentException();
	
		studentCounter ++;
		studentId = studentCounter;
		
		this.timeRequired = timeRequired;
		timeArrived = initTimeArrived;
		this.course = course;

	}
	
	/**
	 * [Get the time the student arrived.]
	 * 
	 * @return
	 *     [int: The time the student arrived.]
	 */

	public int getTimeArrived() {
		
		return timeArrived;
	
	}
	
	/**
	 * [Get the time the student needs in the office.]
	 * 
	 * @return
	 *     [int: The time required by the student.]
	 */

	public int getTimeRequired() {
	
		return timeRequired;
	
	}
	
	/**
	 * [Get the students Course object.]
	 * 
	 * @return
	 *     [Course: course object.]
	 */

	public Course getCourse() {
	
		return course;
	
	}
	
	/**
	 * [Get the ID of the Student.]
	 * 
	 * @return
	 *     [int: student Id.]
	 */
	
	public int getStudentId() {
		
		return studentId;
		
	}
	
	/**
	 * [Get the number of students that have been constructed.]
	 * 
	 * @return
	 *     [int: student counter value (amount of student objects).]
	 */
	
	public int getStudentCounter() {
		
		return studentCounter;
		
	}
	
}
