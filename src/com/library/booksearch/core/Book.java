package com.library.booksearch.core;
import java.math.BigDecimal;

import com.library.booksearch.core.*;
public class Book {

	private String Book_id;
	private String title;
	private String author_name;
	private String branch_id;
	private int no_of_copies;
	private int no_of_copies_available;
	
	
	
	public int getNo_of_copies_available() {
		return no_of_copies_available;
	}




	public void setNo_of_copies_available(int no_of_copies_available) {
		this.no_of_copies_available = no_of_copies_available;
	}


	
	
	public Book(String book_id, String title, String author_name, String branch_id, int no_of_copies,int no_of_copies_available) {
		super();
		this.Book_id = book_id;
		this.title = title;
		this.author_name = author_name;
		this.branch_id=branch_id;
		this.no_of_copies=no_of_copies;
		this.no_of_copies_available=no_of_copies_available;
	}

	public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public String getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(String branch_id) {
		this.branch_id = branch_id;
	}

	public int getNo_of_copies() {
		return no_of_copies;
	}

	public void setNo_of_copies(int no_of_copies) {
		this.no_of_copies = no_of_copies;
	}

	public String getauthor_name() {
		return author_name;
	}

	public void setauthor_name(String author_name) {
		this.author_name = author_name;
	}

	
	public String getBook_id() {
		return Book_id;
	}

	public void setBook_id(String book_id) {
		Book_id = book_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	

	@Override
	public String toString() {
		return "Book [Book_id=" + Book_id + ", title=" + title
				+ ", author_name=" + author_name + ", branch_id=" + branch_id
				+ ", no_of_copies=" + no_of_copies + "]";
	}



	
		
}
