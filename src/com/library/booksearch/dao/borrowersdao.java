package com.library.booksearch.dao;
import com.library.booksearch.core.Borrower;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.library.booksearch.core.Book;
import com.library.booksearch.core.Borrower;

public class borrowersdao {

	private Connection myConn;
	
	public borrowersdao() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	
		
	public void addBorrower(String firstname,
			String lastname, String add, String phoneno) throws Exception {
		PreparedStatement myStmt = null;
		PreparedStatement myStmt1 = null;
		Statement stmt=null;
		stmt = myConn.createStatement();
		
		try {
			// prepare statement
			ResultSet myRs = null;
			myRs= stmt.executeQuery("select fname,lname,address from borrower where fname='"+ firstname+"' and lname='"+ lastname +"' and address='"+ add +"'" );
			
			
			if(myRs.next()){
			    JOptionPane.showMessageDialog(null,"User  already Exists");
			  
			    }
			else{
			
			myStmt = myConn.prepareStatement("insert into borrower(fname,lname,address,phone) values (?, ?, ?, ?)");
						// set params
			
			myStmt.setString(1,firstname);
			myStmt.setString(2,lastname);
			myStmt.setString(3,add);
			myStmt.setString(4,phoneno);
			//execute SQL
			myStmt.executeUpdate();			
			}
		}
		finally {
			close(myStmt);
		}
		
	}
	


	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
				throws SQLException {

			if (myRs != null) {
				myRs.close();
			}

			if (myStmt != null) {
				
			}
			
			if (myConn != null) {
				myConn.close();
			}
		}

		private void close(Statement myStmt, ResultSet myRs) throws SQLException {
			close(null, myStmt, myRs);		
		}
		private void close(Statement myStmt) throws SQLException {
			close(null, myStmt, null);		
		}

	
		
	}
	
	
