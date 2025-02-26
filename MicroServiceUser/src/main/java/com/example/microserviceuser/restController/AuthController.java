package com.example.microserviceuser.restController;



import com.example.microserviceuser.dto.UserLoginDto;
import com.example.microserviceuser.dto.UserRegisterDto;
import com.example.microserviceuser.entities.User;
import com.example.microserviceuser.security.JwtTokenProvider;
import com.example.microserviceuser.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Allow requests from Angular app

public class AuthController {


  @Autowired
  private IUserService userService;
  @Autowired
  private JwtTokenProvider jwtTokenProvider;


  @PostMapping("/register")
  public ResponseEntity<?> registerUser(@RequestBody UserRegisterDto user) {
    if (userService.findByEmail(user.getEmail()).isPresent()) {
      return ResponseEntity.badRequest().body("Email is already taken!");
    }
    User registeredUser = userService.registerUser(user);
    return ResponseEntity.ok(registeredUser);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody UserLoginDto userLoginDto) {

    return userService.authenticate(userLoginDto)
      .map(user -> {
        String token = jwtTokenProvider.generateToken(user.getEmail(),user.getRole());
        return ResponseEntity.ok(Map.of(
          "message", "Login successful",
          "token", token,
          "user", user
        ));
      })
      .orElse(ResponseEntity.badRequest().body(Map.of("error", "Invalid email or password")));
  }
}
