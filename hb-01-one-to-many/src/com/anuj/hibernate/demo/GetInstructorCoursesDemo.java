package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;


public class GetInstructorCoursesDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			//start a transaction
			session.beginTransaction();
			// get the instructor from db
			int id =1;
			Instructor instructor = session.get(Instructor.class,id);
			System.out.println("Instructor name: "+instructor);
			//get course of instructor
			System.out.println("Courses are : "+instructor.getCourses());
			
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
