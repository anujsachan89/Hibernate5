package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;


public class EagerLazyDemo {

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
			System.out.println(" Instructor name: "+instructor);
			System.out.println(" Courses are : "+instructor.getCourses());
			// commit transaction
			session.getTransaction().commit();

			// close session
			session.close();
			// option1 call getter method while session is open
			System.out.println("Session is now going close");

			//get course of instructor
			System.out.println("Courses are : "+instructor.getCourses());

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
