package com.anuj.hibernate.demo;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

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
			// create a object
			/*Instructor tempinstructor = new Instructor("Anuj", "Sachan", "anujsachan@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail(
					"www.youtube.com", "cricket");*/
			
			Instructor tempinstructor = new Instructor("Madhu", "Patel", "xyz@gmail.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail(
					"www.yahoo.com", "Books");
			
			//associate a object
			tempinstructor.setInstructorDetail(tempInstructorDetail);

			// start transaction
			session.beginTransaction();
			
			// save instructor
			// Note: this will also save the details object
			//because of cascade.type.All
			System.out.println("Saving Instructor : "+tempinstructor);
			session.save(tempinstructor);

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
