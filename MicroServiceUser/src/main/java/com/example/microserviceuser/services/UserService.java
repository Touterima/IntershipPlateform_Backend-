package com.example.microserviceuser.services;


import com.example.microserviceuser.dto.UserLoginDto;
import com.example.microserviceuser.dto.UserRegisterDto;
import com.example.microserviceuser.entities.User;
import com.example.microserviceuser.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(UserRegisterDto userRegisterDto) {
    User user=new User(userRegisterDto.getEmail(),userRegisterDto.getPassword(),userRegisterDto.getFirstName(),userRegisterDto.getLastName(),userRegisterDto.getPhoneNumber(),userRegisterDto.getStudentClass());
    user.setRole(userRegisterDto.getRole());
    user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));
    return userRepository.save(user);

  }

  @Override
  public User createUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  @Override
  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  @Override
  public Optional<User> authenticate(UserLoginDto userLoginDto) {
    return userRepository.findByEmail(userLoginDto.getEmail())
      .filter(user -> passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword()));
  }

  @Override
  public List<User> getAllUsers() {
    System.out.println("Fetching all users...");
    return userRepository.findAll();
  }
  // Find user by id
  public Optional<User> findById(Long id) {
    return userRepository.findById(id);
  }

  // Delete user by id
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  // Update user
  public User updateUser(User user) {

    return userRepository.save(user);
  }


}

