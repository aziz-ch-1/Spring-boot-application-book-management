package com.cb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.model.Book;
import com.cb.repository.bookRepository;
@Service
public class BookService {
	@Autowired
	private bookRepository bRepo;
	
	public void save(Book b) {
		bRepo.save(b);
	}
	
	public List<Book> getAllBook(){
		return bRepo.findAll();
	}
	
	public Book getBookById(long id) {
		return bRepo.findById(id).get();
	}
	public void deleteById(long id) {
		bRepo.deleteById(id);
	}
	
}
