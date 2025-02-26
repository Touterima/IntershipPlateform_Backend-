package com.example.microserviceuser.services;

import com.example.microserviceuser.dto.UserLoginDto;
import com.example.microserviceuser.dto.UserRegisterDto;
import com.example.microserviceuser.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IUserService {
  public User registerUser(UserRegisterDto user) ;
  public User createUser(User user) ;

  public Optional<User> findByEmail(String email);
  public Optional<User> authenticate(UserLoginDto userLoginDto);
  List<User> getAllUsers();
  // Find a user by their ID
  public Optional<User> findById(Long id);

  // Update an existing user
  public User updateUser(User user);

  // Delete a user by their ID
  public void deleteUser(Long id);


}
