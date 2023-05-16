package com.cb.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.cb.model.Book;

import jakarta.transaction.Transactional;

@Transactional
public interface bookRepository extends JpaRepository <Book, Long> {
	@RestResource(path="/")
	List<Book> findAll();
	Optional<Book> findById(Long id);
	Book save(Book book);

	@Transactional
    @Modifying
    @Query("UPDATE Book b SET b.name = :name, b.author = :author, b.price = :price WHERE b.IDBook = :id")
    void updatebook(@Param("id") Long id, @Param("name") String name, @Param("author") String author, @Param("price") String price);}


