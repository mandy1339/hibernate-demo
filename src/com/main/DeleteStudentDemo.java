package com.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entity.Student;

public class DeleteStudentDemo {

	public static void main(String[] args) {
		// create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();
		
		//create a session
		Session session = factory.getCurrentSession();
		
		try {				
			
			// INSERTING A STUDENT
//			session = factory.getCurrentSession();
//			session.beginTransaction();
//			
//			Student tempStudent = new Student("name2delete", "lastname2delete", "email2delte@lie.com");
//			
//			session.save(tempStudent);
//			
//			System.out.println("after saving now student has an id: ");
//			System.out.println(tempStudent);
//			
//			session.getTransaction().commit();

			
			
			// DELETING A STUDENT WITH SESSION
			int studentId = 8;	
			
			// get a new session and start transaction
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// get a student we want to delete
			Student student1 = session.get(Student.class, studentId);
			System.out.println("We got student: " + student1);
			
			// use session to delete a student
			System.out.println("Deleting student");
			session.delete(student1);
			
			// commit the transaction
			session.getTransaction().commit();
			
			
			
			
			
			// 2nd TRANSACTION (DELETING A STUDENT WITH QUERY)
			session = factory.getCurrentSession();
			session.beginTransaction();
			
			// update email for all students
			System.out.println("delete a student using query statement");
			
			session.createQuery("delete from Student where id=9").executeUpdate();
			
			// commit 2nd transaction
			session.getTransaction().commit();
			
			
			System.out.println("Done!");
		}
		finally {
			factory.close();
		}
	
	}

}
