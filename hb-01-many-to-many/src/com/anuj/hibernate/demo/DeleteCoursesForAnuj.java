package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;
import com.anuj.hibernate.demo.entity.Review;
import com.anuj.hibernate.demo.entity.Student;


public class DeleteCoursesForAnuj {

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

			// get the packman course from databases
			int studentTempId =2;
			Student tempStudent = session.get(Student.class, studentTempId);
			System.out.println("Student Loaded:"+tempStudent);
			
			System.out.println("Courses fro Student are: "+tempStudent.getCourses());
			//delete Student
			System.out.println("Deleting Student first name as: "+tempStudent.getFirstName());
			session.delete(tempStudent);
			
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
