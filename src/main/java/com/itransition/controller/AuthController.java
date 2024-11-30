package com.itransition.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itransition.model.LoginRequest;
import com.itransition.model.User;
import com.itransition.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest, HttpSession session){
		Optional<User> user = userService.findByEmail(loginRequest.getEmail());
		
		if(user.get().getBlocked()) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is blocked");
		}
		if(user.isPresent() && passwordEncoder.matches(loginRequest.getPassword(), user.get().getPassword()) ) {
			userService.updateLogin(user.get());
			session.setAttribute("user", user);
			return ResponseEntity.ok("Logged in");
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Bad credentials");
	}
	
	@PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
		try {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return ResponseEntity.ok("User registered");
		}catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
    }
	
	@PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("logged out");
    }
}
