package com.microservices.restController;

import com.microservices.entities.User;
import com.microservices.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
  @Autowired
  private IUserService userService;

  @PostMapping("/create")
  public ResponseEntity<?> createUser(@RequestBody User user) {
    System.out.println("IM AT CREATE USER");
    if (userService.findByEmail(user.getEmail()).isPresent()) {
      return ResponseEntity.badRequest().body("Email is already taken!");
    }
     User createdUser = userService.createUser(user);
    return ResponseEntity.ok(createdUser);
  }


}
