package com.microservices.restController;



import com.microservices.dto.UserLoginDto;
import com.microservices.dto.UserRegisterDto;
import com.microservices.entities.User;
import com.microservices.security.JwtTokenProvider;
import com.microservices.services.IUserService;
import com.microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200") // Allow requests from Angular app

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
