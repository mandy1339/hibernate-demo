package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Student;

public class UpdateStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {				
			int studentId = 1;
		
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + studentId);
			Student myStudent = session.get(Student.class, studentId);
			
			System.out.println("Updating student...");	
			myStudent.setFirstName("Scooby");
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			
			// 2nd TRANSACTION
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("update email for all students");
			
			session.createQuery("update Student set email='foo@gmail.com'").executeUpdate();
			
			// commit 2nd transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	
	}

}
