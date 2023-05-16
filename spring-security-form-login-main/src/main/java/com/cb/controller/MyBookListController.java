package com.cb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cb.repository.MyBookListRepository;
import com.cb.service.BookService;

import com.cb.service.MyBookListService;

@Controller
public class MyBookListController {
	
	@Autowired
	private MyBookListRepository bk;
	
	
	@GetMapping("/user/deleteMyList/{id}")
	 public String supprimerbook(@PathVariable("id") Long id) {
	     bk.deleteById(id);
	     return "redirect:/user/booklist";
	 }
	}


