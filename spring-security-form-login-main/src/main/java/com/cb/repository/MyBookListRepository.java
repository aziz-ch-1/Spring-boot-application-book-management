package com.cb.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cb.model.Book;
import com.cb.model.MyBookList;
import com.cb.model.User;


public interface MyBookListRepository extends JpaRepository <MyBookList, Long>{
	List<MyBookList> findByUser(User user);
	boolean existsByBookAndUser(Book book, User user);
	List<MyBookList> findByBook(Book book);
}
