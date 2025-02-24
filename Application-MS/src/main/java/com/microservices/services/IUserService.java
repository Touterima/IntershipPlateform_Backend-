package com.microservices.services;

import com.microservices.dto.UserLoginDto;
import com.microservices.dto.UserRegisterDto;
import com.microservices.entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface IUserService {
  public User registerUser(UserRegisterDto user) ;
  public User createUser(User user) ;

  public Optional<User> findByEmail(String email);
  public Optional<User> authenticate(UserLoginDto userLoginDto);

  }
