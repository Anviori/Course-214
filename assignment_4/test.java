package assignment_4;

public class test {
	
	public static void main(String[] args) {
		
		StudentQueue q = new StudentQueue();
		
		Course c = new Course(1, 2.5);
		Course c1 = new Course(2, 2.5);
		Course c2 = new Course(3, 2.5);
		
		Student s = new Student(1, c, 2);
		Student s1 = new Student(1, c1, 3);
		Student s2 = new Student(1, c2, 2);
		
		q.enqueue(s);
		q.enqueue(s1);
		q.enqueue(s2);
		
		reverse(q);
		
	}
	
	public static void reverse(StudentQueue q) {
		
		Student temp;
		if(q.isEmpty()) {
			
			return;
			
		} else {
			
			temp = q.dequeue();
			
			reverse(q);
			q.enqueue(temp);
			System.out.println(temp.getCourse().getCourseNumber());
		}
		
	}

}
