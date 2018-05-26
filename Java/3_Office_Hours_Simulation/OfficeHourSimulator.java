/**
 * @author Wilmer Suarez
 * 109592501
 * Homework Number 4
 * R14
 * Tayo Amuneke and Yiwen Wang
 * Grading TA: Anand Aiyer 
 */

package assignment_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class OfficeHourSimulator {
	
	public final static int MAX = 100;
	public static int[] courseNums;
	public static int maxTime;
	public static int minTime;
	public static int numCourses;
	static Student studentTemp;
	public int timeStep;
	
	/**
	 * [Main method that, first, asks the user for input file
	 * and reads the file's values and assigns these values to
	 * 7 variables. The method then sends these values to the
	 * simulate method to being the simulation.]
	 * 
	 * @param args
	 * @throws IOException
	 */

	public static void main(String args[]) throws IOException {
		
		InputStreamReader inStream = new InputStreamReader(System.in);
		BufferedReader input = new BufferedReader(inStream);
		Scanner file = null;
		
		System.out.println("Welcome to the Office Hours Simulation!\n");
		
		while(true) {
			
			System.out.print("Enter file name: ");
			String fileName = input.readLine();
			fileName.replace("\\", "/");
		
			try {
			
				file = new Scanner(new File(fileName));	
				
				System.out.println("... " + fileName + " loaded.");
		
				break;
					
			} catch (FileNotFoundException e) {
			
				System.out.println("\nFile not found. Try again.\n");
							
			}
		
		}

		double[] items = new double[MAX];

		for(int i = 0; i < items.length; i++) {
			
			items[i] = read(file);
			 
		}

		file.close();
		
		// Number of courses
		numCourses = (int) items[0];
		
		// Array of course numbers
		courseNums = new int[numCourses];
		
		for(int i = 0; i < numCourses; i++) {
			
			courseNums[i] = (int) items[i + 1];
			
		}
		
		// Sort course numbers
		int[] temp = courseNums;
		Arrays.sort(temp);
		
		// Array of probabilities 
		double[] arrivalProb = new double[numCourses];
		
		for(int i = 0; i < numCourses; i++) {
			
			arrivalProb[i] = items[i + numCourses + 1];
				
		}
		
		Arrays.sort(arrivalProb);
			
		// Minimum time 
		minTime = (int) items[2 * numCourses + 1];
		
		// Maximum time
		maxTime = (int) items[2 * numCourses + 2];

		// Number of cups
		int numCups = (int) items[2 * numCourses + 3];
		
		// Simulation time (officeHrTime)
		int simTime = (int) items[2 * numCourses + 4];
		
		// Number of TA's 
		int numTAs = (int) items[2 * numCourses + 5];
		
		// Array of Course Objects using course numbers
		Course[] courses = new Course[numCourses];
		
		for(int i = 0; i < numCourses; i++) {
			
			courses[i] = new Course(courseNums[i], arrivalProb[i]); 
			courses[i].setCourseDifficulty(courseNums, temp, courses[i].getCourseNumber());
			
		}
		
		simulate(simTime, arrivalProb, courses, minTime, maxTime, numCups, numTAs);
		
	}
	
	/**
	 * [This method reads the file and returns the 
	 * value read.]
	 * 
	 * @param file
	 *     [Scanner: File to be read.]
	 * @return
	 *     [double: Value read in file.]
	 */
	
	public static double read(Scanner file) {
		
		double num = 0.0;

		while(file.hasNext()) {
		
			String a = file.next(); 
		
			String str[] = a.split(":");
			
			for(String s : str) {
				
				try {
						 
					return num = Double.parseDouble(s);
				
				} catch (NumberFormatException e) { 
					
					//do nothing 
				
				} 
				
			}
			
		}
			
		return num;
		
	}
	
	/**
	 * [Get the courseNums array. Used for Course class
	 * exception condition.] 
	 * 
	 * @return
	 *    [int[]: The courseNums array.]
	 */
	
	public static int[] getCourseNums() {
		
		return courseNums;
		
	}
	
	/**
	 * [Produces a randomly generated value between the 
	 * maximum time and minimum time that was read from the file.]
	 * 
	 * @return
	 *     [int: Value generated between min and max time.]
	 */
	
	public static int timeRequired() {
		
		int time = minTime + (int)(Math.random() * ((maxTime - minTime) + 1));
		
		return time;
		
	}
	
	/**
	 * ["Manager" of the simulation.]
	 * 
	 * @param officeHrTime
	 *     [int: The total time the students will be allowed to line-up.]
	 * @param arrivalProbability
	 *     [double[]: array of the probabilities of arrival for each course.]
	 * @param courses
	 *     [Course[]: array of Course objects.]
	 * @param minTime
	 *     [int: minimum time student is allowed to need.]
	 * @param MaxTime
	 *     [int: maximum time student is allowed to need.]
	 * @param numCups
	 *     [int: number of coffee cups the professor drank.]
	 * @param numTAs
	 *     [int: the number of TAs available to help the students.]
	 */
	
	public static void simulate(int officeHrTime, double[] arrivalProbability, 
			Course[] courses, int minTime, int maxTime, int numCups, int numTAs) {
		
		StudentQueue s = new StudentQueue();
		Helper prof = new Helper(true);
		
		Helper ta[] = new Helper[numTAs];
		
		for(int i = 0; i < numTAs; i++) {
			
			Helper TA = new Helper(false);
			ta[i] = TA;
			
		}
		
		String course = "Coures";
		String prob = "Probability";
		   
		System.out.printf("%n%-21s%-20s", course, prob);
		System.out.println("\n---------------------------------");
		for(int i = 0; i < numCourses; i++)
			System.out.printf("%-21d%-20.1f%n", courses[i].getCourseNumber(), courses[i].getArrivalProbability());
		System.out.println("Number of TAs:" + numTAs);
		System.out.println("Coffee cups: " + numCups);
		System.out.println("Base time interval: " + minTime + "-" + maxTime);
		System.out.println("Time: " + officeHrTime);
		
		System.out.println("\n\n         Simulation has begun!        ");
		
		int timeStep = 1;
		
		while(officeHrTime > 0) {
			
			System.out.println("\nTime Step " + timeStep + ": ");
			System.out.println("____________________________________________________");
			
			for(int i = 0; i < courses.length; i++) {
				
				BooleanSource arrival = new BooleanSource(courses[i].getArrivalProbability());
				
				if(arrival.occurs()) {
				
				Student s1 = new Student(timeStep, courses[i], timeRequired());
				s.enqueue(s1);
				
				System.out.println("Student " + s1.getStudentId() + " has arrived for "
						+ s1.getCourse().getCourseNumber() + " requiring " + s1.getTimeRequired()
						+ " minutes."); 
				
				} else {
					
					System.out.println("No students have arrived for " + courses[i].getCourseNumber() + ".");
					
				}				
				
			}
			
			System.out.println();
			
//---------------------------------------------------------------------------------------------------------
			
			if((prof.getTimeLeftTilFree() == 0 || prof.getTimeLeftTilFree() == 1) && !s.isEmpty()) {
					
				studentTemp = s.dequeue();
					
				prof.setTimeLeftTilFree(studentTemp.getTimeRequired() - numCups);
				System.out.println("Professor is helping Student " + studentTemp.getStudentId()
						+ ". " + prof.getTimeLeftTilFree() + " minutes remaining.");
					
			} else if(prof.getTimeLeftTilFree() > 0){
				
				prof.setTimeLeftTilFree(prof.getTimeLeftTilFree() - 1);
				System.out.println("Professor is helping Student " + studentTemp.getStudentId()
				+ ". " + prof.getTimeLeftTilFree() + " minutes remaining.");
				
			} else {
				
				System.out.println("Professor is waiting for student to arrive.");
				
			}
				
			for(int i = 0; i < ta.length; i++) {
				
				if((ta[i].getTimeLeftTilFree() == 0 || ta[i].getTimeLeftTilFree() == 1) && !s.isEmpty()) {	
					
					studentTemp = s.dequeue();
					
					ta[i].setTimeLeftTilFree(studentTemp.getTimeRequired() * 2);
					System.out.println("TA "+ (i + 1) + " is helping Student " + studentTemp.getStudentId()
							+ ". " + ta[i].getTimeLeftTilFree() + " minutes remaining.");
						
				} else if(ta[i].getTimeLeftTilFree() > 0){
					
					ta[i].setTimeLeftTilFree(ta[i].getTimeLeftTilFree() - 1);
					System.out.println("TA "+ (i + 1) + " is helping Student " + studentTemp.getStudentId()
					+ ". " + ta[i].getTimeLeftTilFree() + " minutes remaining.");
					
				} else {
					
					System.out.println("TA " + (i + 1) + " is waiting for student to arrive.");
					
				}
			
			}
			
			System.out.println(s);
			
			officeHrTime--;
			timeStep++;
		}
		
		System.out.println("____________________________________________________");
		System.out.println("\n                Simulation ended!        ");
		
		System.out.println("Statistics: ");
		System.out.printf("%n%-21s%-20s$-20s", "Course", "#StudentsHelped", "Avg. Time");
		System.out.println("____________________________________________________");
		for(int i = 0; i < numCourses; i++)
			System.out.printf("%-21d%-20.1f%n", courses[i].getCourseNumber(), courses[i].getArrivalProbability());
		
	}
	
}
