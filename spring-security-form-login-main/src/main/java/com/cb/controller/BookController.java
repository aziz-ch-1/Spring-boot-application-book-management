package com.cb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cb.model.Book;
import com.cb.model.MyBookList;
import com.cb.model.Role;
import com.cb.model.User;
import com.cb.repository.MyBookListRepository;
import com.cb.repository.UserRepository;
import com.cb.repository.bookRepository;
import com.cb.service.MyBookListService;
import com.cb.service.UserService;
import com.cb.service.UserServiceImpl;
import com.cb.service.BookService;


import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
public class BookController {
	@Autowired
	private bookRepository bk;
	@Autowired
	private MyBookListRepository bl;
	@Autowired
	private MyBookListService bs;
	@Autowired
	private BookService service;
	@Autowired
	private UserRepository rep;
	
	@Autowired
	private UserRepository ur;
 @GetMapping("/home")
 public String home() {
	 return"home";
 } 
 
 @RequestMapping("/user/edit/{id}")
	public String editBook(@PathVariable("id") long id,Model model) {
	 	Book b=service.getBookById(id);
	    model.addAttribute("book",b);
		return "bookEdit";
	}
 
 @GetMapping("/user/bookRegister")
 public String afficherFormulaireAjoutBook(Model model) {
     model.addAttribute("book", new Book());
     return "registerbook";
 }
 @PostMapping("/user/bookRegister")
 public String addbook(@ModelAttribute Book book) {
     bk.save(book);
     return "redirect:/user/available";
 }
 @PostMapping("/user/mylist/{id}")
 public String addbook(@ModelAttribute MyBookList book,BindingResult result) {
     bl.save(book);
     return "redirect:/user/booklist";
 }
 @GetMapping("/user/available")
 public String afficherListeBook(Model model) {
     List<Book> books = bk.findAll();
     model.addAttribute("books", books);
     return "availablebook";
 }
 

 @PostMapping("/user/save/{id}")
 @Secured("permitAll")
 public String saveupdate(@ModelAttribute Book book,@PathVariable("id") Long id) {
	    Book existingBook = service.getBookById(id);

	    existingBook.setName(book.getName());
	    existingBook.setAuthor(book.getAuthor());
	    existingBook.setPrice(book.getPrice());
	 

	 bk.save(existingBook);

	 
	 List<MyBookList> myBookList = bl.findByBook(existingBook);

	
	    for (MyBookList myBook : myBookList) {
	        myBook.setName(existingBook.getName());
	        myBook.setAuthor(existingBook.getAuthor());
	        myBook.setPrice(existingBook.getPrice());
	        bl.save(myBook);
	    }


     return "redirect:/user/available";
     
 }
 @GetMapping("/user/delete/{id}")
 public String supprimerbook(@PathVariable("id") Long id) {
	 Optional<Book> bookOptional = bk.findById(id);
	    if (bookOptional.isPresent()) {
	        Book book = bookOptional.get();
	        
	        
	        List<MyBookList> myBookList = bl.findByBook(book);

	       
	        bl.deleteAll(myBookList);

	      
	        bk.delete(book);
	    }

	 
	 
	
     return "redirect:/user/available";
 } 
 
 @GetMapping("/user/booklist")
	public String getMyBooks(Model model,Authentication authentication)
	{
     UserDetails userDetails = (UserDetails) authentication.getPrincipal();
     User user = rep.findByEmail(userDetails.getUsername());
     List<MyBookList> list = bl.findByUser(user);
		model.addAttribute("booklist",list);
		return "booklist";	
	        
	    }
			 
	
 
 @PostMapping("/user/mylist")
 public ModelAndView addtomylist(@ModelAttribute("id") Long id, Authentication authentication, RedirectAttributes redirectAttributes) {
     Book b = bk.findById(id).orElse(null);
     UserDetails userDetails = (UserDetails) authentication.getPrincipal();
     User user = rep.findByEmail(userDetails.getUsername());

if (bl.existsByBookAndUser(b, user)) {
         redirectAttributes.addFlashAttribute("errorMessage", "le livre est déjà dans vos favoris.");
     } else {
    	 MyBookList mb=new MyBookList(b.getIDBook(),b.getName(),b.getAuthor(),b.getPrice(), null, null);
         mb.setBook(b);
         mb.setUser(user);
   	  bl.save(mb);
         redirectAttributes.addFlashAttribute("successMessage", "le livre  a été ajoutée à vos favoris.");
     }
return new ModelAndView("redirect:/user/booklist");
		
 }
	
 }
 
 


