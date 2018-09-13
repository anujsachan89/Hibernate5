package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Student;

public class StudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		try {
			// create a student object
			System.out.println("creatin a new student object");
			Student tempStudent = new Student("Anuj","Sachan","anujsachan@gamil.com");
			//start a transaction
			System.out.println("Begining Transaction");
			session.beginTransaction();
			//save the student object
			
			System.out.println("Saving Student");
			session.save(tempStudent);
			
			//commit transaction
			System.out.println("Commit Transacation");
			session.getTransaction().commit();
			System.out.println("done");
		}
		catch (Exception exe) {
			exe.printStackTrace();
		}
		finally {
			factory.close();
		}
	}

}
