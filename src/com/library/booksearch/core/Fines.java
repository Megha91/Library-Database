package com.library.booksearch.core;

import java.text.DecimalFormat;

public class Fines {
	private int Loan_id;
	private float fine_amt;
	private boolean paid;
	public int getLoan_id() {
		return Loan_id;
	}
	public void setLoan_id(int loan_id) {
		Loan_id = loan_id;
	}
	public float getFine_amt() {
		return fine_amt;
	}
	public void setFine_amt(float fine_amt) {
		this.fine_amt = fine_amt;
	}
	public boolean getPaid() {
		return paid;
	}
	public Fines() {
		super();
	}
	public void setPaid(boolean paid) {
		this.paid = paid;
	}
	@Override
	public String toString() {
		return "Fines [Loan_id=" + Loan_id + ", fine_amt=" + fine_amt
				+ ", paid=" + paid + "]";
	}
	public Fines(int loan_id, float fine_amt, boolean paid) {
		super();
		Loan_id = loan_id;
		this.fine_amt = fine_amt;
		this.paid = paid;
	}
	
	
}
