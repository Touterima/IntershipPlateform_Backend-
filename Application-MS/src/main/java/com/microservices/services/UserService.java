package com.microservices.services;


import com.microservices.dto.UserLoginDto;
import com.microservices.dto.UserRegisterDto;
import com.microservices.entities.User;
import com.microservices.entities.UserRole;
import com.microservices.repositories.UserRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserService implements IUserService{

  @Autowired
  private UserRepository userRepository;

   @Autowired
   private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(UserRegisterDto userRegisterDto) {
    User user=new User(userRegisterDto.getEmail(),userRegisterDto.getPassword(),userRegisterDto.getFirstName(),userRegisterDto.getLastName(),userRegisterDto.getPhoneNumber(),userRegisterDto.getStudentClass());
    user.setRole(UserRole.STUDENT);
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
 }
