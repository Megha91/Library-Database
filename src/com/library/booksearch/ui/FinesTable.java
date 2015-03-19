package com.library.booksearch.ui;
import java.util.List;

import com.library.booksearch.dao.*;
import com.library.booksearch.core.*;

import javax.swing.table.AbstractTableModel;

public class FinesTable extends AbstractTableModel {

	private static final int LOAN_ID_COL=0;
	private static final int FINE_AMT_COL=1;
	private static final int PAID_COL=2;
	
	private String[] columnNames = { "Loan_id","Fine_amt","Paid"};
	
	private List<Fines> fines;
	
	public FinesTable(List fines)
	{
		this.fines=fines;
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
		return fines.size();
	}
	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		Fines tempfines=fines.get(row);
		switch (col) {
		case LOAN_ID_COL:
			return tempfines.getLoan_id();
		case FINE_AMT_COL:
			return tempfines.getFine_amt();
		case PAID_COL:
			return tempfines.getPaid();
		default:
			return tempfines.getLoan_id();
		}
	}
	
	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
