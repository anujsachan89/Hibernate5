package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;
import com.anuj.hibernate.demo.entity.Review;


public class CreateCousreReviewDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class)
				.buildSessionFactory();

		// create a session
		Session session = factory.getCurrentSession();

		try {
			//start a transaction
			session.beginTransaction();
			//create a course
			Course tempCourse = new Course("Pacman-How to score millions");
			
			//add some reviews
			tempCourse.addReview(new Review("Great course Love it"));
			tempCourse.addReview(new Review("fine course Love it"));
			tempCourse.addReview(new Review("Awesome course Love it"));
			
			//save the course .. and leverage cascadea ll
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println("Reviews are: "+tempCourse.getReviews());
			session.save(tempCourse);



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
