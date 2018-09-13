package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;


public class DeleteDemo {

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
			
			// get instructor from primary key
			int theId = 1;
			Instructor theInstructor = session.get(Instructor.class,theId);
			System.out.println("Found Instructor :"+theInstructor);
			
			// Delete Instructor
			if(theInstructor!=null) {
				// Note it will also delete associated "details" object
				// because of cascadetype.all
				
				System.out.println("Deleting the object: "+theInstructor);
				session.delete(theInstructor);
			}
			
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Success!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			factory.close();
		}
	}
}
