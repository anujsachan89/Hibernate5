package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;
import com.anuj.hibernate.demo.entity.Review;
import com.anuj.hibernate.demo.entity.Student;


public class GetCoursesForAnuj {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			//start a transaction
			session.beginTransaction();

			// get the student anuj from databases
			int tempId =2;
			Student tempStudent = session.get(Student.class, tempId);
			System.out.println("Loaded Student" +tempStudent);
			System.out.println("Course Are: "+tempStudent.getCourses());

			// create more courses
			Course tempCourse1 = new Course("Rubix cube - how to solve cube");
			Course tempCourse2 = new Course("Game Development");
			Course tempCourse3 = new Course("Android Development");
			
			// add student to that courses
			tempCourse1.addMethod(tempStudent);
			tempCourse2.addMethod(tempStudent);
			tempCourse3.addMethod(tempStudent);
			
			//Save the courses
			System.out.println("Saving Courses");
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);


			// commit transaction
			session.getTransaction().commit();
			System.out.println("Success!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			// add leak clean
			session.close();
			factory.close();
		}
	}
}
