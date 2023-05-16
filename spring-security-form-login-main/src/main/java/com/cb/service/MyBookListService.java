package com.cb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cb.model.MyBookList;
import com.cb.model.User;
import com.cb.repository.MyBookListRepository;

@Service
public class MyBookListService {
	@Autowired
	private MyBookListRepository mybook;
	
	public void saveMyBooks(MyBookList book) {
		mybook.save(book);
	}
	
	public List<MyBookList> getAllMyBooks(){
		return mybook.findAll();
	}

	public void deleteById(Long id) {
		mybook.deleteById(id);
	}
	
}
