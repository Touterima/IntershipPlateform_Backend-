package com.example.microserviceuser.restController;

import com.example.microserviceuser.entities.User;
import com.example.microserviceuser.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;  // Ajout de l'import pour Optional


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

  @GetMapping("/all")
  public ResponseEntity<List<User>> getAllUsers() {
    System.out.println("Endpoint /api/user/all called");
    List<User> users = userService.getAllUsers();
    System.out.println("Users found: " + users.size());
    return ResponseEntity.ok(users);
  }
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    Optional<User> user = userService.findById(id);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    userService.deleteUser(id);
    return ResponseEntity.ok().build();
  }


  // Update user
  @PutMapping("/update/{id}")
  public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
    Optional<User> user = userService.findById(id);
    if (user.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    User existingUser = user.get();
    // Mettre à jour les champs de l'utilisateur existant
    existingUser.setEmail(userDetails.getEmail());
    existingUser.setFirstName(userDetails.getFirstName());
    existingUser.setLastName(userDetails.getLastName());
    existingUser.setPhoneNumber(userDetails.getPhoneNumber());
    existingUser.setStudentClass(userDetails.getStudentClass());
    // Note : la mise à jour du mot de passe n'est pas incluse dans cet exemple.

    userService.updateUser(existingUser);
    return ResponseEntity.ok(existingUser);
  }




}
