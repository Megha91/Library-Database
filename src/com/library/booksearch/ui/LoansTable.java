package com.library.booksearch.ui;

import java.sql.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.library.booksearch.core.Book;
import com.library.booksearch.core.Loans;

public class LoansTable extends AbstractTableModel{
	private static final int LOAN_ID_COL = 0;
	private static final int BOOK_ID_COL = 1;
	private static final int BRANCH_ID_COL = 2;
	private static final int CARD_NO_COL = 3;
	private static final int DATEOUT_COL = 4;
	private static final int DUEDATE_COL = 5;
	private static final int DATEIN_COL = 6;
	
	private String[] columnNames = { "Loan_id", "Book_id","Branch_id","Card_id","Dateout","Duedate","Datein" };
	private List<Loans> loans;
	
	public LoansTable(List loans) {
		this.loans=loans;
		
	}

	public String[] getColumnNames() {
		return columnNames;
	}



	public void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}

	
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return loans.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	@Override
	public Object getValueAt(int row, int col) {

		Loans tempLoan = loans.get(row);


		switch (col) {
		case LOAN_ID_COL:
			return tempLoan.getLoan_id();
		case BOOK_ID_COL:
			return tempLoan.getBook_id();
		case BRANCH_ID_COL:
			return tempLoan.getBranch_id();
		case CARD_NO_COL:
			return tempLoan.getCard_no();
		case DATEOUT_COL:
			return tempLoan.getDateout();
		case DUEDATE_COL:
			return tempLoan.getDuedate();
		case DATEIN_COL:
			return tempLoan.getDatein();
		
		default:
			return tempLoan.getLoan_id();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
	
}
