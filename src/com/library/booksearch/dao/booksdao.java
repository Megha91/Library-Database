package com.library.booksearch.dao;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

import com.library.booksearch.core.Book;
public class booksdao { 

	private Connection myConn;
	
	public booksdao() throws Exception {
		
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
	
	public List<Book> getAllBooks() throws Exception {
		List<Book> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from book");
			
			while (myRs.next()) {
				Book tempBook = convertRowToBook(myRs);
				list.add(tempBook);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Book> searchBook(String Book_id, String Author, String Title) throws Exception {
		List<Book> list = new ArrayList<>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		try {
			Book_id="%"+Book_id+"%";
			Author="%"+Author+"%";
			Title="%"+Title+"%";
			
			myStmt=myConn.prepareStatement("SELECT b.Book_id, b.Title,Author_name,lb.Branch_id,  "
					+ "No_of_copies, no_of_Copies_Available from BOOK b "
					+ "inner join BOOK_COPIES bc on b.Book_id=bc.Book_id "
					+ "inner join BOOK_AUTHORS ba on b.Book_id=ba.Book_id "
					+ "inner join LIBRARY_BRANCH lb on lb.Branch_id=bc.Branch_id "
					+ "left outer join BOOK_LOANS bl on bl.Book_id=b.Book_id and bl.Branch_id=lb.Branch_id "
					+ "where b.Book_id like \"%"
					+ Book_id
					+ "%\" AND ba.Author_name like \"%"
					+ Author
					+ "%\" AND b.Title like \"%"
					+ Title
					+ "%\""
					+ "GROUP BY b.Title,lb.Branch_name;");
		
			myRs = myStmt.executeQuery();
			int i=0;
				while (myRs.next()) {
					i++;
					System.out.println(myRs.getInt("no_of_copies"));
				Book tempBook = convertRowToBook(myRs);
				list.add(tempBook);
				System.out.println(i);
			}
				System.out.println(i);
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Book convertRowToBook(ResultSet myRs) throws SQLException {
		
		String Book_id = myRs.getString("Book_id");
		String Title = myRs.getString("Title");
		String author_name=myRs.getString("author_name");
		String branch_id=myRs.getString("branch_id");
		int no_of_copies=myRs.getInt("no_of_copies");
		int no_of_copies_available=myRs.getInt("no_of_copies_available");

		Book tempBook = new Book(Book_id, Title,author_name,branch_id,no_of_copies,no_of_copies_available);
			
		return tempBook;
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


}
