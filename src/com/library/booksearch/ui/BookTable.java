package com.library.booksearch.ui;

import com.library.booksearch.dao.booksdao;
import com.library.booksearch.core.*; 


import java.util.List;

import javax.swing.table.AbstractTableModel;


public class BookTable extends AbstractTableModel {

	private static final int BOOK_ID_COL = 0;
	private static final int TITLE_COL = 1;
	private static final int AUTHOR_COL = 2;
	private static final int BRANCH_ID_COL = 3;
	private static final int NO_OF_COPIES_COL = 4;
	private static final int NO_OF_COPIES_AVAILABLE_COL = 5;
	

	private String[] columnNames = { "Book_id", "Title","Author_name","Branch_id","No_of_Copies","No_of_copies_available"};
//,"Branch_id","No_of_copies"
	private List<Book> book;
	//private List<Book_Authors> bookauthors;

	public BookTable(List book) {
		this.book=book;
		
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
		return book.size();
	}
	

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Book tempBook = book.get(row);


		switch (col) {
		case BOOK_ID_COL:
			return tempBook.getBook_id();
		case TITLE_COL:
			return tempBook.getTitle();
		case AUTHOR_COL:
			return tempBook.getauthor_name();
		case BRANCH_ID_COL:
			return tempBook.getBranch_id();
		case NO_OF_COPIES_COL:
			return tempBook.getNo_of_copies();
		case NO_OF_COPIES_AVAILABLE_COL:
			return tempBook.getNo_of_copies_available();	

		default:
			return tempBook.getTitle();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
