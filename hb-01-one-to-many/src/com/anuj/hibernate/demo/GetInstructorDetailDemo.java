package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;


public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {

			// start transaction
			session.beginTransaction();
			// get the instructor detail object
			int tempId=2;
			InstructorDetail instructor = session.get(InstructorDetail.class, tempId);
			// print the instructor detail
			System.out.println("Fetched from db: "+instructor);
			//print the associated instructor
			System.out.println("Assocaited instructor id : "+instructor.getInstructor());
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Success!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			// handle connection leak issue
			session.close();
			factory.close();
		}
	}
}
