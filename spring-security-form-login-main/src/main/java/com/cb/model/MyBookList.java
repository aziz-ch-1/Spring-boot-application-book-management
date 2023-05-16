package com.cb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name="MyBooks")

public class MyBookList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int id;
	   
	   private String name;
	  
	private String author;
	   
	   private String price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public MyBookList(int id, String name, String author, String price,Book book,User user) {
		super();
		this.id = id;
		this.name = name;
		this.author = author;
		this.price = price;
		this.book = book;
		this.user=user;

	}
	public MyBookList( String name, String author, String price,Book book,User user) {
		super();
		this.name = name;
		this.author = author;
		this.price = price;
		this.book = book;
		this.user=user;

	}


	public MyBookList() {
		super();
		// TODO Auto-generated constructor stub
	}
	   
	   @ManyToOne
	    @JoinColumn(name = "IDBook")
	    private Book book;

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	@ManyToOne
    @JoinColumn(name = "userid")
    private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	   
}
