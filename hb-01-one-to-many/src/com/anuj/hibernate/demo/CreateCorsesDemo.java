package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;


public class CreateCorsesDemo {

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
			// create some courses
			Course courses1 = new Course("Air guitar-the ultomat guide");
			Course courses2 = new Course("Swimming -the swiming guide");
			Course courses3 = new Course("Singging-the guide to sing");
			// add courses to instructor
			instructor.add(courses1);
			instructor.add(courses2);
			instructor.add(courses3);
			
			// save the course
			session.save(courses1);
			session.save(courses2);
			session.save(courses3);
			
			
			
			
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
