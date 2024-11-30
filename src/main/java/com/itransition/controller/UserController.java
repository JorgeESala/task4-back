package com.itransition.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itransition.dto.EmailDTO;
import com.itransition.model.User;
import com.itransition.service.UserService;

import jakarta.servlet.http.HttpSession;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getUsers(HttpSession session){
		if(!isUserLogged(session))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		return new ResponseEntity<List<User>>(userService.getUsers(), HttpStatus.OK);
	}
	
//	@PostMapping
//	public ResponseEntity<User> createUser(User user){
//		try {
//			User createdUser = userService.save(user);
//			return new ResponseEntity<User> (createdUser, HttpStatus.CREATED);
//			
//		}catch (Exception e) {
//			return new ResponseEntity<User>(HttpStatus.CONFLICT);
//		}
//	}
	
//	@PostMapping("/block")
//	public ResponseEntity<String> blockUser(@RequestBody EmailDTO email, HttpSession session){
//		if(!isUserLogged(session))
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		try {
//			userService.changeBlockedStatus(email.getEmail(), true);
//			return ResponseEntity.ok("User blocked");
//		}catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//	}
	
//	@PostMapping("/unblock")
//	public ResponseEntity<String> unblockUser(@RequestBody EmailDTO email, HttpSession session){
//		if(!isUserLogged(session))
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		try {
//			userService.changeBlockedStatus(email.getEmail(), false);
//			return ResponseEntity.ok("User unblocked");
//		}catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//	}
	
	@PostMapping("/unblock")
	public ResponseEntity<String> unblockUsers(@RequestBody List<EmailDTO> usersEmail, HttpSession session){
		
		if(!isUserLogged(session))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return changeUsersStatus(usersEmail, false);
	}
	
	@PostMapping("/block")
	public ResponseEntity<String> blockUsers(@RequestBody List<EmailDTO> usersEmail, HttpSession session){
		
		if(!isUserLogged(session))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		
		return changeUsersStatus(usersEmail, true);
	}
	
//	@PostMapping("/delete")
//	public ResponseEntity<String> deleteUser(@RequestBody EmailDTO email, HttpSession session){
//		if(!isUserLogged(session))
//			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//		try {
//			userService.deleteByEmail(email.getEmail());
//			return ResponseEntity.ok("User deleted");
//		}catch (Exception e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//		}
//	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteUsers(@RequestBody List<EmailDTO> usersEmail, HttpSession session){
		if(!isUserLogged(session))
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		for(EmailDTO email :usersEmail) {
			try {
				userService.deleteByEmail(email.getEmail());
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			}
		}
		return ResponseEntity.ok("Users deleted");
	}
	
	
	public ResponseEntity<String> changeUsersStatus(List<EmailDTO> usersEmail, Boolean status) {
		try {
			for(EmailDTO email :usersEmail ) {
				userService.changeBlockedStatus(email.getEmail(), status);
			}
			return ResponseEntity.ok(status? "users blocked" :"users unblocked");
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	public Boolean isUserLogged(HttpSession session) {
		Optional<User>user = (Optional<User>) session.getAttribute("user");

        if (user == null) {
            return false;
        }
        return true;
	}
}
