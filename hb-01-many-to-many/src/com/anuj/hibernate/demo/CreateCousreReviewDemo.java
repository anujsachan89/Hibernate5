package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Course;
import com.anuj.hibernate.demo.entity.Instructor;
import com.anuj.hibernate.demo.entity.InstructorDetail;
import com.anuj.hibernate.demo.entity.Review;
import com.anuj.hibernate.demo.entity.Student;


public class CreateCousreReviewDemo {

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
			//create a course
			Course tempCourse = new Course("Pacman-How to score millions");
			// save the course
			System.out.println("Saving Course..."+tempCourse);
			session.save(tempCourse);
			// create a student
			Student tempStudent = new Student("John", "Doe", "abc@yahoo.com");
			Student tempStudent1 = new Student("Anuj", "Sachan", "alibaba@yahoo.com");
			Student tempStudent2 = new Student("Bottle", "Waala", "bootle@yahoo.com");
			// add studnets to course
			tempCourse.addMethod(tempStudent);
			tempCourse.addMethod(tempStudent1);
			tempCourse.addMethod(tempStudent2);
			// save students
			System.out.println("Saving Students");
			session.save(tempStudent);
			session.save(tempStudent1);
			session.save(tempStudent2);
			System.out.println("Save Students Completed: "+tempCourse.getStudents());
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
