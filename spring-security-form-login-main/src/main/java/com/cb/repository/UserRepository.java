package com.cb.repository;

import com.cb.model.User;



import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);  
   // List<User> findByUser(User user);
}
