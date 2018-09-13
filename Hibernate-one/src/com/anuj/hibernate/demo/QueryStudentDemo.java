package com.anuj.hibernate.demo;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Student.class)
				.buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();
		try {
			//start a transaction
			System.out.println("Begining Transaction");
			session.beginTransaction();
			// query students
			List<Student> myStudent = session.createQuery("from Student").getResultList();
			// display students
			displayStudents(myStudent);

			/*//Query Students: lastname='Deo' Use Java Property name as in Student class
			myStudent=session.createQuery("from Student s where s.lastName='Deo'").getResultList(); 
			System.out.println("Student who have last name as Deo");
			displayStudents(myStudent);	*/	

			/*//Query Students: lastname='Deo' OR firstname = Daffy Use Java Property name as in Student class
			myStudent=session.createQuery("from Student s where"
					+ " s.lastName='Deo'OR s.firstName='Daffy'").getResultList(); 
			System.out.println("Student who have last name as Deo OR firstname = Daffy");
			displayStudents(myStudent);*/	


			//quey students where email LIKE '%luv2code.com'
			myStudent = session.createQuery("from Student s where "
					+"s.email LIKE '%love2code.com'").getResultList();
			displayStudents(myStudent);



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

	private static void displayStudents(List<Student> myStudent) {
		for(Student tempStudent:myStudent) {
			System.out.println(tempStudent);
		}
	}

}
