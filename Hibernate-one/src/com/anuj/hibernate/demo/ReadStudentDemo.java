package com.anuj.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anuj.hibernate.demo.entity.Student;

public class ReadStudentDemo {

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
			Student tempStudent = new Student("Daffy","Duck","daffy@love2code.com");
			//start a transaction
			System.out.println("Begining Transaction");
			session.beginTransaction();
			//save the student object

			System.out.println("Saving Student");
			System.out.println(tempStudent);
			session.save(tempStudent);

			//commit transaction
			System.out.println("Commit Transacation");
			session.getTransaction().commit();

			// My new code
			//find out student id: primary key id
			System.out.println("id for daffy is"+tempStudent.getId());

			//now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			//reterive student based on id: primary key
			System.out.println("\n Gettign student form id: "+tempStudent.getId());
			Student myStudent = session.get(Student.class, tempStudent.getId());
			System.out.println("Get student complete:"+myStudent);
			
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
