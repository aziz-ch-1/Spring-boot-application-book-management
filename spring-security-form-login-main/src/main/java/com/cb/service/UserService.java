package com.cb.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cb.dto.UserDto;
import com.cb.model.User;
@Service
@Component
public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);
    
}