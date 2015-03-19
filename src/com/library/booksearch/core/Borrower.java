package com.library.booksearch.core;

public class Borrower {
	private int card_id;
	private String fname;
	private String lname;
	private String address;
	private String phone;
	public Borrower(int card_id, String fname, String lname, String address,
			String phone) {
		super();
		this.card_id = card_id;
		this.fname = fname;
		this.lname = lname;
		this.address = address;
		this.phone = phone;
	}
	
	public int getCard_id() {
		return card_id;
	}
	public void setCard_id(int card_id) {
		card_id = card_id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Borrowers [Card_id=" + card_id + ", fname=" + fname
				+ ", lname=" + lname + ", address=" + address + ", phone="
				+ phone + "]";
	}
	
}
