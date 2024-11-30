package com.itransition.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itransition.model.User;
import com.itransition.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	public User save(User user) {
		user.setBlocked(false);
		user.setId(null);
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("the email " + user.getEmail() + " is already associated with a user");
		}
		return userRepository.save(user);
	}
	public List<User> getUsers(){
		return userRepository.findAll();
	}
	public void updateLogin(User user) {
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
	}
	
	public Optional<User> findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	@Transactional
	public void deleteByEmail(String email) {
		userRepository.deleteByEmail(email);
	}
	
	public void changeBlockedStatus(String email, Boolean status){
		Optional<User> userToBlock = userRepository.findByEmail(email);
		if(userToBlock.isPresent()) {
			userToBlock.get().setBlocked(status);
			userRepository.save(userToBlock.get());
		} else {
			throw new RuntimeException("invalid user");
		}
	}
    
}
