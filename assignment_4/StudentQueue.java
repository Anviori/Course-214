/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 4
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_4;

import java.util.ArrayList;
import java.util.EmptyStackException;

@SuppressWarnings("serial")
public class StudentQueue extends ArrayList<Student>{

	private ArrayList<Student> q;
	
	/**
	 * [Default constructor that creates an empty Queue of 
	 * Student Objects. ]
	 * 
	 */
	
	public StudentQueue() {
		
		q = new ArrayList<Student>();

	}
	
	/**
	 * [Adds the Student object into the Queue.]
	 * <dt><b>Preconditions:</b><dd>
	 *     [The StudentQueue should be instantiated.]
	 * <dt><b>Postconditions:</b><dd>
	 *     [Student object, not being null, is added
	 *     to the proper position of the StudentQueue.]
	 * @param s
	 *     [Student: Student object to be added to the queue.]
	 */
	
	public void enqueue(Student s) {
		
		q.add(s);
		
	}
	
	/**
	 * [Removes the first student in the queue.]
	 * 
	 * @return
	 *     [Student: The student that was dequeued.]
	 */
	
	public Student dequeue() {

		if(q.isEmpty()) {
			
			throw new EmptyStackException();
			
		} else {
			
			return q.remove(0);
			
		}
		
	}
	
	/**
	 * [Checks the size of the queue
	 * (how many students are currently in line).]
	 * 
	 * @return
	 *    [int: size of the queue.]
	 */
	
	public int size() {
		
		return q.size();
		
	}
	
	/**
	 * [Checks to see if queue is empty.]
	 * 
	 * @return
	 *     [boolean: false if size > 0,
	 *     true if size is equal.]]
	 */
	
	public boolean isEmpty() {
		
		return q.isEmpty();
		
	}
	
	/**
	 * [Print out the queue.]
	 * 
	 */
	
	public String toString() {
		
		String s = " ";
		
		System.out.println("\nStudent Queue: ");
		System.out.printf("%-5s%-5s%-10s%-10s", "Id", "Course", "    Required Time", "    Arrival Time");
		System.out.println("\n----------------------------------------");
		
		if(isEmpty()) {
			
			s = "[No students in line]";
			
		} else {
			
			for(int i = 0; i < size(); i++) {
			
				Student temp = dequeue();
			
				s = String.format("%-5d%-5d     %d                %d%n",temp.getStudentId(), temp.getCourse().getCourseNumber(), temp.getTimeRequired(), temp.getTimeArrived());
			
				enqueue(temp);
			
			}
			
		}
		
		return s;
		
	}

}
