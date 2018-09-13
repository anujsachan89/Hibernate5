package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Student;

public class PrimaryKeyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Student.class)
									.buildSessionFactory();
		
		// create session
		Session session = factory.getCurrentSession();
		try {
			// create 3 student object
			System.out.println("creating a new student object");
			Student tempStudent = new Student("John","Deo","a@gamil.com");
			Student tempStudent1 = new Student("Master","Blaster","achan@gamil.com");
			Student tempStudent2 = new Student("Yahoo","Google","anuj@gamil.com");
			Student tempStudent3 = new Student("Brain","lara","an@gamil.com");
			//start a transaction
			System.out.println("Begining Transaction");
			session.beginTransaction();
			//save the student object
			
			System.out.println("Saving Student");
			session.save(tempStudent);
			session.save(tempStudent1);
			session.save(tempStudent2);
			session.save(tempStudent3);
			
			
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
