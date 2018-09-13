package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		try {
			int tempStudent =1;

			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//Retrieve student based on id: primary key
			System.out.println("\n Gettign student form id: "+tempStudent);
			Student myStudent = session.get(Student.class, tempStudent);
			
			//delete Student
			//System.out.println("Deleting Student");
			//session.delete(myStudent);
			
			//delete Student id=2
			System.out.println("Deleting Student with id:3");
			session.createQuery("delete from Student where id=3").executeUpdate();
			
			//commit the transaction
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
