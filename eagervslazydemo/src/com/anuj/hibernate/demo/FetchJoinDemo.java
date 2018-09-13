package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;


public class FetchJoinDemo {

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

			// Option2: hibernate quesy with HQL
			// get the instructor from db
			int tempID =1;
			Query<Instructor> query = session.createQuery("select i from Instructor "
					+"JOIN FETCH i.course "
					+"WHERE i.id=:theInstructorId",
					Instructor.class);
			//set parameter in query
			query.setParameter("theInstructorId",tempID);

			//execute query and get the instructor
			Instructor instructor = query.getSingleResult();

			System.out.println("luve2code Instructor name: "+instructor);
			System.out.println("luve2code Courses are : "+instructor.getCourses());
			// commit transaction
			session.getTransaction().commit();

			// close session
			session.close();
			System.out.println("Session is now going close");

			//get course of instructor
			System.out.println("luve2code Courses are : "+instructor.getCourses());

			System.out.println("luve2code Success!");
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			// add leak clean
			session.close();
			factory.close();
		}
	}
}
