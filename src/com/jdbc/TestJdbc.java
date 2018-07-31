package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.entity.Student;

public class TestJdbc {

	public static void main(String[] args) {
		
		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "hbstudent";
		String pass = "hbstudent";
		try {
			System.out.println("connecting to database: " + jdbcUrl);
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("connection successful");
			
			
			// extra
			// query manually and get results
			ResultSet rset = myConn.prepareStatement("select * from student").executeQuery();
			List<Student> students = new ArrayList<Student>();
			while (rset.next()) {
				Student student = new Student();
				student.setId(Integer.parseInt(rset.getString("id")));
				student.setFirstName(rset.getString("first_name"));
				student.setLastName(rset.getString("last_name"));
				student.setEmail(rset.getString("email"));
				students.add(student);
			}

			for(Student s : students) {
				System.out.println(s.toString());
			}
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
	}

}
