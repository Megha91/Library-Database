package com.library.booksearch.core;

import java.sql.Date;

public class Loans {
	private int Loan_id;
	private String Book_id;
	private String Branch_id;
	private int Card_no;
	private Date Dateout;
	private Date Duedate;
	private Date Datein;
	
	public int getLoan_id() {
		return Loan_id;
	}
	public void setLoan_id(int loan_id) {
		Loan_id = loan_id;
	}
	public String getBook_id() {
		return Book_id;
	}
	public void setBook_id(String book_id) {
		Book_id = book_id;
	}
	public String getBranch_id() {
		return Branch_id;
	}
	public void setBranch_id(String branch_id) {
		Branch_id = branch_id;
	}
	public int getCard_no() {
		return Card_no;
	}
	public void setCard_no(int card_no) {
		Card_no = card_no;
	}
	public Date getDateout() {
		return Dateout;
	}
	public void setDateout(Date dateout) {
		Dateout = dateout;
	}
	public Date getDuedate() {
		return Duedate;
	}
	public void setDuedate(Date duedate) {
		Duedate = duedate;
	}
	public Date getDatein() {
		return Datein;
	}
	public void setDatein(Date datein) {
		Datein = datein;
	}
	@Override
	public String toString() {
		return "Loans [Loan_id=" + Loan_id + ", Book_id=" + Book_id
				+ ", Branch_id=" + Branch_id + ", Card_no=" + Card_no
				+ ", Dateout=" + Dateout + ", Duedate=" + Duedate
				+ ", Datein=" + Datein + "]";
	}
	public Loans(int loan_id, String book_id, String branch_id, int card_no,
			Date dateout, Date duedate, Date datein) {
		super();
		Loan_id = loan_id;
		Book_id = book_id;
		Branch_id = branch_id;
		Card_no = card_no;
		Dateout = dateout;
		Duedate = duedate;
		Datein = datein;
	}
		
}
