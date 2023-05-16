package com.cb.model;


import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Book {
@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int IDBook;
   
   private String name;
  
private String author;
   
   private String price;
   
   public Book(int iDBook, String name, String author, String price, User user) {
	super();
	IDBook = iDBook;
	this.name = name;
	this.author = author;
	this.price = price;
	this.user = user;
}

public Book() {
	super();
	// TODO Auto-generated constructor stub
}

public int getIDBook() {
	return IDBook;
}

public void setIDBook(int iDBook) {
	IDBook = iDBook;
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
@ManyToOne
@JoinColumn(name = "user")
private User user;

public User getUser() {
	return user;
}

public void setUser(User user) {
	this.user = user;
}


}