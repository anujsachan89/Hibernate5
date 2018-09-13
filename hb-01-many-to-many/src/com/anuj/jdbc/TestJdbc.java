package com.anuj.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcURL = "jdbc:mysql://localhost:3306/hb-05-many-to-many?useSSL=false&servertTimezone=UTC";
		String user ="root";
		String password = "";
		try {
			System.out.println("Connection to databse: "+jdbcURL);
			Connection myConn = DriverManager.getConnection(jdbcURL,user,password);
			System.out.println("Connection Succesfull");
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}

}
