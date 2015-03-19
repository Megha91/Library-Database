package com.library.booksearch.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import com.library.booksearch.core.Book;
import com.library.booksearch.core.Loans;

public class loansdao {

	private Connection myConn;
	
	public loansdao() throws Exception{
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


	
	public void addLoan(String Book_id ,String Branch_id,int Card_no) throws Exception{
	
		PreparedStatement myStmt = null;
		PreparedStatement stmt=null;
		PreparedStatement stmnt=null;
		PreparedStatement stmnt2=null;
		PreparedStatement stmnt3=null;
		PreparedStatement stmnt4=null;
		//Statement stmnt = null;
		ResultSet myRs = null;
		ResultSet myRs2 = null;
		ResultSet myRs3 = null;
		ResultSet myRs4 = null;
		//stmnt=myConn.createStatement();
		try{
			int count=0;
			int copy_count=0;
			int total_copies=0;
			int available_copies=0;
			stmnt=myConn.prepareStatement("select count(*) from book_loans where card_no='"+Card_no+"' and Datein is NULL group by card_no ");
			myRs=stmnt.executeQuery();
			
			
			stmnt3=myConn.prepareStatement("select no_of_copies from book_copies where Book_id='"+Book_id+"' and Branch_id='"+Branch_id+"'  ");
			myRs3=stmnt3.executeQuery();
			

			stmnt4=myConn.prepareStatement("select no_of_copies_available from book_copies where Book_id='"+Book_id+"'and Branch_id='"+Branch_id+"' ");
			myRs4=stmnt4.executeQuery();
			
			
			while(myRs.next())
			count = myRs.getInt(1);
		
			while(myRs3.next())
			total_copies = myRs3.getInt(1);
			System.out.println(total_copies);
			while(myRs4.next())
				available_copies = myRs4.getInt(1);
			if(count==3){
				JOptionPane.showMessageDialog(null,"The borrower already has three books against the card number");
			}else if(available_copies==0)
			{
				JOptionPane.showMessageDialog(null,"There are no more books in the inventory");
			}else
			{
			java.util.Date utilDate= new java.util.Date();
             java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
		myStmt= myConn.prepareStatement("insert into book_loans(Book_id,branch_id,card_no, Dateout, Duedate)values(?,?,?,?,?)");
		myStmt.setString(1, Book_id);
		myStmt.setString(2, Branch_id);
		myStmt.setInt(3, Card_no);
		myStmt.setDate(4, currentDate);
		myStmt.setDate(5, currentDate);
		myStmt.executeUpdate();
		stmt= myConn.prepareStatement("Update book_loans  set duedate = DATE_ADD(Dateout,INTERVAL 14 DAY) where Dateout='"
                + currentDate + "'");
		stmnt2=myConn.prepareStatement("update book_copies set no_of_copies_available=(no_of_copies_available-1) where branch_id='"+Branch_id+"' and book_id='"+Book_id+"'");
		
		stmt.executeUpdate();
		stmnt2.executeUpdate();
			}
		}
	finally{
		close(myStmt);
		close(stmt);
		close(stmnt);
		
	}
	}

	

	private void close(Statement stmt1) {
		
		
	}

	public List<Loans> returnBook(String book_id, String branch_id, int card_no) throws Exception{
		PreparedStatement stmt=null;
		PreparedStatement stmt2=null;
		Statement stmt1=null;
		//Statement stmt2=null;
		ResultSet myRs=null;
		ResultSet myRs1=null;
		ResultSet myRs2=null;
		List<Loans> loans=new ArrayList<>();
		
		try{ 
			java.util.Date utilDate= new java.util.Date();
            java.sql.Date currentDate = new java.sql.Date(utilDate.getTime());
            System.out.println(currentDate);
			stmt=myConn.prepareStatement("Update book_loans set Datein ='"+ currentDate +"' where card_no='"+card_no+"' and branch_id='"+branch_id+"' and book_id='"+book_id+"' ");
			stmt.executeUpdate();
			stmt1=myConn.createStatement();
			myRs=stmt1.executeQuery("select * from book_loans where card_no='"+card_no+"' and branch_id='"+branch_id+"' and book_id='"+book_id+"' ");
			stmt2=myConn.prepareStatement("update book_copies set no_of_copies_available=(no_of_copies_available+1) where branch_id='"+branch_id+"' and book_id='"+book_id+"'");
			stmt2.executeUpdate();		
			while (myRs.next()) {
				Loans tempLoan = convertRowToLoans(myRs);
				loans.add(tempLoan);
			}

			return loans;
		
		}
		finally{
			close(stmt);
			close(stmt1);
		}
		
	}

	private Loans convertRowToLoans(ResultSet myRs) throws SQLException{
		int Loan_id = myRs.getInt("Loan_id");
		String book_id=myRs.getString("Book_id");
		String branch_id=myRs.getString("Branch_id");
		int card_no = myRs.getInt("Card_no");
		Date dateout=myRs.getDate("Dateout");
		Date duedate=myRs.getDate("Duedate");
		Date datein=myRs.getDate("Datein");
		Loans tempLoan=new Loans(Loan_id,book_id,branch_id,card_no,dateout,duedate,datein);
		return tempLoan;
	}
	
	

}
