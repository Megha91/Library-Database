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
import com.library.booksearch.core.Fines;

public class finesdao {
	private Connection myConn;
	public finesdao() throws Exception{
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
	public List<Fines> getAllFines(int card_no) throws Exception {
		List<Fines> list = new ArrayList<>();
		
		Statement myStmt = null;
		Statement myStmt1 = null;
		PreparedStatement stmt=null;
		Statement myStmt2 = null;
		Statement myStmt5 = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		ResultSet myRs2 = null;
		ResultSet myRs5 = null;
		Date datein=null;
		try {
			int loan_id=0;
		float cnt=0;
			boolean paid=false;
			myStmt5=myConn.createStatement();
			myRs5=myStmt5.executeQuery("select sum(fine_amt) from fines,book_loans where book_loans.loan_id=fines.loan_id and card_no='"+card_no+"'");
			while(myRs5.next())
			{
				cnt=myRs5.getFloat(1);
			}
			
			JOptionPane.showMessageDialog(null,"Total fine =" +cnt);
			
			myStmt = myConn.createStatement();
			System.out.println(card_no);
			
			myRs=myStmt.executeQuery("select all f.loan_id,f.paid,bl.datein from book_loans as bl join fines as f on f.loan_id = bl.loan_id and card_no='"+card_no+"';");
			while(myRs.next())
			{
				loan_id = myRs.getInt(1);
				paid=myRs.getBoolean(2);
				datein=myRs.getDate(3);
				//card_no=myRs.getInt(2);
				if(paid==false){
				if(datein!=null){
					stmt= myConn.prepareStatement("update fines set fine_amt = (select DATEDIFF((select datein from book_loans where loan_id ='"+loan_id+"'),(select duedate from book_loans where loan_id ='"+loan_id+"'))*0.25) where loan_id= '"+loan_id+"'");
					stmt.executeUpdate();
					System.out.println(" wen date in not null ");
				}
				else
				{
					stmt= myConn.prepareStatement("update fines set fine_amt = (select DATEDIFF(curdate(),(select duedate from book_loans where loan_id ='"+loan_id+"'))*0.25) where loan_id= '"+loan_id+"'");
					stmt.executeUpdate();
					System.out.println(" wen date in is null ");
				}}
			//	else
				//	JOptionPane.showMessageDialog(null,"Fine already paid");
				myStmt2=myConn.createStatement();
				myRs2=myStmt2.executeQuery("select * from fines where loan_id='"+loan_id+"'");
				System.out.println("in loop"+loan_id);
				while (myRs2.next()) {
					Fines tempFines = convertRowToFines(myRs2);
					list.add(tempFines);
				}
				
			}
			System.out.println(loan_id);
			return list;		
		}
		finally {
			close(myStmt, myRs);
			close(myStmt1, myRs1);
		}
	}
		
	public void RefreshFines() throws Exception{
		Statement myStmt = null;
		Statement myStmt1 = null;
		PreparedStatement stmt=null;
		Statement myStmt2 = null;
		ResultSet myRs = null;
		ResultSet myRs1 = null;
		ResultSet myRs2 = null;
		int loan_id=0;
		boolean paid=false;
		Date datein=null;
		Date duedate=null;
		float fine_amt=0;
		try{
			//System.out.println("entered");
			myStmt = myConn.createStatement();
			myRs=myStmt.executeQuery("select * from fines");
			while(myRs.next())
			{
				loan_id = myRs.getInt(1);
				System.out.println(loan_id);
				fine_amt=myRs.getFloat(2);
				System.out.println(fine_amt);
				paid=myRs.getBoolean(3);
				System.out.println(paid);
				
				//datein=myRs.getDate(3);
				//card_no=myRs.getInt(2);
				int diff=0;
				if(paid==false)
				{
					System.out.println("entered paid while");
					myStmt1 = myConn.createStatement();
					myRs1=myStmt1.executeQuery("select datein,duedate from book_loans where loan_id='"+loan_id+"'");
					while(myRs1.next()){
						duedate=myRs1.getDate(2);
						datein=myRs1.getDate(1);
						System.out.println(datein);
						
						myStmt2 = myConn.createStatement();
						myRs2=myStmt2.executeQuery("select datediff(datein,duedate) from book_loans where loan_id='"+loan_id+"'");
						while(myRs2.next())
						{
							diff=myRs2.getInt(1);
							System.out.println("diff"+diff);
						
						System.out.println(datein);
					
					if(datein!=null && diff>0)
					{
						//stmt= myConn.prepareStatement("update fines set fine_amt = (select DATEDIFF((select datein from book_loans where loan_id ='"+loan_id+"'),(select duedate from book_loans where loan_id ='"+loan_id+"'))*0.25) where loan_id= '"+loan_id+"'");
						stmt=myConn.prepareStatement("update fines set fine_amt=('"+diff+"'*0.25)");
						stmt.executeUpdate();
						System.out.println("update when datein is present and amt ="+fine_amt);
					}
					else
					{
						stmt= myConn.prepareStatement("update fines set fine_amt = (select DATEDIFF(curdate(),(select duedate from book_loans where loan_id ='"+loan_id+"'))*0.25)where loan_id= '"+loan_id+"'");
						stmt.executeUpdate();
						System.out.println("update when datein is null");
					}
					}
				}}
			}
			
		}
		finally {
			close(myStmt, myRs);
			close(myStmt1, myRs1);
		}
		
	}



	private Fines convertRowToFines(ResultSet myRs) throws SQLException {
		int Loan_id=myRs.getInt("Loan_id");
		float fine_amt=myRs.getFloat("fine_amt");
		boolean paid=myRs.getBoolean("paid");
		Fines tempFines=new Fines(Loan_id,fine_amt,paid);
		return tempFines;
	}
	private void close(Statement myStmt, ResultSet myRs) throws SQLException{
		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}
	public void PayFine(int loan_id) {
		PreparedStatement stmt = null;
		PreparedStatement stmt1 = null;
		ResultSet myRs=null;
		ResultSet myRs1=null;
		
		try {
			stmt= myConn.prepareStatement("update fines set paid=True where loan_id='"+loan_id+"'");
			stmt.executeUpdate();
			stmt1=myConn.prepareStatement("update fines set fine_amt=0 where loan_id='"+loan_id+"'");
			stmt1.executeUpdate();
			System.out.println("successfully paid");
			
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
