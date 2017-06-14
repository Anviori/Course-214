#Office Hours Simulator

#Introduction:

Algorithms and Datastructures third assginment. Implementing a simulation that
models a line at a professor's office. 

- The professor gives priority to students with a higher class number over
students with a lower class number.

- The probability of students showing up at any given minute for a particular
class is inversely proportional to the amount of time left until the next exam
or homework due date for the class.

- Depending on the amount of coffee the professor has had, visits can last 
between a minimum and maximum number of minutes.

- The simulation takes, as input, a number of courses, their numbers, the 
probability of a student arriving at any given minute for that course,
a minimum and maximum visit time, and the number of minutes office hours is 
available. The program reads this through a text file.

- The textfile being read must be of this format:

	number of courses:3 <br />
	course numbers:219 214 114 <br />
	arrival probabilities:0.4 0.6 0.8 <br />
	min time:2 <br />
	max time:4 <br />
	num cups:1 <br />
	simulation time:50 <br />
	number of tas:2 

- Each student is assigned a number of minutes their question takes to answer.

- TAs are also available to help the professor. They will take the next student
with the highest course number but will spend twice as long as the professor
answering questions.

- The program outputs a minute by minute simulation of the office hours line.