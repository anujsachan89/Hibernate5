package com.anuj.hibernate.demo;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Student;

public class UpdateStudentDemo {

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
