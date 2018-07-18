package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Student;

public class ReadStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {			
			// create 3 student objects
			System.out.println("Creating 3 student objects...");
			Student tempStudent1 = new Student("Daffy", "Duck", "daffy@luv2code.com");

			
			// start a transaction
			session.beginTransaction();
			
			// save the student object
			System.out.println("saving the student...");
			System.out.println(tempStudent1);
			session.save(tempStudent1);
			
			// commit transaction
			session.getTransaction().commit();
			
			
			// NEW CODE
			
			
			// find out the student's id: primary key
			System.out.println("Saved student. Generated id: " + tempStudent1.getId());
			
		
			// now get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// retrieve student based on the id: primary key
			System.out.println("\nGetting student with id: " + tempStudent1.getId());
			Student myStudent = session.get(Student.class, tempStudent1.getId());
			System.out.println("Get complete: " + myStudent);
			
			// commit the transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	}

}
